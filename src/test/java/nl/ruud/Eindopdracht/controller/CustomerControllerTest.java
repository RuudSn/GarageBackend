package nl.ruud.Eindopdracht.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.ruud.Eindopdracht.GarageBackendApplication;
import nl.ruud.Eindopdracht.dto.CustomerInputDto;
import nl.ruud.Eindopdracht.model.Customer;
import nl.ruud.Eindopdracht.service.CustomerService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.Arrays;
import java.util.List;


import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ContextConfiguration(classes= {GarageBackendApplication.class})
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CustomerService customerService;


    @Test
    public void testHTTPRequestMatching() throws Exception{

        Customer customer = new Customer("testnaam", "test@mail", "test123");
        customer.setName("jansen");
        customer.setEmail("jansen@mail");
        customer.setTelephone("1234");
        customer.setId(12L);

        List<Customer> allCustomers = Arrays.asList(customer);

        given(customerService.getCustomers()).willReturn(allCustomers);

        mvc.perform(get( "/customers")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].name", is(customer.getName())));

    }



    @Test
    void testInputSerialization() throws Exception{

        CustomerInputDto dto = new CustomerInputDto("testnaam", "test@mail", "test123");

        mvc.perform(post("/customers", 42L)
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(dto)))
             .andExpect(status().isOk());

    }

                 // test servicelayer call,



}
