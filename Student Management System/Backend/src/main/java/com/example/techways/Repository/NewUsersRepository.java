package com.example.techways.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.techways.Models.NewUsers;

@Repository
public interface NewUsersRepository extends JpaRepository<NewUsers, Integer> { // <Entity, PRIMARY KEY TYPE>
    Optional<NewUsers> findByEmail(String email);
}
