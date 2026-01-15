package mz.dev.lxrd.ExplicaMoz.controller;

import jakarta.validation.Valid;
import mz.dev.lxrd.ExplicaMoz.domain.Content;
import mz.dev.lxrd.ExplicaMoz.dto.AdminLoginDTO;
import mz.dev.lxrd.ExplicaMoz.dto.ContentDTO;
import mz.dev.lxrd.ExplicaMoz.repository.ContentRepository;
import mz.dev.lxrd.ExplicaMoz.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    // SENHA SIMPLES PARA MVP (substituir depois por JWT)
    private static final String ADMIN_PASSWORD = "admin123";

    @Autowired
    private ContentRepository contentRepository;
    @Autowired
    private ContentService contentService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AdminLoginDTO login) {
        if ("admin".equals(login.getUsername()) && ADMIN_PASSWORD.equals(login.getPassword())) {
            Map<String, String> response = new HashMap<>();
            response.put("token", "logged-in"); // Simples para MVP
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Content> getById(@PathVariable Long id) {
        return contentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/conteudos")
    public ResponseEntity<List<Content>> getAllContent() {
        return ResponseEntity.ok(contentRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ContentDTO dto) {
        try {
            Content created = contentService.createContent(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid @RequestBody ContentDTO dto) {
        try {
            Content updated = contentService.updateContent(id, dto);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            contentService.deleteContent(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.notFound().build();
        }

    }
}