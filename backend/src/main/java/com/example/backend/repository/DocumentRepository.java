package com.example.backend.repository;

import com.example.backend.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    // C'est vide, mais Spring Boot remplit tout magiquement !
}