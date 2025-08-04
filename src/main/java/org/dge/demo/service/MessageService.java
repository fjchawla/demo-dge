package org.dge.demo.service;

import org.dge.demo.model.Message;
import org.dge.demo.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    public Page<Message> getMessages(Long sessionId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").ascending());
        return repo.findBySessionId(sessionId, pageable);
    }

}
