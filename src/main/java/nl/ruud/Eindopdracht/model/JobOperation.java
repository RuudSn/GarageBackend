package nl.ruud.Eindopdracht.model;

import javax.persistence.*;

@Entity
@Table(name="joboperations")
public class JobOperation {

    @EmbeddedId
    private JobOperationID ID;

    @ManyToOne
    @MapsId("operationId")
    @JoinColumn(name = "operation_id")
    private Operation operation;


    @ManyToOne
    @MapsId("carJobId")
    @JoinColumn(name = "carjob_id")
    private CarJob carJob;

    //private double quantity;  wel description/remarks?



    public JobOperation() {
    }
    public JobOperation(JobOperationID ID, Operation operation, CarJob carJob) {
        this.ID = ID;
        this.operation =  operation;
        this.carJob = carJob;

    }


    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public CarJob getCarJob() {
        return carJob;
    }

    public void setCarJob(CarJob carJob) {
        this.carJob = carJob;
    }


    public JobOperationID getID() {
        return ID;
    }

    public void setID(JobOperationID ID) {
        this.ID = ID;
    }
}
