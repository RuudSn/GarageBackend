package nl.ruud.Eindopdracht.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JobOperationID implements Serializable{

    @Column(name="operation_id")
    private Long operationId;

    @Column(name="carjob_id")
    private Long carJobId;


    public JobOperationID() {
    }

    public JobOperationID(Long operationId, Long carJobId) {
        this.operationId = operationId;
        this.carJobId = carJobId;
    }

    public Long getOperationId() {
        return operationId;
    }

    public void setOperationId(Long operationId) {
        this.operationId = operationId;
    }

    public Long getCarJobId() {
        return carJobId;
    }

    public void setCarJobId(Long carJobId) {
        this.carJobId = carJobId;
    }
}
