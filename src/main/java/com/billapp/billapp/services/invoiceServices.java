package com.billapp.billapp.services;
import java.util.List;

import com.billapp.billapp.dto.InvoiceDetailsDTO;
import com.billapp.billapp.entities.*;


public interface invoiceServices {
    public List<InvoiceDetailsDTO> getInvoiceDetails();
    public InvoiceDetailsDTO getInvoiceDetail(int invoiceId);
    public clientDetails addInvoiceDetail(clientDetails InvoiceDetail);
    public clientDetails updateInvoiceDetail(clientDetails InvoiceDetail);
    public void deleteInvoiceDetail(int invoiceId);
    public void saveInvoiceDetails(clientDetails clientDetails, invoiceDetails invoiceDetails, List<invoiceList> invoiceList);


}
