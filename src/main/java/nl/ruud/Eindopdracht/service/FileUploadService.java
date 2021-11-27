package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.FileUpload;
import nl.ruud.Eindopdracht.repository.FileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class FileUploadService {

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

    public Optional<FileUpload> getFileById(long id) {
        if (!fileUploadRepository.existsById(id)) throw new RecordNotFoundException();
        return fileUploadRepository.findById(id);
    }


    public long uploadFile(MultipartFile file) {

        String originalFilename = StringUtils.cleanPath(file.getOriginalFilename());

        FileUpload newFileToStore = new FileUpload();
        newFileToStore.setFileName(originalFilename);

        FileUpload storedFile = fileUploadRepository.save(newFileToStore);

        return storedFile.getId();
    }

    public void deleteFile(long id) {
        if (!fileUploadRepository.existsById(id)) throw new RecordNotFoundException();
        fileUploadRepository.deleteById(id);
    }



}
