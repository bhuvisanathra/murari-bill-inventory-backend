package com.billapp.billapp.entities;


import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "client_details")
public class clientDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_address")
    private String clientAddress;
    
    @Column(name = "clientgst")
    private String clientGST;
    
    @Column(name = "clientpos")
    private String clientPOS;
    
    @Column(name = "client_state")
    private String clientState;
    
    @Column(name = "client_state_code")
    private String clientStateCode;
    
    @Column(name = "invoiceDate")
    private Date invoiceDate;

    @JsonIgnore
    @OneToOne(mappedBy = "clientDetails", cascade = CascadeType.ALL)
    private invoiceDetails invoiceDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "clientDetails", cascade = CascadeType.ALL)
    private List<invoiceList> invoices;

    // Default constructor
    public clientDetails() {
    }
    
    public clientDetails(Long id, String ClientName, String ClientAddress, String clientGST, String clientPOS, String clientState, String clientStateCode, Date invoiceDate) {
        this.id = id;
        this.clientName = ClientName;
        this.clientAddress = ClientAddress;
        this.clientGST = clientGST;
        this.clientPOS = clientPOS;
        this.clientState = clientState;
        this.clientStateCode = clientStateCode;
        this.invoiceDate = invoiceDate;
    }

    public clientDetails(Map<String, Object> map) {
        this.clientName = (String) map.get("clientName");
        this.clientAddress = (String) map.get("clientAddress");
        this.clientGST = (String) map.get("clientGst");
        this.clientPOS = (String) map.get("clientPos");
        this.clientState = (String) map.get("clientState");
        this.clientStateCode = (String) map.get("clientStateCode");
        Object invoiceDateValue = map.get("invoiceDate");
        if (invoiceDateValue != null) {
            this.invoiceDate = Date.valueOf((String) invoiceDateValue);
        } else {
            this.invoiceDate = null;
        }
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientName() {
        return this.clientName;
    }

    public void setClientName(String ClientName) {
        this.clientName = ClientName;
    }

    public String getClientAddress() {
        return this.clientAddress;
    }

    public void setClientAddress(String ClientAddress) {
        this.clientAddress = ClientAddress;
    }

    public String getClientGST() {
        return this.clientGST;
    }

    public void setClientGST(String clientGST) {
        this.clientGST = clientGST;
    }

    public String getClientPOS() {
        return this.clientPOS;
    }

    public void setClientPOS(String clientPOS) {
        this.clientPOS = clientPOS;
    }

    public String getClientState() {
        return this.clientState;
    }

    public void setClientState(String clientState) {
        this.clientState = clientState;
    }

    public String getClientStateCode() {
        return this.clientStateCode;
    }

    public void setClientStateCode(String clientStateCode) {
        this.clientStateCode = clientStateCode;
    }

    public Date getClientDate() {
        return this.invoiceDate;
    }

    public void setClientDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public invoiceDetails getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(invoiceDetails invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", clientName='" + getClientName() + "'" +
            ", clientAddress='" + getClientAddress() + "'" +
            ", clientGST='" + getClientGST() + "'" +
            ", clientPOS='" + getClientPOS() + "'" +
            ", clientState='" + getClientState() + "'" +
            ", clientStateCode='" + getClientStateCode() + "'" +
            ", clientDate='" + getClientDate() + "'" +
            ", invoiceDetails='" + getInvoiceDetails() + "'" +
            "}";
    }
    
    public List<invoiceList> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<invoiceList> invoices) {
        this.invoices = invoices;
    }

}
