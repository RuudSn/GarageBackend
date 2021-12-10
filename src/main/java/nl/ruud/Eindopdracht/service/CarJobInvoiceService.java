package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.exception.BadRequestException;
import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.*;
import nl.ruud.Eindopdracht.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarJobInvoiceService {


    private CarJobRepository carJobRepository;

    private CustomerRepository customerRepository;

    private JobOperationRepository jobOperationRepository;

    private JobPartRepository jobPartRepository;

    private CarJobInvoiceRepository carJobInvoiceRepository;


    @Autowired
    public CarJobInvoiceService(CarJobRepository carJobRepository, CustomerRepository customerRepository,
                    JobOperationRepository jobOperationRepository, JobPartRepository jobPartRepository,
                                CarJobInvoiceRepository carJobInvoiceRepository) {
        this.carJobRepository = carJobRepository;
        this.customerRepository = customerRepository;
        this.jobOperationRepository = jobOperationRepository;
        this.jobPartRepository = jobPartRepository;
        this.carJobInvoiceRepository = carJobInvoiceRepository;
    }

   public List<CarJobInvoice> getInvoices(){
        List<CarJobInvoice> inv = carJobInvoiceRepository.findAll();
        return inv;
   }

   public CarJobInvoice getInvoiceById(Long id){
        if(carJobInvoiceRepository.existsById(id)){
            return carJobInvoiceRepository.findById(id).get();
        }else{throw new RecordNotFoundException();}
   }


    // berekenen totaalbedragen incl. btw  en genereren factuur

    public Long addCarJobInvoice(Long carJobId, String name, String telephone, String email, String licensePlate) {

        CarJob carJob = getCarJobFromOptionalInput(carJobId, name, telephone,  email, licensePlate);


        CarJobInvoice carJobInvoice = new CarJobInvoice();
        carJobId = carJob.getId();

        boolean statusOk = StatusCheck(carJob);
       if(statusOk){

            carJobInvoice.setCustomerName(carJob.getCustomer().getName());
            carJobInvoice.setRemarks(carJob.getRemarks());



            List<JobOperation> jobOperations = jobOperationRepository.findAllByCarJobId(carJobId);
            List<String> operationDescriptions = getOperationsDescriptions(jobOperations);

           List<JobPart> jobParts = jobPartRepository.findAllByCarJobId(carJobId);
           List<String> partDescriptions = getPartDescriptions(jobParts);

           carJobInvoice.setOperationDescriptions(operationDescriptions);
           carJobInvoice.setPartDescriptions(partDescriptions);



            double operationsCharge = calculateOperationsCharge(jobOperations);

            double partsCharge = calculatePartsCharge(jobParts);

            double totalCharge = partsCharge + operationsCharge;

            carJobInvoice.setOperationsCharge(operationsCharge);
            carJobInvoice.setPartsCharge(partsCharge);
            carJobInvoice.setTotalCharge(totalCharge);


            carJobInvoiceRepository.save(carJobInvoice);

        }else { throw new BadRequestException("check status of carjob"); }

        return carJobInvoice.getId();
    }


        // ophalen van juiste carjob voor invoice aan hand van opgave aan balie van ofwel: carjobID, klantnaam+telfoonnr,
      // klantnaam+email, of kenteken.    wellicht onnodig ?  evt. makkelijk te verwijderen

    public CarJob getCarJobFromOptionalInput(Long carJobId, String name, String telephone, String email, String licensePlate){
        CarJob carJob = new CarJob();

        if(carJobId != null){
            if(!carJobRepository.existsById(carJobId)){throw new RecordNotFoundException("unknown carJobId");}
            else{ carJob = carJobRepository.findById(carJobId).get(); }

        }else if ( name != null && email != null){
            carJob = carJobRepository.findByCustomerNameAndCustomerEmail(name, email);

        }else if (name != null && telephone != null) {
            carJob = carJobRepository.findByCustomerNameAndCustomerTelephone(name, telephone);

        }else if(licensePlate != null){
            carJob = carJobRepository.findByCarLicensePlate(licensePlate);

        }else {throw new BadRequestException("foutieve invoer");}

        return carJob; }



    public boolean StatusCheck(CarJob carJob){
        CarJobStatus status = carJob.getStatus();
        if( (status == CarJobStatus.COMPLETED) || (status == CarJobStatus.DONOTEXECUTE)){
            return true;}  return false;
    }


    public List<String> getOperationsDescriptions(List<JobOperation> jobOperations){
        List<String> operationDescriptions = new ArrayList<String>();
        for(JobOperation jobOperation : jobOperations) {
            operationDescriptions.add(jobOperation.getOperation().getDescription());
        }
        return operationDescriptions;
    }

    public List<String> getPartDescriptions(List<JobPart> jobParts){
        List<String> partDescriptions = new ArrayList<String>();
        for(JobPart jobPart : jobParts) {
            partDescriptions.add(jobPart.getPart().getDescription());
        } return partDescriptions; }



    public double calculateOperationsCharge(List<JobOperation> jobOperations){
        double operationsCharge  = 0;
        for(JobOperation jobOperation : jobOperations) {

            double price = jobOperation.getOperation().getPrice();
            operationsCharge = operationsCharge + price ;
        }
            double operationsWithVAT = operationsCharge * 1.21;
            return operationsWithVAT;
    }


    public double calculatePartsCharge(List<JobPart> jobParts){
        double partsCharge = 0;
        for(JobPart jobPart : jobParts){

            double price = jobPart.getPart().getPrice();
            double quantity = jobPart.getQuantity();
            double charge = price * quantity;
            partsCharge = partsCharge +charge;
        }
        double partsWithVAT = partsCharge * 1.21;
        return partsWithVAT;
    }



    public void removeCarJobInvoiceById(long id) {
        if(carJobInvoiceRepository.existsById(id)){
        carJobInvoiceRepository.deleteById(id);}else{
            throw new RecordNotFoundException(""); }
    }




}
