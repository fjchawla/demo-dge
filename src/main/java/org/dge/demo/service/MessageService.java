package org.dge.demo.service;

import org.dge.demo.model.Message;
import org.dge.demo.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {


    @Autowired
    private MessageRepository repo;

    public Message create(Message m) { return repo.save(m); }
    public List<Message> getMessages(Long sessionId) {
        return repo.findBySessionIdOrderByCreatedAtAsc(sessionId);
    }



}
