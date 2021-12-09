package nl.ruud.Eindopdracht.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarJobInvoiceTest {

    private CarJobInvoice invoice;

    @BeforeEach
    void setUp(){

        List<String> operationDescriptions = new ArrayList<>();
        operationDescriptions.add("test1");
        operationDescriptions.add("test2");
        List<String> partDescriptions = new ArrayList<>();
        partDescriptions.add("part1");
        partDescriptions.add("part2");
        this.invoice = new CarJobInvoice("jansen", "test", operationDescriptions,
                50.00,partDescriptions,100.50, 160.00);
    }

    @Test
    void testGetCustomerName(){
        String expect = "jansen";
        String name = this.invoice.getCustomerName();
        assertEquals(expect, name);
    }

    @Test
    void testGetRemarks(){
        String expect="test";
        String remarks = this.invoice.getRemarks();
        assertEquals(expect, remarks);
    }

    @Test
    void testGetOperationDescriptions(){
        List<String> expect = Arrays.asList("test1", "test2");
        List<String> descript = this.invoice.getOperationDescriptions();
        assertEquals(expect, descript);
    }

    @Test
    void testGetPartDescriptions(){
        List<String> expect = Arrays.asList("part1", "part2");
        List<String> descript = this.invoice.getPartDescriptions();
        assertEquals(expect, descript);
    }

    @Test
    void testGetOperationsCharge(){
       double expect = 50.00;
       double charge = this.invoice.getOperationsCharge();
       assertEquals(expect, charge);
    }

    @Test
    void testGetPartsCharge(){
        double expect = 100.50;
        double charge = this.invoice.getPartsCharge();
        assertEquals(expect, charge);
    }

    @Test
    void testGetTotalCharge(){
        double expect = 160.00;
        double total = this.invoice.getTotalCharge();
        assertEquals(expect, total);
    }

    @Test
    void testSetPartDescriptions(){
        List<String> newdescr = Arrays.asList("part3", "part4");
        this.invoice.setPartDescriptions(newdescr);
        List<String> expect = Arrays.asList("part3", "part4");
        List<String> descript = this.invoice.getPartDescriptions();
        assertEquals(expect, descript);

    }

    @Test
    void testSetTotalCharge(){
        double newtotal = 300.25;
        double expect = 300.25;
        this.invoice.setTotalCharge(300.25);
        double total = this.invoice.getTotalCharge();
        assertEquals(expect, total );
    }




}
