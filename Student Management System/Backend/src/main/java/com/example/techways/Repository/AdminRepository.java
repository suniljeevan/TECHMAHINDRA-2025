package com.example.techways.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techways.Models.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> { // <Entity, PRIMARY KEY TYPE>
    Optional<Admin> findByEmail(String email);
}
