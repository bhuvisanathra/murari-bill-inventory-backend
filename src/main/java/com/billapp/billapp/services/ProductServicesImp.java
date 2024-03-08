package com.billapp.billapp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.billapp.billapp.dao.*;
import com.billapp.billapp.entities.*;

@Service
public class ProductServicesImp  implements ProductServices{

    @Autowired
    productDao pd;
    
    @Override
    public List<Product> getProducts() {
        return pd.findAll();
    }

    @Override
    public Product getProductbyId(Long productId) {
        Optional<Product> optionalProduct = pd.findById(productId);
        return optionalProduct.get();

    }

    public Product addProduct(Product product){
        pd.save(product);
        return product;
    }

    @Override
    public Product updateProduct(Product product) {
        pd.save(product);
        return product;
    }

    @Override
    public Product deleteProduct(Product product) {
        pd.delete(product);
        return product;
    }
    
}
