package nl.ruud.Eindopdracht.controller;

import nl.ruud.Eindopdracht.model.JobPart;
import nl.ruud.Eindopdracht.model.JobPartID;
import nl.ruud.Eindopdracht.service.JobPartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
@RestController
@RequestMapping("/api/v1/jobparts")
public class JobPartController {


    private JobPartService jobPartService;


    @Autowired
    public JobPartController(JobPartService jobPartService) {
        this.jobPartService = jobPartService;
    }



    @PostMapping("/{carjob_id}/{part_id}")
    public ResponseEntity<Object> addJobPart(@PathVariable("carjob_id")Long carJobId,
                                             @PathVariable("part_id")Long partId,
                                             @RequestBody JobPart quantity) {
        JobPartID ID = jobPartService.addJobPart(carJobId, partId, quantity);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();

        return ResponseEntity.created(location).body(location);
    }

    @GetMapping("/{carjob_id}/{part_id}")
    public ResponseEntity<Object> getJobPart(@PathVariable("carjob_id") Long carJobId,
                                             @PathVariable("part_id") Long partId){
        return ResponseEntity.ok().body(jobPartService.getJobPartById(carJobId, partId));
    }

    @GetMapping("/{carjob_id}")
    public ResponseEntity<Object> getJobPartsByCarJobId (@PathVariable("carjob_id") Long carJobId){
        return ResponseEntity.ok().body(jobPartService.getJobPartsByCarJobId(carJobId));
    }
    @GetMapping("/{part_id}")
    public ResponseEntity<Object> getJobParts(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(jobPartService.getJobPartsByPartId(id));
    }

    @DeleteMapping("/{carjob_id}/{part_id}")
    public ResponseEntity<Object> removeJobPart (@PathVariable("carjob_id") Long carJobId,
                                                 @PathVariable("part_id") Long partId) {
        jobPartService.removeJobPart(carJobId, partId);
        return ResponseEntity.noContent().build().ok("Deleted");
    }

    @PutMapping("/{carjob_id}/{part_id}")
    public ResponseEntity<Object> updateJobPart(@PathVariable("carjob_id") Long carJobId,
                                                @PathVariable("part_id") Long partId,
                                                @RequestBody JobPart quantity) {
        jobPartService.updateJobPart(carJobId, partId, quantity);
        return ResponseEntity.noContent().build().ok("Updated");
    }












}
