package com.billapp.billapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billapp.billapp.entities.clientDetails;
import com.billapp.billapp.entities.invoiceList;

public interface invoiceListDao extends JpaRepository<invoiceList,Integer>{
}
