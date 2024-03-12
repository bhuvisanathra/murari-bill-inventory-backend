package com.billapp.billapp.entities;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "invoice_list")
public class invoiceList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "invoice_id")
    private Long id;

    @Column(name = "srNo")
    private Double srNo;

    @Column(name = "productDetail")
    private String productDetail;

    @Column(name = "kgOrGram")
    private Double kgOrGram;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "value")
    private Double value;

    @Column(name = "disc")
    private Double discount;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private clientDetails clientDetails;

    public invoiceList() {
    }

    public invoiceList(Long id, Double srNo, String productDetail, Double kgOrGram, Double rate, Double value,
            clientDetails clientDetails) {
        this.id = id;
        this.srNo = srNo;
        this.productDetail = productDetail;
        this.kgOrGram = kgOrGram;
        this.rate = rate;
        this.value = value;
        this.clientDetails = clientDetails;
    }

    public invoiceList(Map<String, Object> map) {
        this.srNo = ((Number) map.get("srNo")).doubleValue();
        this.productDetail = (String) map.get("productDetail");
        this.kgOrGram = ((Number) map.get("kgOrGram")).doubleValue();
        this.rate = ((Number) map.get("rate")).doubleValue();
        this.value = ((Number) map.get("value")).doubleValue();
        this.discount = ((Number) map.get("disc")).doubleValue();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSrNo() {
        return this.srNo;
    }

    public void setSrNo(Double srNo) {
        this.srNo = srNo;
    }

    public String getProductDetail() {
        return this.productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public Double getKgOrGram() {
        return this.kgOrGram;
    }

    public void setKgOrGram(Double kgOrGram) {
        this.kgOrGram = kgOrGram;
    }

    public Double getRate() {
        return this.rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public clientDetails getClientDetails() {
        return this.clientDetails;
    }

    public void setClientDetails(clientDetails clientDetails) {
        this.clientDetails = clientDetails;
    }

    public Double getDiscount() {
        return this.discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", srNo='" + getSrNo() + "'" +
                ", productDetail='" + getProductDetail() + "'" +
                ", kgOrGram='" + getKgOrGram() + "'" +
                ", rate='" + getRate() + "'" +
                ", value='" + getValue() + "'" +
                ", discount='" + getDiscount() + "'" +
                ", clientDetails='" + getClientDetails() + "'" +
                "}";
    }

}
