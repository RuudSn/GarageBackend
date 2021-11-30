package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.exception.BadRequestException;
import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.*;
import nl.ruud.Eindopdracht.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    // berekenen totaalbedragen incl. btw en genereren factuur

    public long addCarJobInvoice(Long carJobId, String name, String telephone, String email, String licensePlate) {

        CarJob carJob = getCarJobFromOptionalInput(carJobId, name, telephone,  email, licensePlate);


        CarJobInvoice carJobInvoice = new CarJobInvoice();
        CarJobStatus status = carJob.getStatus();
        if((status == CarJobStatus.COMPLETED) || (status == CarJobStatus.DONOTEXECUTE)){

            carJobInvoice.setCustomerName(carJob.getCustomer().getName());
            carJobInvoice.setRemarks(carJob.getRemarks());

            List<JobOperation> jobOperations = jobOperationRepository.findAllByCarJobId(carJobId);
            List<String> operationDescriptions = new ArrayList<String>();



            double operationsCharge  = 0;
            for(JobOperation jobOperation : jobOperations) {
                operationDescriptions.add(jobOperation.getOperation().getDescription());
                double price = jobOperation.getOperation().getPrice();
              //  double quantity = jobOperation.getQuantity();
            //    double charge = price * quantity;
                operationsCharge = operationsCharge ;
            }
            carJobInvoice.setPartDescriptions(operationDescriptions);
            double operationsWithVAT = operationsCharge * 1.21;
            carJobInvoice.setOperationsCharge(operationsWithVAT);



            List<JobPart> jobParts = jobPartRepository.findAllByCarJobId(carJobId);
            List<String> partDescriptions = new ArrayList<String>();

            double partsCharge = 0;
            for(JobPart jobPart : jobParts){
                partDescriptions.add(jobPart.getPart().getDescription());
                double price = jobPart.getPart().getPrice();
                double quantity = jobPart.getQuantity();
                double charge = price * quantity;
                partsCharge = partsCharge +charge;
            }
            carJobInvoice.setPartDescriptions(partDescriptions);

            double partsWithVAT = partsCharge * 1.21;
            carJobInvoice.setPartsCharge(partsWithVAT);


            double totalCharge = operationsWithVAT + partsWithVAT;
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
            else{
                carJob = carJobRepository.findById(carJobId).get(); }

        }else if (carJobId == null  && name != null && email != null){

            carJob = carJobRepository.findByCustomerNameAndCustomerEmail(name, email);


        }else if (carJobId == null  && name != null && telephone != null) {

            carJob = carJobRepository.findByCustomerNameAndCustomerTelephone(name, telephone);

        }else if(carJobId==null && licensePlate != null){

            carJob = carJobRepository.findByCarLicensePlate(licensePlate);

        }else {throw new BadRequestException("djj");}

        return carJob;
    }



    public void removeCarJobInvoiceById(long id) {
        if(carJobInvoiceRepository.existsById(id)){
        carJobInvoiceRepository.deleteById(id);}else{
            throw new RecordNotFoundException(""); }
    }




}
