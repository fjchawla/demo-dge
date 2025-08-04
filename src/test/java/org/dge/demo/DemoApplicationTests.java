package org.dge.demo;

import org.dge.demo.model.Message;
import org.dge.demo.model.Session;
import org.dge.demo.repository.MessageRepository;
import org.dge.demo.repository.SessionRepository;
import org.dge.demo.service.MessageService;
import org.dge.demo.service.SessionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
class DemoApplicationTests {

	@Autowired
	private SessionService sessionService;
	@Autowired
	private SessionRepository sessionRepo;
	@Autowired
	private MessageService messageService;
	@Autowired
	private MessageRepository messageRepo;

	private Session session;

	@BeforeEach
	void setUp() {
		sessionRepo.deleteAll();
		sessionRepo.deleteAll();
		session = sessionRepo.save(new Session(null, "Session", false, LocalDateTime.now()));
	}

	@Test
	void testCreateSession() {
		Session session = new Session(null, "Test Session", false, LocalDateTime.now());
		Session saved = sessionService.create(session);
		assertNotNull(saved.getId());
		assertEquals("Test Session", saved.getSessionName());
	}

	@Test
	void testRenameSession() {
		Session s = sessionService.create(new Session(null, "Old Name", false, LocalDateTime.now()));
        Session renamed = null;
        try {
            renamed = sessionService.rename(s.getId(), "New Name");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals("New Name", renamed.getSessionName());
	}

	@Test
	void testToggleFavorite() {
		Session s = sessionService.create(new Session(null, "My Session", false, LocalDateTime.now()));
        Session toggled = null;
        try {
            toggled = sessionService.toggleFavorite(s.getId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertTrue(toggled.getIsFavorite());
	}


	@Test
	void testCreateMessage() {
		Message m = new Message(null, session.getId(), "user", "Hello", "{}", LocalDateTime.now());
		Message saved = messageService.create(m);
		assertNotNull(saved.getId());
		assertEquals("Hello", saved.getContent());
	}

	@Test
	void testGetMessages() {
		messageService.create(new Message(null, session.getId(), "user", "Hi", "{}", LocalDateTime.now()));
		messageService.create(new Message(null, session.getId(), "assistant", "Hello there!", "{}", LocalDateTime.now()));

		List<Message> messages = messageService.getMessages(session.getId());
		assertEquals(2, messages.size());
	}

}
