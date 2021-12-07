package nl.ruud.Eindopdracht.controller;

import nl.ruud.Eindopdracht.model.JobOperation;
import nl.ruud.Eindopdracht.model.JobOperationID;
import nl.ruud.Eindopdracht.model.JobPart;
import nl.ruud.Eindopdracht.model.JobPartID;
import nl.ruud.Eindopdracht.service.JobOperationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/joboperations")
public class JobOperationController {


    private JobOperationService jobOperationService;

    public JobOperationController(JobOperationService jobOperationService) {
        this.jobOperationService = jobOperationService;
    }


    @PostMapping("/{carjob_id}/{operation_id}")
    public ResponseEntity<Object> addJobOperation(@PathVariable("carjob_id")Long carJobId,
                                             @PathVariable("operation_id")Long operationId,
                                             @RequestBody JobOperation quantity) {
        JobOperationID ID = jobOperationService.addJobOperation(carJobId, operationId, quantity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(location);
    }

    @GetMapping("/{carjob_id}/{operation_id}")
    public ResponseEntity<Object> getJobOperation(@PathVariable("carjob_id") Long carJobId,
                                             @PathVariable("operation_id") Long operationId){
        return ResponseEntity.ok().body(jobOperationService.getJobOperationById(carJobId, operationId));
    }

    @GetMapping("/{carjob_id}/operations")
    public ResponseEntity<Object> getJobOperationsByCarJobId (@PathVariable("carjob_id") Long carJobId){
        return ResponseEntity.ok().body(jobOperationService.getJobOperationsByCarJobId(carJobId));
    }
    @GetMapping("/{operation_id}/jobs")
    public ResponseEntity<Object> getJobOperations(@PathVariable("operation_id") Long id){
        return ResponseEntity.ok().body(jobOperationService.getJobOperationsByOperationId(id));
    }

    @DeleteMapping("/{carjob_id}/{operation_id}")
    public ResponseEntity<Object> removeJobOperation (@PathVariable("carjob_id") Long carJobId,
                                                 @PathVariable("operation_id") Long operationId) {
        jobOperationService.removeJobOperation(carJobId, operationId);
        return ResponseEntity.noContent().build().ok("Deleted");
    }

    @PutMapping("/{carjob_id}/{operation_id}")
    public ResponseEntity<Object> updateJobOperation(@PathVariable("carjob_id") Long carJobId,
                                                @PathVariable("operation_id") Long operationId,
                                                @RequestBody JobOperation quantity) {
        jobOperationService.updateJobOperation(carJobId, operationId, quantity);
        return ResponseEntity.noContent().build().ok("Updated");
    }








}
