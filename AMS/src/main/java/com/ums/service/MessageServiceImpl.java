package com.ums.service;

import com.ums.model.Message;
import com.ums.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public List<Message> getMessagesForAlumni(int alumniId) {
        return messageRepository.findByRecipientIdAndRecipientType(alumniId, "ALUMNI");
    }

    @Override
    public List<Message> getMessagesForAdmin(int adminId) {
        return messageRepository.findByRecipientIdAndRecipientType(adminId, "ADMIN");
    }

    @Override
    public List<Message> getMessagesSentByAdmin(int adminId) {
        return messageRepository.findBySenderIdAndSenderType(adminId, "ADMIN");
    }

    @Override
    public List<Message> getMessagesSentByAlumni(int alumniId) {
        return messageRepository.findBySenderIdAndSenderType(alumniId, "ALUMNI");
    }

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
}
