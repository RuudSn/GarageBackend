package nl.ruud.Eindopdracht.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "carjobinvoices")
public class CarJobInvoice {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String customerName;

    private String remarks;

    @ElementCollection
    private List<String> operationDescriptions;

    private double operationsCharge;

    @ElementCollection
    private List<String> partDescriptions;

    private double partsCharge;

    private double totalCharge;


    public CarJobInvoice() {
    }



    public CarJobInvoice( String customerName, String remarks, List<String> operationDescriptions,
                         double operationsCharge,List<String> partDescriptions,
                                                            double partsCharge, double totalCharge) {
        this.customerName = customerName;
        this.remarks = remarks;
        this.operationDescriptions = operationDescriptions;
        this.operationsCharge = operationsCharge;
        this.partDescriptions = partDescriptions;
        this.partsCharge = partsCharge;
        this.totalCharge = totalCharge;
    }


    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<String> getOperationDescriptions() {
        return operationDescriptions;
    }

    public void setOperationDescriptions(List<String> operationDescriptions) {
        this.operationDescriptions = operationDescriptions;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public double getOperationsCharge() {
        return operationsCharge;
    }

    public void setOperationsCharge(double operationsCharge) {
        this.operationsCharge = operationsCharge;
    }

    public List<String> getPartDescriptions() {
        return partDescriptions;
    }

    public void setPartDescriptions(List<String> partDescriptions) {
        this.partDescriptions = partDescriptions;
    }

    public double getPartsCharge() {
        return partsCharge;
    }

    public void setPartsCharge(double partsCharge) {
        this.partsCharge = partsCharge;
    }

    public double getTotalCharge() {
        return totalCharge;
    }

    public void setTotalCharge(double totalCharge) {
        this.totalCharge = totalCharge;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
