package mz.dev.lxrd.ExplicaMoz.service;

import mz.dev.lxrd.ExplicaMoz.domain.Content;
import mz.dev.lxrd.ExplicaMoz.dto.ContentDTO;
import mz.dev.lxrd.ExplicaMoz.exception.ResourceNotFoundException;
import mz.dev.lxrd.ExplicaMoz.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.Normalizer;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Transactional
    public Content createContent(ContentDTO dto) {
        String slug = dto.getUrlSlug();
        if (slug == null || slug.trim().isEmpty()) {
            slug = generateSlug(dto.getTitulo());
        }

        // Verifica se URL já existe
        if (contentRepository.findByUrlSlug(slug) != null) {
            throw new RuntimeException("URL já existe. Escolha outra.");
        }

        Content content = new Content();
        mapDtoToEntity(dto, content);
        content.setUrlSlug(slug);

        return contentRepository.save(content);
    }

    @Transactional
    public Content updateContent(Long id, ContentDTO dto) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conteúdo não encontrado"));

        String newSlug = dto.getUrlSlug();
        if (newSlug == null || newSlug.trim().isEmpty()) {
            newSlug = generateSlug(dto.getTitulo());
        }

        // Verifica se nova URL já existe (para outro conteúdo)
        if (!content.getUrlSlug().equals(newSlug)) {
            Content existing = contentRepository.findByUrlSlug(newSlug);
            if (existing != null && !existing.getId().equals(id)) {
                throw new RuntimeException("URL já existe. Escolha outra.");
            }
        }

        mapDtoToEntity(dto, content);
        content.setUrlSlug(newSlug);
        return contentRepository.save(content);
    }

    @Transactional(readOnly = true)
    public Content getBySlug(String slug) {
        Content content = contentRepository.findByUrlSlugAndStatus(slug, "PUBLICADO");
        if (content == null) {
            throw new ResourceNotFoundException("Conteúdo não encontrado ou não publicado");
        }
        return content;
    }

    @Transactional(readOnly = true)
    public List<Content> getRelatedContent(Long currentId, Integer classe, String disciplina) {
        return contentRepository.findByClasseNumeroAndDisciplinaAndStatusAndIdNot(
                classe, disciplina, "PUBLICADO", currentId);
    }

    private void mapDtoToEntity(ContentDTO dto, Content entity) {
        entity.setTitulo(dto.getTitulo());
        entity.setDescricao(dto.getDescricao());
        entity.setClasseNumero(dto.getClasseNumero());
        entity.setDisciplina(dto.getDisciplina());
        entity.setTema(dto.getTema());
        entity.setConteudoHtml(dto.getConteudoHtml());
        // urlSlug is handled separately to support generation
        entity.setMetaDescription(dto.getMetaDescription());
        entity.setKeywords(dto.getKeywords());
        entity.setDificuldade(dto.getDificuldade());
        entity.setTempoEstimado(dto.getTempoEstimado());
        entity.setAutor(dto.getAutor());
        entity.setStatus(dto.getStatus());
    }

    @Transactional(readOnly = true)
    public Page<Content> getAllPublished(Pageable pageable) {
        return contentRepository.findByStatus("PUBLICADO", pageable);
    }

    @Transactional(readOnly = true)
    public Page<Content> getByClass(Integer classe, Pageable pageable) {
        return contentRepository.findByClasseNumeroAndStatus(classe, "PUBLICADO", pageable);
    }

    @Transactional(readOnly = true)
    public Page<Content> getAll(Pageable pageable) {
        return contentRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Content> search(String query, Pageable pageable) {
        return contentRepository.searchPublished(query, pageable);
    }

    @Transactional
    public void deleteContent(Long id) {
        if (!contentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Conteúdo não encontrado");
        }
        contentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Content getById(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conteúdo não encontrado"));
    }

    private String generateSlug(String input) {
        if (input == null || input.isEmpty()) return "";

        String nowhitespace = input.trim().replaceAll("\\s+", "-");
        String normalized = Normalizer.normalize(nowhitespace, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String slug = pattern.matcher(normalized).replaceAll("")
                .toLowerCase(Locale.ENGLISH)
                .replaceAll("[^a-z0-9-]", "");

        return slug.replaceAll("-+", "-").replaceAll("^-|-$", "");
    }
}