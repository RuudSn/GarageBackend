package nl.ruud.Eindopdracht.service;


import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.*;
import nl.ruud.Eindopdracht.repository.CarJobRepository;
import nl.ruud.Eindopdracht.repository.JobOperationRepository;
import nl.ruud.Eindopdracht.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class JobOperationService {


    private CarJobRepository carJobRepository;

    private OperationRepository operationRepository;

    private JobOperationRepository jobOperationRepository;

    @Autowired
    public JobOperationService(CarJobRepository carJobRepository,OperationRepository operationRepository, JobOperationRepository jobOperationRepository) {
        this.carJobRepository = carJobRepository;
        this.operationRepository = operationRepository;
        this.jobOperationRepository = jobOperationRepository;
    }



    public nl.ruud.Eindopdracht.model.JobOperation getJobOperationById(Long carJobId, Long operationId) {
        JobOperationID ID = new JobOperationID(carJobId, operationId);
        if (jobOperationRepository.existsById(ID)) {
            return jobOperationRepository.findById(ID).get();
        } else { throw new RecordNotFoundException(); }
    }

    public Collection<JobOperation> getJobOperationsByCarJobId (Long carJobId){
        return jobOperationRepository.findAllByCarJobId(carJobId);
    }

    public Collection<JobOperation> getJobOperationsByOperationId(Long operationId){
        return jobOperationRepository.findAllByOperationId(operationId);
    }


    public JobOperationID addJobOperation(Long carJobId, Long operationId){
        if(!carJobRepository.existsById(carJobId)){throw new RecordNotFoundException();}
        CarJob carJob = carJobRepository.findById(carJobId).get();
        if(!operationRepository.existsById(operationId)){throw  new RecordNotFoundException();}
        Operation operation = operationRepository.findById(operationId).get();
        JobOperation jobOperation = new JobOperation();
        jobOperation.setCarJob(carJob);
        jobOperation.setOperation(operation);
        JobOperationID ID= new JobOperationID(carJobId, operationId);
        jobOperation.setID(ID);
        jobOperationRepository.save(jobOperation);
        return ID;
    }



    public void removeJobOperation(Long carJobId, Long operationId) {
        JobOperationID ID = new JobOperationID(carJobId, operationId);
        if(jobOperationRepository.existsById(ID)){
            jobOperationRepository.deleteById(ID);
        }else {
            throw new RecordNotFoundException(); }
    }

    public void updateJobOperation(Long carJobId, Long operationId) {
        JobOperationID ID = new JobOperationID(carJobId, operationId);
        if(jobOperationRepository.existsById(ID)){
            JobOperation existingJobOperation = jobOperationRepository.findById(ID).get();
            CarJob carJob = carJobRepository.findById(carJobId).get();
            Operation operation = operationRepository.findById(operationId).get();
            existingJobOperation.setCarJob(carJob);
            existingJobOperation.setOperation(operation);
            jobOperationRepository.save(existingJobOperation); }
        else { throw new RecordNotFoundException(); }
    }








}
