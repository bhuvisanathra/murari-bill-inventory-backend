package com.billapp.billapp.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import com.billapp.billapp.dto.InvoiceDetailsDTO;
import com.billapp.billapp.entities.*;
import com.billapp.billapp.services.*;

@RestController
@CrossOrigin(origins="http://localhost:5173/")
public class BillController {

    @Autowired
    private invoiceServices invoiceServices; 
    
    // Display all the bills
    
    @GetMapping("/invoices")
    public List<InvoiceDetailsDTO> getInvoices(){
        return this.invoiceServices.getInvoiceDetails();
    }

    // Get invoice by id
    @GetMapping("/invoices/{invoiceId}")
    public clientDetails getInvoiceDetail(@PathVariable int invoiceId){
            return this.invoiceServices.getInvoiceDetail(invoiceId);
    }

    @PostMapping("/invoices")
    public void addInvoiceDetail(@RequestBody Map<String, Object> payload) {

        Map<String, Object> clientDetailsMap = (Map<String, Object>) payload.get("clientDetails");
        Map<String, Object> invoiceDetailsMap = (Map<String, Object>) payload.get("invoiceDetails");
        
        clientDetails clientDetails = new clientDetails(clientDetailsMap);
        invoiceDetails invoiceDetails = new invoiceDetails(invoiceDetailsMap);
        List<Map<String, Object>> invoiceListMap = (List<Map<String, Object>>) payload.get("invoiceList");
        
        // System.out.println(invoiceListMap);
        
        List<invoiceList> invoiceList = new ArrayList<>();
        
        if (invoiceListMap != null) {
            for (Map<String, Object> item : invoiceListMap) {
                invoiceList.add(new invoiceList(item));
            }
        }
        
        this.invoiceServices.saveInvoiceDetails(clientDetails, invoiceDetails, invoiceList);
    }   


    //Update the invoice
    @PutMapping("/invoices")
    public clientDetails updateInvoiceDetail(@RequestBody clientDetails InvoiceDetail){
        return this.invoiceServices.updateInvoiceDetail(InvoiceDetail);
    }

    
    //Delete the invoice
    @DeleteMapping("/invoices/{invoiceId}")
    public void deleteInvoiceDetail(@PathVariable int invoiceId){
        this.invoiceServices.deleteInvoiceDetail(invoiceId);
    }
}
