package com.example.naher_farhsa.exammaster_fsvo.Repository;




import com.example.naher_farhsa.exammaster_fsvo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
