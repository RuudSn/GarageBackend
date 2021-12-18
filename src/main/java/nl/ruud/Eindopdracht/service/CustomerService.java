package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.dto.CustomerDto;
import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.CarJob;
import nl.ruud.Eindopdracht.model.CarJobStatus;
import nl.ruud.Eindopdracht.model.Customer;
import nl.ruud.Eindopdracht.repository.CarJobRepository;
import nl.ruud.Eindopdracht.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

    @Service
    public class CustomerService {

        private CustomerRepository customerRepository;

        private CarJobRepository carJobRepository;

        @Autowired
        public CustomerService(CustomerRepository customerRepository, CarJobRepository carJobRepository){
            this.customerRepository = customerRepository;
            this.carJobRepository = carJobRepository;
        }



        public List<Customer> getCustomers(){
            List<Customer> customers = customerRepository.findAll();
            return  customers;
        }

    public Customer getCustomerById(long id) {
        if (customerRepository.existsById(id)) {
            Customer customer = customerRepository.findById(id).get();
            return customer;
        } else {
            throw new RecordNotFoundException("Unknown customerId"); }
    }

    public Customer getCustomerByName(String name) {
        Optional<Customer> customerOpt = customerRepository.findCustomerByNameIgnoreCase(name);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            return customer;
        } else {
            throw new RecordNotFoundException(); }
    }


    public long addCustomer(Customer customer) {
        Customer addedCustomer = customerRepository.save(customer);
        return addedCustomer.getId();

    }


    public void removeCustomerById(long id) {
        if(customerRepository.existsById(id)){
            customerRepository.deleteById(id);}
        else{throw new RecordNotFoundException();}
    }

    public void updateCustomerById(long id, Customer customer) {
            if (customerRepository.existsById(id)) {
                Customer knownCustomer = customerRepository.findById(id).get();
                knownCustomer.setName(customer.getName());
                knownCustomer.setEmail(customer.getEmail());
                knownCustomer.setTelephone(customer.getTelephone());
                customerRepository.save(knownCustomer);
            } else {
                throw new RecordNotFoundException();
            }
        }







}
