package com.example.backend.controller;

import com.example.backend.model.Document;
import com.example.backend.repository.DocumentRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/documents")
@CrossOrigin(origins = "@CrossOrigin(origins = \"*\")")
public class DocumentController {

    private final DocumentRepository repository;
    
    // Le dossier où seront stockés les fichiers (à la racine du projet backend)
    private static final String UPLOAD_DIR = "uploads/";

    public DocumentController(DocumentRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Document> getAllDocuments() {
        return repository.findAll();
    }

    // NOUVELLE MÉTHODE D'AJOUT AVEC FICHIER
    @PostMapping(consumes = {"multipart/form-data"})
    public Document addDocument(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("price") Double price,
            @RequestParam("type") String type,
            @RequestParam("file") MultipartFile file) throws IOException {

        // 1. Créer le dossier uploads s'il n'existe pas
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        // 2. Générer un nom de fichier unique (pour éviter les doublons)
        String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);

        // 3. Sauvegarder le fichier sur le disque dur
        Files.copy(file.getInputStream(), filePath);

        // 4. Créer l'objet Document pour la base de données
        Document doc = new Document();
        doc.setTitle(title);
        doc.setDescription(description);
        doc.setPrice(price);
        doc.setType(type);
        
        // On construit l'URL accessible depuis le navigateur
        // Ex: http://localhost:8080/uploads/123456_monlivre.pdf
        String fileUrl = "http://localhost:8080/uploads/" + filename;
        doc.setFileUrl(fileUrl);

        return repository.save(doc);
    }
}