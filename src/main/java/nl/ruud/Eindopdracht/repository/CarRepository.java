package nl.ruud.Eindopdracht.repository;

import nl.ruud.Eindopdracht.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {

    Boolean existsByLicensePlate(String licensePlate);

    Optional<Car> findByLicensePlate(String licensePlate);


}
