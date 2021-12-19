package nl.ruud.Eindopdracht.dto;

import nl.ruud.Eindopdracht.model.JobPart;

public class JobPartInputDto {

    private Long partId;

    private Long jobId;

    private double quantity;





    public Long getPartId() {
        return partId;
    }

    public void setPartId(Long partId) {
        this.partId = partId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
