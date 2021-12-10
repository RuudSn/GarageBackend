package nl.ruud.Eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="jobparts")
public class JobPart {


    @EmbeddedId
    private JobPartID ID;

    @ManyToOne
    @MapsId("partId")
    @JoinColumn(name = "part_id")
    @JsonIgnore
    private Part part;


    @ManyToOne
    @MapsId("carJobId")
    @JoinColumn(name = "carjob_id")
    @JsonIgnore
    private CarJob carJob;

    private double quantity;


    public JobPart() {
    }


    public JobPart( CarJob carJob, double quantity) {
        this.part = part;
        this.carJob = carJob;
        this.quantity = quantity;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public CarJob getCarJob() {
        return carJob;
    }

    public void setCarJob(CarJob carJob) {
        this.carJob = carJob;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public JobPartID getID() {
        return ID;
    }

    public void setID(JobPartID ID) {
        this.ID = ID;
    }
}
