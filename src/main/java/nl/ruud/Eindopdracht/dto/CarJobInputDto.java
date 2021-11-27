package nl.ruud.Eindopdracht.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import nl.ruud.Eindopdracht.model.CarJob;
import nl.ruud.Eindopdracht.model.CarJobStatus;

import java.time.LocalDateTime;


public class CarJobInputDto {


    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern="yyyy-MM-dd HH:mm")
    private LocalDateTime repairDate;

    private CarJobStatus status;

    private String remarks;

    private Long customerId;

    private Long carId;


    public CarJobInputDto() {
    }


    public CarJob toCarJob(CarJobInputDto Dto) {
        CarJob carJob = new CarJob();
        carJob.setStatus(Dto.getStatus());
        carJob.setRepairDate(Dto.getRepairDate());
        carJob.setRemarks(Dto.getRemarks());
        return carJob;
    }


    public Long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public LocalDateTime getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDateTime repairDate) {
        this.repairDate = repairDate;
    }

    public CarJobStatus getStatus() {
        return status;
    }

    public void setStatus(CarJobStatus status) {
        this.status = status;
    }
}
