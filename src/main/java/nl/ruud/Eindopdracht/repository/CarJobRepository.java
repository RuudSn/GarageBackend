package nl.ruud.Eindopdracht.repository;

import nl.ruud.Eindopdracht.model.CarJob;
import nl.ruud.Eindopdracht.model.CarJobStatus;
import nl.ruud.Eindopdracht.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarJobRepository extends JpaRepository<CarJob, Long> {

    List<CarJob> findByStatus(CarJobStatus status);
    CarJob findByStatusAndCarLicensePlate(CarJobStatus status,String licensePlate);
    CarJob findByStatusAndCustomerNameAndCustomerEmail(CarJobStatus status, String name, String email);

    CarJob findByStatusAndCustomerNameAndCustomerTelephone(CarJobStatus status, String name, String telephone);


}
