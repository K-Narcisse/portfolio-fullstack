package com.example.backend.controller; // Changement ici

// Imports mis à jour vers 'com.example.backend'
import com.example.backend.model.Message;
import com.example.backend.repository.MessageRepository;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*") 
public class MessageController {

    private final MessageRepository repository;

    public MessageController(MessageRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public Message sendMessage(@RequestBody Message message) {
        return repository.save(message);
    }

    @GetMapping
    public List<Message> getAllMessages() {
        return repository.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        repository.deleteById(id);
    }
}