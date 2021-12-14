package nl.ruud.Eindopdracht.service;


import nl.ruud.Eindopdracht.GarageBackendApplication;
import nl.ruud.Eindopdracht.model.Customer;
import nl.ruud.Eindopdracht.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest()
@ContextConfiguration(classes= {GarageBackendApplication.class})
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @Test
    public void testAddCustomer(){

        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jansen@mail");
        customer.setTelephone("1234");
        customer.setId(12L);


        Mockito
                .when(customerRepository.save(customer))
                .thenReturn(customer);

        Long expect = customer.getId();
        Long found = customerService.addCustomer(customer);

        assertEquals(expect, found);
    }

    @Test
    public void testGetCustomerById(){

        Customer customer = new Customer();
        customer.setName("jansen");
        customer.setEmail("jansen@mail");
        customer.setTelephone("1234");
        customer.setId(12L);

        Mockito
               .doReturn(Optional.of(customer)).when(customerRepository).findById(12L);

        Customer expect = customer;
        Customer found = customerService.getCustomerById(12L);

        assertEquals(expect, found);
    }













}
