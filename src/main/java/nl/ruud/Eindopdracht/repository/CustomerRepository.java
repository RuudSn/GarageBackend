package nl.ruud.Eindopdracht.repository;

import nl.ruud.Eindopdracht.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

        Optional<Customer> findCustomerByNameIgnoreCase(String name);
        Optional<Customer> findCustomerByEmail(String email);
        Customer findByNameAndTelephone(String customerName, String telephone);




}
