package nl.ruud.Eindopdracht.service;

import nl.ruud.Eindopdracht.dto.CarInputDto;
import nl.ruud.Eindopdracht.exception.RecordNotFoundException;
import nl.ruud.Eindopdracht.model.Car;
import nl.ruud.Eindopdracht.model.Customer;
import nl.ruud.Eindopdracht.repository.CarRepository;
import nl.ruud.Eindopdracht.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarService {

    private CarRepository carRepository;
    private CustomerRepository customerRepository;

    @Autowired
    public CarService(CarRepository carRepository, CustomerRepository customerRepository) {
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    public List<Car> getCars(){
        List<Car> cars = carRepository.findAll();
        return cars;
    }

    public Car getCarById(long id) {
        if(carRepository.existsById(id)){
            Car car = carRepository.findById(id).get();
            return car; }
        else {
            throw new RecordNotFoundException("Unknown ID"); }
    }

    public Optional<Car> getCarByLicensePlate(String licensePlate){
        return carRepository.findCarByLicensePlate(licensePlate);
    }


    public void removeCarById ( long id){
        if(carRepository.existsById(id)){
        carRepository.deleteById(id);
    }else {
        throw new RecordNotFoundException("Unknown ID");}
    }



    public void updateCar(long id, Car car) {
        if (carRepository.existsById(id)) {
            Car knownCar = carRepository.findById(id).get();
            knownCar.setLicensePlate(car.getLicensePlate());
            knownCar.setType(car.getType());
            knownCar.setCustomer(car.getCustomer());
            carRepository.save(knownCar);
        } else {
            throw new RecordNotFoundException("Unknown ID"); }
    }

        public long addCar (Car car){
        Car addedCar = carRepository.save(car);
        return addedCar.getId();
        }

        public void partialUpdateCar(long id, Map<String, String> fields) {
            if(carRepository.existsById(id)){
                Car car = carRepository.findById(id).get();
                for (String field : fields.keySet()) {
                    switch (field.toLowerCase()){
                        case "licensePlate" : car.setLicensePlate((String) fields.get(field));
                            break;
                        case "type" : car.setType((String) fields.get(field));
                            break; }
                }
                carRepository.save(car);
            } else {
                throw new RecordNotFoundException("Unknown ID"); }
            }




}

