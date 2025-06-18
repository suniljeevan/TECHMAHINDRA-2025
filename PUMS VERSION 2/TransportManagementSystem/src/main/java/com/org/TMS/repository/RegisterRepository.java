package com.org.TMS.repository;

import com.org.TMS.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepository extends JpaRepository<Student, Long> {
    boolean existsBySid(String sid);
}
