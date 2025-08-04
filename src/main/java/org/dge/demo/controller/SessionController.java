package org.dge.demo.controller;

import org.dge.demo.model.Session;
import org.dge.demo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sessions")
        public class SessionController {
        @Autowired
        private SessionService sessionService;

        @PostMapping
        public Session create(@RequestBody Session session) {
        return sessionService.create(session);
        }

        @PatchMapping("/{id}/rename")
        public Session rename(@PathVariable Long id, @RequestBody Map<String, String> body) throws Exception{
            return sessionService.rename(id, body.get("name"));
        }

        @PatchMapping("/{id}/favorite")
        public Session toggleFavorite(@PathVariable Long id) throws Exception {
            return sessionService.toggleFavorite(id);
        }

        @DeleteMapping("/{id}")
        public void delete(@PathVariable Long id) {
            sessionService.delete(id);
        }

        @GetMapping
        public List<Session> list() {
            return sessionService.getAll();
        }
}

