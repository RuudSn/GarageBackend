package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.dto.FileUploadDto;
import nl.ruud.Eindopdracht.dto.FileUploadInputDto;
import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.FileUpload;
import nl.ruud.Eindopdracht.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class FileUploadService {

    @Value("${app.upload.dir:${user.home}}")
    private String uploadDirectory;  // relative to root
    private final Path uploads = Paths.get("uploads");

    private FileUploadRepository fileUploadRepository;

    @Autowired
    public FileUploadService(FileUploadRepository fileUploadRepository) {
        this.fileUploadRepository = fileUploadRepository;
    }


    public boolean fileExistsById(long id) {
        return fileUploadRepository.existsById(id);
    }


    public Iterable<FileUpload> getFiles() {
        return fileUploadRepository.findAll();
    }

    public FileUploadDto getFileById(long id) {
        Optional<FileUpload> stored = fileUploadRepository.findById(id);

        if (stored.isPresent()) {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand("download").toUri();

            FileUploadDto Dto = new FileUploadDto();
            Dto.setFileName(stored.get().getFileName());
            Dto.setTitle(stored.get().getTitle());
            Dto.setDescription(stored.get().getDescription());
            Dto.setMediaType(stored.get().getMediaType());
            Dto.setDownloadUri(uri.toString());
            return Dto;
        }
        else {
            throw new RecordNotFoundException();
        }
    }


    public long uploadFile(FileUploadInputDto Dto) {


        MultipartFile file = Dto.getFile();
        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());
        Path copyLocation = this.uploads.resolve(file.getOriginalFilename());


        FileUpload newFile = new FileUpload();
        newFile.setFileName(originalFilename);
        newFile.setTitle(Dto.getTitle());
        newFile.setDescription(Dto.getDescription());
        newFile.setLocation(copyLocation.toString());


        FileUpload storedFile = fileUploadRepository.save(newFile);

        return storedFile.getId();
    }

    public void deleteFile(long id) {
        if (!fileUploadRepository.existsById(id)) throw new RecordNotFoundException();
        fileUploadRepository.deleteById(id);
    }



}
