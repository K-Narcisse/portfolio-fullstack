package com.example.backend.controller;

import com.example.backend.model.Project;
import com.example.backend.repository.ProjectRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "@CrossOrigin(origins = \"*\")") // Indispensable pour Vue.js
public class PortfolioController {

    private final ProjectRepository repository;

    public PortfolioController(ProjectRepository repository) {
        this.repository = repository;
    }

    // Récupérer la liste
    @GetMapping("/projects")
    public List<Project> getProjects() {
        return repository.findAll();
    }

    // Ajouter un projet
    @PostMapping("/projects")
    public Project addProject(@RequestBody Project project) {
        return repository.save(project);
    }
}