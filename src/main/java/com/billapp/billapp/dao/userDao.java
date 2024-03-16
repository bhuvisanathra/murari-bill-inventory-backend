package com.billapp.billapp.dao;

import com.billapp.billapp.entities.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userDao extends JpaRepository<ApplicationUser,Integer> {
    Optional<ApplicationUser> findByUsername(String username);
}
