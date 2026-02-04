package mz.dev.lxrd.ExplicaMoz.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="conteudos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(length = 500)
    private String descricao;

    @Column(name = "classe_numero", nullable = false)
    private Integer classeNumero; // 6 a 12

    @Column(nullable = false)
    private String disciplina; // Matemática, Português, etc.

    private String tema;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String conteudoHtml;

    @Column(name = "url_slug", unique = true, nullable = false)
    private String urlSlug;

    @Column(name = "meta_description", length = 300)
    private String metaDescription;

    @Column(name = "keywords")
    private String keywords;

    @Column(name = "dificuldade")
    private String dificuldade; // Fácil, Médio, Difícil

    @Column(name = "tempo_estimado")
    private Integer tempoEstimado; // Minutos

    @Column(name = "autor")
    private String autor;

    @Column(name = "status")
    private String status = "RASCUNHO"; // RASCUNHO, PUBLICADO

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
