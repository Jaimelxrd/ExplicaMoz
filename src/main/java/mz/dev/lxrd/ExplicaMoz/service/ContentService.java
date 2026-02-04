package mz.dev.lxrd.ExplicaMoz.service;

import mz.dev.lxrd.ExplicaMoz.domain.Content;
import mz.dev.lxrd.ExplicaMoz.dto.ContentDTO;
import mz.dev.lxrd.ExplicaMoz.repository.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Transactional
    public Content createContent(ContentDTO dto) {
        // Verifica se URL já existe
        if (contentRepository.findByUrlSlug(dto.getUrlSlug()) != null) {
            throw new RuntimeException("URL já existe. Escolha outra.");
        }

        Content content = new Content();
        mapDtoToEntity(dto, content);

        return contentRepository.save(content);
    }

    @Transactional
    public Content updateContent(Long id, ContentDTO dto) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));

        // Verifica se nova URL já existe (para outro conteúdo)
        if (!content.getUrlSlug().equals(dto.getUrlSlug())) {
            Content existing = contentRepository.findByUrlSlug(dto.getUrlSlug());
            if (existing != null && !existing.getId().equals(id)) {
                throw new RuntimeException("URL já existe. Escolha outra.");
            }
        }

        mapDtoToEntity(dto, content);
        return contentRepository.save(content);
    }

    @Transactional(readOnly = true)
    public Content getBySlug(String slug) {
        Content content = contentRepository.findByUrlSlugAndStatus(slug, "PUBLICADO");
        if (content == null) {
            throw new RuntimeException("Conteúdo não encontrado ou não publicado");
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
        entity.setUrlSlug(dto.getUrlSlug());
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
            throw new RuntimeException("Conteúdo não encontrado");
        }
        contentRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Content getById(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conteúdo não encontrado"));
    }
}