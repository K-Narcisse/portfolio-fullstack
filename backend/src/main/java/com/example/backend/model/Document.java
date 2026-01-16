package com.example.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Document {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    
    @Column(length = 1000)
    private String description;
    
    private Double price;
    
    private String fileUrl;   // J'ai renommé pdfUrl en fileUrl (car ça peut être une vidéo)
    private String coverUrl; 
    
    // NOUVEAU CHAMP : Pour savoir si c'est "PDF" ou "VIDEO"
    private String type; 
}