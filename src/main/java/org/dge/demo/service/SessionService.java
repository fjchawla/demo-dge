package org.dge.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.dge.demo.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.dge.demo.model.Session;
import java.util.List;

@Service
@Slf4j
public class SessionService {
    @Autowired
    private SessionRepository repo;

    public Session create(Session s) { return repo.save(s); }
    public Session rename(Long id, String name) throws Exception{
        Session s = repo.findById(id).orElseThrow(() -> new Exception("Session not found"));
        log.info("Setting session {} name {}",s.getId(),s.getSessionName());
        s.setSessionName(name);
        return repo.save(s);
    }
    public Session toggleFavorite(Long id) throws Exception{
        Session session = repo.findById(id).orElseThrow(() -> new Exception("Session not found"));
        log.info("Setting session {} favourite {}",session.getId(), !session.getIsFavorite());
        session.setIsFavorite(!session.getIsFavorite());
        return repo.save(session);
    }
    public void delete(Long id) { repo.deleteById(id); }
    public List<Session> getAll() { return repo.findAll(); }
}
