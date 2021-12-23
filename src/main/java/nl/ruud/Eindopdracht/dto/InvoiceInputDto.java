package nl.ruud.Eindopdracht.dto;

import nl.ruud.Eindopdracht.model.CarJobInvoice;

public class InvoiceInputDto {


    public Long carJobId;

    public String customerName;

    public String telephone;

    public String email;

    public String licensePlate;


    
//Niet nodig,kan weg
    public CarJobInvoice toInvoice(){
        CarJobInvoice invoice = new CarJobInvoice();
        invoice.setCustomerName(customerName);

        return invoice;
    }


}
