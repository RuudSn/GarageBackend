package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.GarageBackendApplication;
import nl.ruud.Eindopdracht.dto.InvoiceInputDto;
import nl.ruud.Eindopdracht.model.*;
import nl.ruud.Eindopdracht.repository.CarJobInvoiceRepository;
import nl.ruud.Eindopdracht.repository.CarJobRepository;
import nl.ruud.Eindopdracht.repository.JobOperationRepository;
import nl.ruud.Eindopdracht.repository.JobPartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
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


@ExtendWith(MockitoExtension.class)
public class CarJobInvoiceServiceTest {

    @Mock
    CarJobRepository carJobRepository;

    @Mock
    CarJobInvoiceRepository carJobInvoiceRepository;

    @Mock
    JobOperationRepository jobOperationRepository;

    @Mock
    JobPartRepository jobPartRepository;

    @InjectMocks
    CarJobInvoiceService carJobInvoiceService;

    @BeforeEach
    void setup() {
        CarJobInvoiceService carJobInvoiceService;
    }


    @Captor
    ArgumentCaptor<CarJobInvoice> carJobInvoiceCaptor;


    @Test
    public void testGetCarJobInvoices(){
        CarJobInvoice carJobInvoice = new CarJobInvoice();
        CarJobInvoice carJobInvoice2 = new CarJobInvoice();

        List<CarJobInvoice> invoices = new ArrayList<>();
        invoices.add(carJobInvoice);
        invoices.add(carJobInvoice2);

        Mockito
                .doReturn(invoices).when(carJobInvoiceRepository).findAll();

        assertEquals(2, carJobInvoiceService.getInvoices().size());
        assertEquals(carJobInvoice2,carJobInvoiceService.getInvoices().get(1));

    }

    @Test
    public void testInvoiceById(){
        CarJobInvoice carJobInvoice = new CarJobInvoice();
        carJobInvoice.setId(1L);

        Mockito
                .when(carJobInvoiceRepository.existsById(1L))
                .thenReturn(true);
        Mockito
                .doReturn(Optional.of(carJobInvoice)).when(carJobInvoiceRepository).findById(1L);

        assertEquals(carJobInvoice, carJobInvoiceService.getInvoiceById(1L));
    }

    @Test
    public void testAddCarJobInvoiceShouldReturnAnInvoice(){

        CarJob job = new CarJob();
        Long carJobId = 12L;
        job.setId(carJobId);
        job.setStatus(CarJobStatus.COMPLETED);

        Operation operation = new Operation();
        operation.setDescription("testOperation");




        JobOperation jobOperation = new JobOperation();
        jobOperation.setOperation(operation);
        jobOperation.setCarJob(job);
        List<JobOperation> jobOperations = new ArrayList<>();
        jobOperations.add(jobOperation);


        Part part = new Part();
        part.setDescription("testPart1");
        part.setPrice(100.00);

        Part part2 = new Part();
        part2.setPrice(25.50);
        part2.setDescription("testPart2");


        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jans@mail");
        customer.setTelephone("1234");
        customer.setId(15L);

        Car car = new Car();
        car.setType("auto");
        car.setLicensePlate("12AB34");
        car.setCustomer(customer);

        job.setCustomer(customer);
        job.setCar(car);

        JobPart jobPart = new JobPart();
        jobPart.setCarJob(job);
        jobPart.setPart(part);
        jobPart.setQuantity(1L);

        JobPart jobPart2 =new JobPart();
        jobPart2.setCarJob(job);
        jobPart2.setPart(part2);
        jobPart2.setQuantity(2L);

        List<JobPart> jobParts = new ArrayList<>();
        jobParts.add(jobPart);
        jobParts.add(jobPart2);




        Mockito
                .doReturn(job).when(carJobRepository).findByCustomerNameAndCustomerEmail("jansen","jans@mail" );
        Mockito
                .doReturn(jobOperations).when(jobOperationRepository).findAllByCarJobId(carJobId);

        Mockito
                .doReturn(jobParts).when(jobPartRepository).findAllByCarJobId(carJobId);
     //   Mockito
      //          .doReturn(carJobInvoice).when(carJobInvoiceRepository).save(carJobInvoice);


       carJobInvoiceService.addCarJobInvoice(null, "jansen", null, "jans@mail", null);


        Mockito
                .verify(carJobInvoiceRepository).save(carJobInvoiceCaptor.capture());

        CarJobInvoice carJobInvoice1 = carJobInvoiceCaptor.getValue();
        double expect = (2*25.50)*1.21 + (1*100)*1.21;


        assertEquals("jansen", carJobInvoice1.getCustomerName() );
        assertEquals(expect, carJobInvoice1.getPartsCharge() );
        assertEquals("testOperation", carJobInvoice1.getOperationDescriptions().get(0));
    }

    @Test
    public void givenCarJobWithStatusCOMPLETEDOrDONOTEXECUTEShouldReturnTrue(){

        CarJob job = new CarJob();
        job.setStatus(CarJobStatus.DONOTEXECUTE);

        boolean statusOk = carJobInvoiceService.StatusCheck(job);

        assertTrue(statusOk);
    }

    @Test
    public void testGetOperationDescriptions(){

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

        List<JobOperation> jobOperations = new ArrayList<>();
        jobOperations.add(jobOperation);
        jobOperations.add(jobOperationB);

        assertEquals(2, carJobInvoiceService.getOperationsDescriptions(jobOperations).size());
        assertEquals("testOperation2", carJobInvoiceService.getOperationsDescriptions(jobOperations).get(1));
    }

    @Test
    public void testGetPartDescriptions(){
        CarJob job = new CarJob();
        job.setId(12L);

        Part part = new Part();
        part.setDescription("testPart1");

        JobPart jobPart = new JobPart();
        jobPart.setPart(part);

        List<JobPart> jobParts = new ArrayList<>();
        jobParts.add(jobPart);

        assertEquals("testPart1", carJobInvoiceService.getPartDescriptions(jobParts).get(0));
    }
    @Test
    public void testCalculateOperationsCharge(){
        CarJob job = new CarJob();

        Operation operation = new Operation();
        operation.setPrice(50.50);

        JobOperation jobOperation = new JobOperation();
        jobOperation.setOperation(operation);

        List<JobOperation> jobOperations = new ArrayList<>();
        jobOperations.add(jobOperation);
        
        double expect = 50.50 * 1.21;               // incl 21% VAT
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

        double expected = (2*25.50)*1.21 + (2*50)*1.21;      // incl. 21% VAT
        assertEquals(expected, carJobInvoiceService.calculatePartsCharge(jobParts));
    }

        @Test
        public void testGetCarJobFromOptionalInputCarJobId(){
        CarJob carJob = new CarJob();Customer customer = new Customer();
        carJob.setId(1L);

            Mockito
                    .doReturn(Optional.of(carJob)).when(carJobRepository).findById(1L);
            Mockito
                    .when(carJobRepository.existsById(1L))
                    .thenReturn(true);

          CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  1L, "jansen", null, "null",null);

        assertEquals(carJob ,found);

        }

    @Test
    public void testGetCarJobFromOptionalInputNameAndEmail(){

        CarJob carJob = new CarJob();
        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jansen@mail");
        carJob.setCustomer(customer);

        Mockito
                .doReturn(carJob).when(carJobRepository).findByCustomerNameAndCustomerEmail("jansen","jansen@mail" );

        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  null, "jansen", null, "jansen@mail",null);

        assertEquals(carJob ,found);
    }
    @Test
    public void testGetCarJobFromOptionalInputNameAndTelephone(){

        CarJob carJob = new CarJob();
        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setTelephone("1234");
        carJob.setCustomer(customer);

        Mockito
                .doReturn(carJob).when(carJobRepository).findByCustomerNameAndCustomerTelephone("jansen", "1234" );

        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  null, "jansen", "1234", null,null);

        assertEquals(carJob ,found);
    }

    @Test
    public void testGetCarJobFromOptionalInputLicensePlate(){

        CarJob carJob = new CarJob();
        Car car = new Car();
        car.setLicensePlate("123AS45");
        carJob.setCar(car);

        Mockito
                .doReturn(carJob).when(carJobRepository).findByCarLicensePlate("123AS45" );

        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  null, null, null, null,"123AS45");

        assertEquals(carJob ,found);
    }

    @Test
    public void testGetCarJobFromOptionalInputWhenTwoOptions(){

        CarJob carJob = new CarJob();
        Car car = new Car();
        car.setLicensePlate("123AS45");
        carJob.setCar(car);
        carJob.setId(1L);

        Mockito
                .doReturn(Optional.of(carJob)).when(carJobRepository).findById(1L);
        Mockito
                .when(carJobRepository.existsById(1L))
                .thenReturn(true);

        CarJob found = carJobInvoiceService.getCarJobFromOptionalInput(  1L, null, null, null,"123AS45");

        assertEquals(carJob ,found);
    }
    @Test
    public void testRemoveCarJobInvoiceById() {
        CarJobInvoice carJobInvoice = new CarJobInvoice();
        carJobInvoice.setId(1L);

        Mockito
                .when(carJobInvoiceRepository.existsById(1L))
                .thenReturn(true);

        carJobInvoiceService.removeCarJobInvoiceById(1L);
        Mockito.verify(carJobInvoiceRepository, Mockito.times(1)).deleteById(1L);

    }


}
