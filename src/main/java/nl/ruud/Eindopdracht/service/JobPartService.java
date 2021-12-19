package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.CarJob;
import nl.ruud.Eindopdracht.model.JobPart;
import nl.ruud.Eindopdracht.model.JobPartID;
import nl.ruud.Eindopdracht.model.Part;
import nl.ruud.Eindopdracht.repository.CarJobRepository;
import nl.ruud.Eindopdracht.repository.JobPartRepository;
import nl.ruud.Eindopdracht.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JobPartService {

    private CarJobRepository carJobRepository;

    private PartRepository partRepository;

    private JobPartRepository jobPartRepository;

    @Autowired
    public JobPartService(CarJobRepository carJobRepository, PartRepository partRepository, JobPartRepository jobPartRepository){
        this.carJobRepository = carJobRepository;
        this.partRepository = partRepository;
        this.jobPartRepository = jobPartRepository;

    }


    public Collection<JobPart> getJobParts(){
        return jobPartRepository.findAll();
    }

    public JobPart getJobPartById(Long carJobId, Long partId) {
        JobPartID ID = new JobPartID(carJobId, partId);
        if (jobPartRepository.existsById(ID)) {
            return jobPartRepository.findById(ID).get();
        } else { throw new RecordNotFoundException(); }
    }

    public Collection<JobPart> getJobPartsByCarJobId(Long carJobId){
        return jobPartRepository.findAllByCarJobId(carJobId);
    }

    public Collection<JobPart> getJobPartsByPartId(Long partId){
        return jobPartRepository.findAllByPartId(partId);
    }


    public JobPartID addJobPart(Long carJobId, Long partId, double quantity){
        JobPart jobPart = new JobPart();
        if(!carJobRepository.existsById(carJobId)){throw new RecordNotFoundException();}
        CarJob carJob = carJobRepository.findById(carJobId).get();
        if(!partRepository.existsById(partId)){throw  new RecordNotFoundException();}
        Part part = partRepository.findById(partId).get();
        jobPart.setCarJob(carJob);
        jobPart.setPart(part);
        JobPartID ID= new JobPartID(carJobId, partId);
        jobPart.setID(ID);
        jobPart.setQuantity(quantity);
        jobPartRepository.save(jobPart);
        return ID;
    }


    public void removeJobPart(Long carJobId, Long partId) {
        JobPartID ID = new JobPartID(carJobId, partId);
        if(jobPartRepository.existsById(ID)){
            jobPartRepository.deleteById(ID);
        }else {
            throw new RecordNotFoundException(); }
    }

    public void updateJobPart(Long carJobId, Long partId, double quantity) {
        JobPartID ID = new JobPartID(carJobId, partId);
        if(jobPartRepository.existsById(ID)){
            JobPart existingJobPart = jobPartRepository.findById(ID).get();
            existingJobPart.setQuantity(quantity);
            jobPartRepository.save(existingJobPart); }
        else { throw new RecordNotFoundException(); }
    }






}
