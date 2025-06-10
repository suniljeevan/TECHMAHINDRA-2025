package com.example.techways.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techways.Models.Faculty;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Integer> { // <Entity, PRIMARY KEY TYPE>
    Optional<Faculty> findByEmail(String email);
}

