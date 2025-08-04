package org.dge.demo.controller;


import org.dge.demo.model.Message;
import org.dge.demo.service.MessageService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions/{sessionId}/messages")
public class MessageController {
    @Autowired
    private MessageService service;

    @PostMapping
    public Message add( @RequestBody Long sessionId, @RequestBody Message msg) {

        msg.setSessionId(sessionId);

        return service.create(msg);
    }

    //method to get all messages from session
    @GetMapping
    public List<Message> get(@PathVariable Long sessionId) {
        return service.getMessages(sessionId);
    }
}
