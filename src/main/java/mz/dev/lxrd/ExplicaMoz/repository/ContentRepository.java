package mz.dev.lxrd.ExplicaMoz.repository;

import mz.dev.lxrd.ExplicaMoz.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    List<Content> findByStatus(String status);
    List<Content> findByClasseNumero(Integer classeNumero);
    List<Content> findByDisciplina(String disciplina);
    Content findByUrlSlug(String urlSlug);
    Content findByUrlSlugAndStatus(String urlSlug, String status);

    List<Content> findByClasseNumeroAndStatusOrderByDisciplinaAsc(Integer classeNumero, String status);

    List<Content> findByClasseNumeroAndDisciplinaAndStatusOrderByTemaAsc(
            Integer classeNumero, String disciplina, String status);

    List<Content> findByClasseNumeroAndDisciplinaAndStatusAndIdNot(
            Integer classeNumero, String disciplina, String status, Long id);

    List<Content> findTop10ByStatusOrderByCreatedAtDesc(String status);

    List<Content> findTop5ByStatusOrderByIdDesc(String status);
}
