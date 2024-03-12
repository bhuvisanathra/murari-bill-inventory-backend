package com.billapp.billapp.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.billapp.billapp.dto.InvoiceDetailsDTO;
import com.billapp.billapp.services.*;

@RestController
@CrossOrigin(origins = { "http://localhost:5173", "https://muraribhuvi.netlify.app" })
public class BillController {

    @Autowired
    private invoiceServices invoiceServices;

    // Display all the bills

    @GetMapping("/invoices")
    public List<InvoiceDetailsDTO> getInvoices() {
        return this.invoiceServices.getInvoiceDetails();
    }

    // Get invoice by id
    @GetMapping("/invoices/{invoiceId}")
    public InvoiceDetailsDTO getInvoiceDetail(@PathVariable int invoiceId) {
        return this.invoiceServices.getInvoiceDetail(invoiceId);
    }

    @PostMapping("/invoices")
    public ResponseEntity<Map<String, Object>> addInvoiceDetail(@RequestBody Map<String, Object> payload) {
        return ResponseEntity.ok(this.invoiceServices.saveInvoiceDetails(payload));
    }

    // Update the invoice
    @PutMapping("/invoices/{clientId}")
    public ResponseEntity<Long> updateInvoiceDetail(@PathVariable Long clientId,
            @RequestBody Map<String, Object> payload) {
        System.out.println("\n\n\nPayload" + payload + "\n\n\n");
        return ResponseEntity.ok(this.invoiceServices.updateInvoiceDetail(clientId, payload));
    }

    // Delete the invoice
    @DeleteMapping("/invoices/{invoiceId}")
    public void deleteInvoiceDetail(@PathVariable Long invoiceId) {
        this.invoiceServices.deleteInvoiceDetail(invoiceId);
    }
}
