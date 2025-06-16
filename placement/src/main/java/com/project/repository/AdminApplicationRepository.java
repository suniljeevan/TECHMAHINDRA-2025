package com.project.repository;

import com.project.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdminApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a JOIN FETCH a.job")
    List<Application> findAllWithJob();

    @Query("SELECT a FROM Application a JOIN FETCH a.job WHERE a.status = 'Accepted'")
    List<Application> findAcceptedApplicationsWithJob();
}
