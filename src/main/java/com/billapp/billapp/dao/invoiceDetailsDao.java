package com.billapp.billapp.dao;

import org.springframework.data.jpa.repository.*;

import com.billapp.billapp.entities.*;

public interface invoiceDetailsDao extends JpaRepository<invoiceDetails, Long> {
}
