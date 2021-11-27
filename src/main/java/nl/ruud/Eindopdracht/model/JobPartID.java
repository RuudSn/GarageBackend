package nl.ruud.Eindopdracht.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JobPartID implements Serializable {

    @Column(name="carjob_id")
    private Long carJobId;

    @Column(name="part_id")
    private Long partId;


    public JobPartID() {
    }

    public JobPartID(Long carJobId, Long partId) {
        this.carJobId = carJobId;
        this.partId = partId;
    }

    public Long getCarJobId() {
        return carJobId;
    }

    public void setCarJobId(Long carJobId) {
        this.carJobId = carJobId;
    }

    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }



}
