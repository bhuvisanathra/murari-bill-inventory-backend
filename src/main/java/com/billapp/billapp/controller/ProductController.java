package com.billapp.billapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.billapp.billapp.services.ProductServices;
import com.billapp.billapp.entities.*;

@RestController
@CrossOrigin(origins = {"http://localhost:5173", "https://muraribhuvi.netlify.app"})
public class ProductController {

    @Autowired
    private ProductServices ps;

    @GetMapping("/product")
    public List<Product> getProduct(){
        return this.ps.getProducts();
    }

    @GetMapping("/product/{productid}")
    public Product getProductbyId(@PathVariable Long productid){
        return this.ps.getProductbyId(productid);
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product p){
        return this.ps.addProduct(p);
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product p){
        return this.ps.updateProduct(p);
    }

    @DeleteMapping("/product")
    public Product deleteProduct(@RequestBody Product p){
        return this.ps.deleteProduct(p);
    }


    
}
