package com.billapp.billapp.entities;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
@Table(name = "invoice_details")
public class invoiceDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="paymentType")
    private String paymentType;

    @Column(name = "grand_total")
    private Double grandTotal;

    @Column(name = "round_off")
    private Double roundOff;

    @Column(name = "shipping_charges")
    private Double shippingCharges;

    @Column(name = "total_after_discount")
    private Double totalAfterDiscount;

    @Column(name = "total_cgst")
    private Double totalCgst;

    @Column(name = "total_discount")
    private Double totalDiscount;

    @Column(name = "total_sgst")
    private Double totalSgst;

    @Column(name = "total_value")
    private Double totalValue;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "client_id")
    private clientDetails clientDetails;


    public invoiceDetails() {
    }


    public invoiceDetails(Long id, String paymentType, Double grandTotal, Double roundOff, Double shippingCharges, Double totalAfterDiscount, Double totalCgst, Double totalDiscount, Double totalSgst, Double totalValue, clientDetails clientDetails) {
        this.id = id;
        this.paymentType = paymentType;
        this.grandTotal = grandTotal;
        this.roundOff = roundOff;
        this.shippingCharges = shippingCharges;
        this.totalAfterDiscount = totalAfterDiscount;
        this.totalCgst = totalCgst;
        this.totalDiscount = totalDiscount;
        this.totalSgst = totalSgst;
        this.totalValue = totalValue;
        this.clientDetails = clientDetails;
    }

    public invoiceDetails(Map<String, Object> map) {
        this.paymentType = map.getOrDefault("paymentType", "").toString();
        this.grandTotal = map.get("grandTotal") != null ? Double.parseDouble(map.get("grandTotal").toString()) : 0.0;
        this.roundOff = map.get("roundOff") != null ? Double.parseDouble(map.get("roundOff").toString()) : 0.0;
        this.shippingCharges = map.get("shippingCharges") != null ? Double.parseDouble(map.get("shippingCharges").toString()) : 0.0;
        this.totalAfterDiscount = map.get("totalAfterDiscount") != null ? Double.parseDouble(map.get("totalAfterDiscount").toString()) : 0.0;
        this.totalCgst = map.get("totalCgst") != null ? Double.parseDouble(map.get("totalCgst").toString()) : 0.0;
        this.totalDiscount = map.get("totalDiscount") != null ? Double.parseDouble(map.get("totalDiscount").toString()) : 0.0;
        this.totalSgst = map.get("totalSgst") != null ? Double.parseDouble(map.get("totalSgst").toString()) : 0.0;
        this.totalValue = map.get("totalValue") != null ? Double.parseDouble(map.get("totalValue").toString()) : 0.0;
    }
    

    // Getters and setters

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentType() {
        return this.paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getGrandTotal() {
        return this.grandTotal;
    }

    public void setGrandTotal(Double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Double getRoundOff() {
        return this.roundOff;
    }

    public void setRoundOff(Double roundOff) {
        this.roundOff = roundOff;
    }

    public Double getShippingCharges() {
        return this.shippingCharges;
    }

    public void setShippingCharges(Double shippingCharges) {
        this.shippingCharges = shippingCharges;
    }

    public Double getTotalAfterDiscount() {
        return this.totalAfterDiscount;
    }

    public void setTotalAfterDiscount(Double totalAfterDiscount) {
        this.totalAfterDiscount = totalAfterDiscount;
    }

    public Double getTotalCgst() {
        return this.totalCgst;
    }

    public void setTotalCgst(Double totalCgst) {
        this.totalCgst = totalCgst;
    }

    public Double getTotalDiscount() {
        return this.totalDiscount;
    }

    public void setTotalDiscount(Double totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Double getTotalSgst() {
        return this.totalSgst;
    }

    public void setTotalSgst(Double totalSgst) {
        this.totalSgst = totalSgst;
    }

    public Double getTotalValue() {
        return this.totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public clientDetails getClientDetails() {
        return this.clientDetails;
    }

    public void setClientDetails(clientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

}
