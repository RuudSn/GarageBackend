package nl.ruud.Eindopdracht.model;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carjobs")
public class CarJob {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private CarJobStatus status;

   // @JsonFormat(pattern="yyyy-MM-dd HH:mm")

    private LocalDateTime repairDate;

    private String remarks;

    @OneToMany(mappedBy = "carJob", cascade = CascadeType.ALL)
    private List<JobOperation> operations;

    @OneToMany(mappedBy = "carJob", cascade = CascadeType.ALL)
    private List<JobPart> parts;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id")

    private Car car;


    public CarJob() {
    }

    public CarJob(Long id,CarJobStatus status, LocalDateTime repairDate, String remarks,
               List<JobOperation> operations, List<JobPart> parts, Customer customer,Car car) {
        this.id = id;
        this.status = status;
        this.repairDate = repairDate;
        this.remarks = remarks;
        this.operations = operations;
        this.parts = parts;
        this.customer = customer;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarJobStatus getStatus() {
        return status;
    }

    public void setStatus(CarJobStatus status) {
        this.status = status;
    }

    public LocalDateTime getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(LocalDateTime repairDate) {
        this.repairDate = repairDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<JobPart> getParts() {
        return parts;
    }

    public void setParts(List<JobPart> parts) {
        this.parts = parts;
    }

    public List<JobOperation> getOperations() {
        return operations;
    }

    public void setOperations(List<JobOperation> operations) {
        this.operations = operations;
    }


}
