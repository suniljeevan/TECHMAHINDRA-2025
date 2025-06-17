package com.admin.scholarship.service;

import com.admin.scholarship.model.Message;
import com.admin.scholarship.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository repo;

    public MessageService(MessageRepository repo) {
        this.repo = repo;
    }

    public void saveMessage(Message message) {
        repo.save(message);
    }

    public List<Message> getAllMessages() {
        return repo.findAll();
    }
}
