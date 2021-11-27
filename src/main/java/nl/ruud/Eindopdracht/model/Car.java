package nl.ruud.Eindopdracht.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name="cars")
public class Car {

    @Id
    @GeneratedValue
    private Long id;
    private String licensePlate;
    private String type;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private Customer customer;

    @OneToMany(mappedBy = "car")
    private List<CarJob> carJobs;

    public Car() {
    }

    public Car(Long id,String licensePlate, String type) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.type = type;
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CarJob> getCarJobs() {
        return carJobs;
    }

    public void setCarJobs(List<CarJob> carJobs) {
        this.carJobs = carJobs;
    }
}
