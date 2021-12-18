package nl.ruud.Eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="operations")
public class Operation {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String description;

    private double price;


    @OneToMany(mappedBy = "operation", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<JobOperation> operations;


    public Operation() {
    }

    public Operation(String description, double price, List<JobOperation> operations) {
        this.description = description;
        this.price = price;
        this.operations = operations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;}

    public List<JobOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<JobOperation> operations) {
        this.operations = operations;
    }




}
