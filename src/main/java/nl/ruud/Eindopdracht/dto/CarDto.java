package nl.ruud.Eindopdracht.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import nl.ruud.Eindopdracht.model.Car;

public class CarDto {



    private Long id;

    private String licensePlate;

    private String type;

    private CustomerDto customer;




    public CarDto() {
    }


    public static CarDto fromCar(Car car){
        CarDto carDto= new CarDto();
        carDto.setId(car.getId());
        carDto.setLicensePlate(car.getLicensePlate());
        carDto.setType(car.getType());
        carDto.setCustomer(CustomerDto.fromCustomer(car.getCustomer()));
        return carDto;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
}




