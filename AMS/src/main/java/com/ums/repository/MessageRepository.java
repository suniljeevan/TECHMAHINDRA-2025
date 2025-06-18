package com.ums.repository;

import com.ums.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
	
    List<Message> findByRecipientIdAndRecipientType(int recipientId, String recipientType);

    // Messages sent by a specific sender (optional)
    List<Message> findBySenderIdAndSenderType(int senderId, String senderType);
    
}
