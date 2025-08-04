package org.dge.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Session {
    @Id
    @GeneratedValue
    private Long id;
    private String sessionName;
    private Boolean isFavorite = false;
    private LocalDateTime createdAt = LocalDateTime.now();

}
