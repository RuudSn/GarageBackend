package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.GarageBackendApplication;
import nl.ruud.Eindopdracht.dto.InvoiceInputDto;
import nl.ruud.Eindopdracht.model.*;
import nl.ruud.Eindopdracht.repository.CarJobInvoiceRepository;
import nl.ruud.Eindopdracht.repository.CarJobRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;


import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest()
//@ContextConfiguration(classes= {GarageBackendApplication.class})

@ExtendWith(MockitoExtension.class)
public class CarJobInvoiceServiceTest {

    @Mock
    CarJobRepository carJobRepository;

    @InjectMocks
    CarJobInvoiceService carJobInvoiceService;

    @BeforeEach
    void setup(){
        CarJobInvoiceService carJobInvoiceService = new CarJobInvoiceService();
    }


    //    CarJobInvoiceService carJobInvoiceService;



    @Test
    public void givenCarJobWithStatusCOMPLETEDOrDONOTEXECUTEShouldReturnTrue(){

        CarJob job = new CarJob();
      //  job.setId(12L);
        job.setStatus(CarJobStatus.DONOTEXECUTE);
        //job.setRemarks("Test");
       // LocalDateTime date = LocalDateTime.of(2021, Month.APRIL,10,10,30);
       // job.setRepairDate(date);



        Part part = new Part();
        part.setDescription("testPart1");
        part.setPrice(100.00);
        part.setQuantity(2L);
        part.setId(13L);



        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jans@mail");
        customer.setTelephone("1234");
        customer.setId(15L);

        Car car = new Car();
        car.setType("auto");
        car.setLicensePlate("12AB34");
        car.setCustomer(customer);
        car.setId(16L);

        JobPart jobPart = new JobPart();
        jobPart.setCarJob(job);
        jobPart.setPart(part);



        CarJobInvoiceService carJobInvoiceService = new CarJobInvoiceService();

        boolean statusOk = carJobInvoiceService.StatusCheck(job);

        assertTrue(statusOk);
    }

    @Test
    public void testGetOperationDescriptins(){

        CarJob job = new CarJob();
        job.setId(12L);

        Operation operation = new Operation();
        operation.setDescription("testOperation1");

        Operation operationB = new Operation();
        operationB.setDescription("testOperation2");

        JobOperation jobOperation = new JobOperation();
        jobOperation.setOperation(operation);
        jobOperation.setCarJob(job);

        JobOperation jobOperationB = new JobOperation();
        jobOperationB.setOperation(operationB);
        jobOperationB.setCarJob(job);

        List<JobOperation> jobOperations = new ArrayList<>();
        jobOperations.add(jobOperation);
        jobOperations.add(jobOperationB);

        CarJobInvoiceService carJobInvoiceService = new CarJobInvoiceService();

        assertEquals(2, carJobInvoiceService.getOperationsDescriptions(jobOperations).size());
        assertEquals("testOperation2", carJobInvoiceService.getOperationsDescriptions(jobOperations).get(1));
    }

    @Test
    public void testGetPartDescriptions(){
        CarJob job = new CarJob();
        job.setId(12L);

        Part part = new Part();
        part.setDescription("testPart1");
       // part.setPrice(100.00);
      // part.setQuantity(2L);
      //  part.setId(13L);

        JobPart jobPart = new JobPart();
      //  jobPart.setCarJob(job);
        jobPart.setPart(part);

        List<JobPart> jobParts = new ArrayList<>();
        jobParts.add(jobPart);

        CarJobInvoiceService carJobInvoiceService = new CarJobInvoiceService();

        assertEquals("testPart1", carJobInvoiceService.getPartDescriptions(jobParts).get(0));
    }
    @Test
    public void testCalculateOperationsCharge(){

        CarJob job = new CarJob();

        Operation operation = new Operation();
     //   operation.setDescription("testOperation1");
        operation.setPrice(50.50);
   //     operation.setId(14L);

        JobOperation jobOperation = new JobOperation();
        jobOperation.setOperation(operation);
     //   jobOperation.setCarJob(job);

        List<JobOperation> jobOperations = new ArrayList<>();
        jobOperations.add(jobOperation);

        CarJobInvoiceService carJobInvoiceService = new CarJobInvoiceService();
        double expect = 50.50 * 1.21;
        assertEquals(expect, carJobInvoiceService.calculateOperationsCharge(jobOperations));
    }

    @Test
    public void testCalculatePartsCharge(){

        Part part = new Part();
        part.setPrice(50.00);

        Part part2 = new Part();
        part2.setPrice(25.50);

        JobPart jobPart = new JobPart();
        jobPart.setPart(part);
        jobPart.setQuantity(2L);

        JobPart jobPart2 =new JobPart();
        jobPart2.setPart(part2);
        jobPart2.setQuantity(2L);

        List<JobPart> jobParts = new ArrayList<>();
        jobParts.add(jobPart);
        jobParts.add(jobPart2);

     //   CarJobInvoiceService carJobInvoiceService = new CarJobInvoiceService();
        double expected = (2*25.50)*1.21 + (2*50)*1.21;
        assertEquals(expected, carJobInvoiceService.calculatePartsCharge(jobParts));
    }

        @Test
        public void testGetCarJobFromOptionalInput(){

        CarJob carJob = new CarJob();
        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setTelephone("1234");
        customer.setEmail("jansen@mail");
        carJob.setCustomer(customer);
        Car car = new Car();
        car.setLicensePlate("123AS45");
        carJob.setCar(car);
        Long id  = 15L;
        carJob.setId(id);
        carJob.setRemarks("test");

     //   CarJobInvoiceService carJobInvoiceService = new CarJobInvoiceService();



            Mockito
                    .doReturn(Optional.of(carJob)).when(carJobRepository).findById(id);


        assertEquals(carJob, carJobInvoiceService.getCarJobFromOptionalInput(  id, "jansen", "1234", "jansen@mail","123AS45"));

        }






}
