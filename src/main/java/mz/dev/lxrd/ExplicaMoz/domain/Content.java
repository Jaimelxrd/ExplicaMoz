package mz.dev.lxrd.ExplicaMoz.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="conteudos")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getClasseNumero() {
        return classeNumero;
    }

    public void setClasseNumero(Integer classeNumero) {
        this.classeNumero = classeNumero;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getConteudoHtml() {
        return conteudoHtml;
    }

    public void setConteudoHtml(String conteudoHtml) {
        this.conteudoHtml = conteudoHtml;
    }

    public String getUrlSlug() {
        return urlSlug;
    }

    public void setUrlSlug(String urlSlug) {
        this.urlSlug = urlSlug;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDificuldade() {
        return dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        this.dificuldade = dificuldade;
    }

    public Integer getTempoEstimado() {
        return tempoEstimado;
    }

    public void setTempoEstimado(Integer tempoEstimado) {
        this.tempoEstimado = tempoEstimado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Content(Long id, String titulo, String descricao, Integer classeNumero, String disciplina, String tema, String conteudoHtml, String urlSlug, String metaDescription, String keywords, String dificuldade, Integer tempoEstimado, String autor, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.classeNumero = classeNumero;
        this.disciplina = disciplina;
        this.tema = tema;
        this.conteudoHtml = conteudoHtml;
        this.urlSlug = urlSlug;
        this.metaDescription = metaDescription;
        this.keywords = keywords;
        this.dificuldade = dificuldade;
        this.tempoEstimado = tempoEstimado;
        this.autor = autor;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Content() {
    }
}
