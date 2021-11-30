package nl.ruud.Eindopdracht.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "parts")
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private double price;

    private Long quantity;


    @OneToMany(mappedBy = "part")
    private List<JobPart> parts;



    public Part() {
    }

    public Part(Long id, String description, double price, Long quantity, List<JobPart> parts) {
        this.id = id;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.parts = parts;
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
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<JobPart> getParts() {
        return parts;
    }

    public void setParts(List<JobPart> parts) {
        this.parts = parts;
    }
}
