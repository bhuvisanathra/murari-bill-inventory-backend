package com.billapp.billapp.entities;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;

    @Column(name = "productName")
    private String productName;

    @Column(name="productPrice")
    private Double productPrice;


    public Product() {
    }


    public Product(Long id, String productName, Double productPrice) {
        this.id = id;
        this.productName = productName;
        this.productPrice = productPrice;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return this.productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", productName='" + getProductName() + "'" +
            ", productPrice='" + getProductPrice() + "'" +
            "}";
    }


}
