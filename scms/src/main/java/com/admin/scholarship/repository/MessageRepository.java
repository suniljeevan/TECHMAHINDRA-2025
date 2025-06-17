package com.admin.scholarship.repository;

import com.admin.scholarship.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
