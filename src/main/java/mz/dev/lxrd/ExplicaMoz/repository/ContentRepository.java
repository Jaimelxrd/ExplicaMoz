package mz.dev.lxrd.ExplicaMoz.repository;

import mz.dev.lxrd.ExplicaMoz.domain.Content;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByStatus(String status);
    Page<Content> findByStatus(String status, Pageable pageable);
    List<Content> findByClasseNumero(Integer classeNumero);
    List<Content> findByDisciplina(String disciplina);
    Content findByUrlSlug(String urlSlug);
    Content findByUrlSlugAndStatus(String urlSlug, String status);

    List<Content> findByClasseNumeroAndStatusOrderByDisciplinaAsc(Integer classeNumero, String status);
    Page<Content> findByClasseNumeroAndStatus(Integer classeNumero, String status, Pageable pageable);

    List<Content> findByClasseNumeroAndDisciplinaAndStatusOrderByTemaAsc(
            Integer classeNumero, String disciplina, String status);

    List<Content> findByClasseNumeroAndDisciplinaAndStatusAndIdNot(
            Integer classeNumero, String disciplina, String status, Long id);

    List<Content> findTop10ByStatusOrderByCreatedAtDesc(String status);

    List<Content> findTop5ByStatusOrderByIdDesc(String status);

    @Query("SELECT c FROM Content c WHERE (c.titulo ILIKE %:query% OR c.descricao ILIKE %:query%) AND c.status = 'PUBLICADO'")
    Page<Content> searchPublished(String query, Pageable pageable);
}
