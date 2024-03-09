package com.billapp.billapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.billapp.billapp.entities.Product;

public interface productDao extends JpaRepository<Product,Long>{
    
}
