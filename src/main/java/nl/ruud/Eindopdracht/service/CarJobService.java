package nl.ruud.Eindopdracht.service;


import nl.ruud.Eindopdracht.dto.CarJobInputDto;
import nl.ruud.Eindopdracht.exception.BadRequestException;
import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.*;
import nl.ruud.Eindopdracht.repository.CarJobRepository;
import nl.ruud.Eindopdracht.repository.CustomerRepository;
import nl.ruud.Eindopdracht.repository.JobOperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class CarJobService {

    private CarJobRepository carJobRepository;

    private CustomerRepository customerRepository;


    @Autowired
    public CarJobService(CarJobRepository carJobRepository, CustomerRepository customerRepository){
        this.carJobRepository = carJobRepository;
        this.customerRepository  = customerRepository;

    }


    public List<CarJob> getCarJobs(){
       return carJobRepository.findAll();
    }

    public CarJob getCarJobById(long id) {
        if (carJobRepository.existsById(id)) {
            return carJobRepository.findById(id).get();}
        else { throw new RecordNotFoundException();}
    }


    public long addCarJob(CarJob carJob) {

        Customer customer = carJob.getCar().getCustomer();
        if(customer == carJob.getCustomer()){
            CarJob addedCarJob = carJobRepository.save(carJob);
            return addedCarJob.getId();}
   else{throw new BadRequestException("that's not your own car");}
    }



    public void updateCarJob(long id, CarJob carJob){
        if(carJobRepository.existsById(id)){
           CarJob existingCarJob = carJobRepository.findById(id).get();
            existingCarJob.setStatus(carJob.getStatus());
            existingCarJob.setRepairDate(carJob.getRepairDate());
            existingCarJob.setRemarks(carJob.getRemarks());
            carJobRepository.save(existingCarJob);
        }else { throw new RecordNotFoundException();}
    }
/*
    public void partialUpdateCarJob(long id, CarJob carJob){
        if(carJobRepository.existsById(id)){
        CarJob existingJob = carJobRepository.findById(id).get();
        if(carJob.getStatus()!= null) {
            existingJob.setStatus(carJob.getStatus());
        }
        if(carJob.getRepairDate()!=null){
            existingJob.setRepairDate(carJob.getRepairDate());
        }
        if(carJob.getRemarks()!=null){
            existingJob.setRemarks(carJob.getRemarks());
        }
        carJobRepository.save(existingJob);
        }else {throw new RecordNotFoundException();}
    }

 */


   public void partialUpdateCarJob(long id, Map<String, Object> fields){
        if(carJobRepository.existsById(id)){
        CarJob carJob = carJobRepository.findById(id).get();
        for (String key : fields.keySet()){
            switch(key.toLowerCase()){
                case "status":
                                                                            //    if (  fields.get(key) instanceof CarJobStatus){
                    carJob.setStatus((CarJobStatus) fields.get(key) );      //}
                    break;
                case "repairdate" :                                        //if(fields.get(key)instanceof LocalDateTime){
                    carJob.setRepairDate((LocalDateTime) fields.get(key));     //}
                    break;
                case "remarks" : carJob.setRemarks((String) fields.get(key));
                    break;
            } }
        carJobRepository.save(carJob);
    }else{throw new RecordNotFoundException();}
    }


    public List<CarJob> getCarJobsByStatus(CarJobStatus status){
        return carJobRepository.findByStatus(status);
    }

    public void deleteCarJobById(long id){
        if(carJobRepository.existsById(id)){
            carJobRepository.deleteById(id);
        }else{
            throw new RecordNotFoundException(); }
    }





}
