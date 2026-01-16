package com.example.backend.model; // Changement ici

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String email;
    private String sujet;
    
    @Column(length = 5000)
    private String content;

    private LocalDateTime dateEnvoi = LocalDateTime.now();
}