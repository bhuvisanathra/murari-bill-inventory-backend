package com.billapp.billapp.dto;

import com.billapp.billapp.entities.clientDetails;
import com.billapp.billapp.entities.invoiceDetails;
import com.billapp.billapp.entities.invoiceList;

import java.util.List;

public class InvoiceDetailsDTO {
    private clientDetails cd;
    private invoiceDetails id;
    private List<invoiceList> il;


    public InvoiceDetailsDTO() {
    }


    public clientDetails getCd() {
        return this.cd;
    }

    public void setCd(clientDetails cd) {
        this.cd = cd;
    }

    public invoiceDetails getId() {
        return this.id;
    }

    public void setId(invoiceDetails id) {
        this.id = id;
    }

    public List<invoiceList> getIl() {
        return this.il;
    }

    public void setIl(List<invoiceList> il) {
        this.il = il;
    }


}
