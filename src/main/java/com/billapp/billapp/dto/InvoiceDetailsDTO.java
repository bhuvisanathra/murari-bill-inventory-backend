package com.billapp.billapp.dto;

import com.billapp.billapp.entities.clientDetails;
import com.billapp.billapp.entities.invoiceDetails;
import com.billapp.billapp.entities.invoiceList;

import java.util.List;

public class InvoiceDetailsDTO {

    private clientDetails cd;
    private invoiceDetails id;
    private List<invoiceList> il;

    // For product analysis
    private String productDetail;
    private Double totalValue;
    private Double totalQuantity;

    // For sale analysis
    private Long clientCount;
    private String paymentType;
    private Double totalGrandTotal;

    public clientDetails getCd() {
        return cd;
    }

    public void setCd(clientDetails cd) {
        this.cd = cd;
    }

    public invoiceDetails getId() {
        return id;
    }

    public void setId(invoiceDetails id) {
        this.id = id;
    }

    public List<invoiceList> getIl() {
        return il;
    }

    public void setIl(List<invoiceList> il) {
        this.il = il;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    public Double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Long getClientCount() {
        return clientCount;
    }

    public void setClientCount(Long clientCount) {
        this.clientCount = clientCount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Double getTotalGrandTotal() {
        return totalGrandTotal;
    }

    public void setTotalGrandTotal(Double totalGrandTotal) {
        this.totalGrandTotal = totalGrandTotal;
    }

    @Override
    public String toString() {
        return "InvoiceDetailsDTO{" +
                "cd=" + cd +
                ", id=" + id +
                ", il=" + il +
                ", productDetail='" + productDetail + '\'' +
                ", totalValue=" + totalValue +
                ", totalQuantity=" + totalQuantity +
                ", clientCount=" + clientCount +
                ", paymentType='" + paymentType + '\'' +
                ", totalGrandTotal=" + totalGrandTotal +
                '}';
    }
}
