package nl.ruud.Eindopdracht.controller;



import nl.ruud.Eindopdracht.dto.InvoiceInputDto;
import nl.ruud.Eindopdracht.model.CarJobInvoice;
import nl.ruud.Eindopdracht.service.CarJobInvoiceService;
import nl.ruud.Eindopdracht.service.CarJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/api/v1/carjobinvoices")
public class CarJobInvoiceController {



    private CarJobInvoiceService carJobInvoiceService;

    private CarJobService carJobService;

    @Autowired
    public CarJobInvoiceController(CarJobInvoiceService carJobInvoiceService, CarJobService carJobService) {
        this.carJobInvoiceService = carJobInvoiceService;
        this.carJobService = carJobService;
    }



    @GetMapping("")
    public ResponseEntity<Object> getInvoices() {
        List<CarJobInvoice> carJobInvoices = carJobInvoiceService.getInvoices();

        return ResponseEntity.ok().body(carJobInvoices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoiceById(@PathVariable("id") Long id ){
        return ResponseEntity.ok().body(carJobInvoiceService.getInvoiceById(id));
    }

    @PostMapping("")
    public ResponseEntity<Object> addInvoice(@RequestBody InvoiceInputDto invoiceInputDto){


        Long Id = carJobInvoiceService.addCarJobInvoice(invoiceInputDto.carJobId, invoiceInputDto.customerName,
                              invoiceInputDto.telephone, invoiceInputDto.email, invoiceInputDto.licensePlate  );

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(Id).toUri();

      return  ResponseEntity.created(location).body(location);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removeInvoice(@PathVariable("id") long id) {
        carJobInvoiceService.removeCarJobInvoiceById(id);
        return ResponseEntity.noContent().build().ok("Deleted");
    }


}


