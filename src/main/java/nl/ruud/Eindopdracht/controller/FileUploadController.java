package nl.ruud.Eindopdracht.controller;

import nl.ruud.Eindopdracht.dto.FileUploadDto;
import nl.ruud.Eindopdracht.dto.FileUploadInputDto;
import nl.ruud.Eindopdracht.model.FileUpload;
import nl.ruud.Eindopdracht.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/files")
@CrossOrigin
public class FileUploadController {

    private FileUploadService fileUploadService;

    @Autowired
    public FileUploadController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }



    @GetMapping("")
    public ResponseEntity<Object> getFiles() {
        Iterable<FileUpload> files = fileUploadService.getFiles();
        return ResponseEntity.ok().body(files);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getFileInfo(@PathVariable long id) {
        FileUploadDto response = fileUploadService.getFileById(id);
        return ResponseEntity.ok().body(response);
    }



    @PostMapping(value = "", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE},
                     produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Object> uploadFile( FileUploadInputDto Dto) {

        long newId = fileUploadService.uploadFile(Dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteFile(@PathVariable long id) {
        fileUploadService.deleteFile(id);
        return ResponseEntity.noContent().build();
    }








}
