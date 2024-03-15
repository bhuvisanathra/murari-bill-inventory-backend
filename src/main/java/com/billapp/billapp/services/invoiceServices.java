package com.billapp.billapp.services;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.billapp.billapp.dto.InvoiceDetailsDTO;
import com.billapp.billapp.entities.*;

public interface invoiceServices {
    public List<InvoiceDetailsDTO> getInvoiceDetails();

    public InvoiceDetailsDTO getInvoiceDetail(int invoiceId);

    public List<InvoiceDetailsDTO> getBillAnalysis();

    public List<InvoiceDetailsDTO> getProductAnalysis();
    public List<InvoiceDetailsDTO> getSaleAnalysis();


    public clientDetails addInvoiceDetail(clientDetails InvoiceDetail);

    public Long updateInvoiceDetail(Long ClientId, Map<String, Object> payload);

    public void deleteInvoiceDetail(Long invoiceId);

    public Map<String, Object> saveInvoiceDetails(@RequestBody Map<String, Object> payload);
}
