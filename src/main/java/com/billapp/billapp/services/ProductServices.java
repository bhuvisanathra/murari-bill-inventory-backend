package com.billapp.billapp.services;

import java.util.List;

import com.billapp.billapp.entities.Product;

public interface ProductServices {
 
    public List<Product> getProducts();
    public Product addProduct(Product p);
    public Product getProductbyId(Long id);
    public Product updateProduct(Product p);
    public Product deleteProduct(Long p);
    
}
