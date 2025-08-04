package org.dge.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue
    private Long id;
    private Long sessionId;
    private String sender; // "user" or "assistant"

    @Lob
    private String content;

    @Lob
    private String context;

    private LocalDateTime createdAt = LocalDateTime.now();
}
