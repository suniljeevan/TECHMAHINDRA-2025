package com.ums.service;

import com.ums.model.Message;
import java.util.List;

public interface MessageService {
    Message saveMessage(Message message);

    List<Message> getMessagesForAlumni(int alumniId);      // inbox for alumni
    List<Message> getMessagesForAdmin(int adminId);        // inbox for admin
    List<Message> getMessagesSentByAdmin(int adminId);     // sent by admin
    List<Message> getMessagesSentByAlumni(int alumniId);   // sent by alumni
    List<Message> getAllMessages();                        // optional full list
}
