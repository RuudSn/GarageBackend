package nl.ruud.Eindopdracht.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="operations")
public class Operation {

    @Id
    @GeneratedValue
    private Long id;

    private String description;

    private double price;


    @OneToMany(mappedBy = "operation")
    private List<JobOperation> operations;


    public Operation() {
    }

    public Operation(Long id, String description, double price, List<JobOperation> operations) {
        this.id = id;
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
