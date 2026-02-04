package mz.dev.lxrd.ExplicaMoz.controller;

import jakarta.validation.Valid;
import mz.dev.lxrd.ExplicaMoz.domain.Content;
import mz.dev.lxrd.ExplicaMoz.dto.ContentDTO;
import mz.dev.lxrd.ExplicaMoz.repository.ContentRepository;
import mz.dev.lxrd.ExplicaMoz.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/conteudos")
@CrossOrigin(origins = "*")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ContentRepository contentRepository;

    @GetMapping("/publicados")
    public ResponseEntity<Page<Content>> getPublished(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(contentService.getAllPublished(pageable));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Content> getById(@PathVariable Long id) {
        return contentRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/classe/{classe}")
    public ResponseEntity<Page<Content>> getByClass(
            @PathVariable Integer classe,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(contentService.getByClass(classe, pageable));
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<Content> getBySlug(@PathVariable String slug) {
        try {
            Content content = contentService.getBySlug(slug);
            return ResponseEntity.ok(content);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/relacionados/{id}")
    public ResponseEntity<List<Content>> getRelated(
            @PathVariable Long id,
            @RequestParam Integer classe,
            @RequestParam String disciplina) {
        return ResponseEntity.ok(contentService.getRelatedContent(id, classe, disciplina));
    }

    @GetMapping("/busca")
    public ResponseEntity<Page<Content>> search(
            @RequestParam String q,
            @PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(contentService.search(q, pageable));
    }

}