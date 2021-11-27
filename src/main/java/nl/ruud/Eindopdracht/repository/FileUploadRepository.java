package nl.ruud.Eindopdracht.repository;


import nl.ruud.Eindopdracht.model.FileUpload;
import org.springframework.data.repository.CrudRepository;

public interface FileUploadRepository extends CrudRepository<FileUpload,  Long> {
}
