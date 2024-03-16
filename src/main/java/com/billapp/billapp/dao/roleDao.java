package com.billapp.billapp.dao;

import com.billapp.billapp.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface roleDao extends JpaRepository<Role,Integer> {
    Optional<Role> findByAuthority(String authority);
}
