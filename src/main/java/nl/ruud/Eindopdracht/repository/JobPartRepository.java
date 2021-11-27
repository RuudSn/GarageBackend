package nl.ruud.Eindopdracht.repository;

import nl.ruud.Eindopdracht.model.JobPart;
import nl.ruud.Eindopdracht.model.JobPartID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface JobPartRepository extends JpaRepository<JobPart, JobPartID> {

    List<JobPart> findAllByCarJobId(Long carJobId);
    List<JobPart> findAllByPartId(Long partId);

}
