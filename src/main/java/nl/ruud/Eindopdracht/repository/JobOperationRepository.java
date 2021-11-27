package nl.ruud.Eindopdracht.repository;

import nl.ruud.Eindopdracht.model.JobOperation;
import nl.ruud.Eindopdracht.model.JobOperationID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobOperationRepository extends JpaRepository<JobOperation, JobOperationID> {

    List<JobOperation> findAllByCarJobId(Long carJobId);

    List<JobOperation> findAllByOperationId(Long operationId);

}
