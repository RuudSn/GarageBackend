package nl.ruud.Eindopdracht.repository;

import nl.ruud.Eindopdracht.model.CarJob;
import nl.ruud.Eindopdracht.model.CarJobStatus;
import nl.ruud.Eindopdracht.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarJobRepository extends JpaRepository<CarJob, Long> {

    List<CarJob> findByStatus(CarJobStatus status);
    CarJob findByCarLicensePlate(String licensePlate);
    CarJob findByCustomerNameAndCustomerEmail(String name, String email);
    CarJob findByCustomerNameAndCustomerTelephone(String name, String telephone);


}
