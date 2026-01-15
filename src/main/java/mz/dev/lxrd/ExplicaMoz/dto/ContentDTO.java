package mz.dev.lxrd.ExplicaMoz.dto;

import jakarta.validation.constraints.*;

public class ContentDTO {

    @NotNull(message = "Título é obrigatório")
    @Size(min = 5, max = 200, message = "Título deve ter entre 5 e 200 caracteres")
    private String titulo;

    @NotNull(message = "Descrição é obrigatória")
    @Size(min = 20, max = 500, message = "Descrição deve ter entre 20 e 500 caracteres") // Reduzido para 20
    private String descricao;

    @NotNull(message = "Classe é obrigatória")
    @Min(value = 6, message = "Classe mínima é 6")
    @Max(value = 12, message = "Classe máxima é 12")
    private Integer classeNumero;

    @NotNull(message = "Disciplina é obrigatória")
    @Size(min = 2, max = 50, message = "Disciplina deve ter entre 2 e 50 caracteres")
    private String disciplina;

    @NotNull(message = "Tema é obrigatório")
    @Size(min = 2, max = 100, message = "Tema deve ter entre 2 e 100 caracteres") // Reduzido para 2
    private String tema;

    @NotNull(message = "Conteúdo HTML é obrigatório")
    @Size(min = 50, message = "Conteúdo muito curto. Mínimo 50 caracteres") // Reduzido para 50
    private String conteudoHtml;

    @NotNull(message = "URL amigável é obrigatória")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",
            message = "URL deve conter apenas letras minúsculas, números e hífens")
    private String urlSlug;

    @Size(max = 300, message = "Meta descrição deve ter no máximo 300 caracteres") // Torna opcional
    private String metaDescription;

    @Size(max = 200, message = "Palavras-chave devem ter no máximo 200 caracteres") // Torna opcional
    private String keywords;

    private String dificuldade;
    private Integer tempoEstimado;
    private String autor;
    private String status = "RASCUNHO";

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Integer getClasseNumero() { return classeNumero; }
    public void setClasseNumero(Integer classeNumero) { this.classeNumero = classeNumero; }

    public String getDisciplina() { return disciplina; }
    public void setDisciplina(String disciplina) { this.disciplina = disciplina; }

    public String getTema() { return tema; }
    public void setTema(String tema) { this.tema = tema; }

    public String getConteudoHtml() { return conteudoHtml; }
    public void setConteudoHtml(String conteudoHtml) { this.conteudoHtml = conteudoHtml; }

    public String getUrlSlug() { return urlSlug; }
    public void setUrlSlug(String urlSlug) { this.urlSlug = urlSlug; }

    public String getMetaDescription() { return metaDescription; }
    public void setMetaDescription(String metaDescription) { this.metaDescription = metaDescription; }

    public String getKeywords() { return keywords; }
    public void setKeywords(String keywords) { this.keywords = keywords; }

    public String getDificuldade() { return dificuldade; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }

    public Integer getTempoEstimado() { return tempoEstimado; }
    public void setTempoEstimado(Integer tempoEstimado) { this.tempoEstimado = tempoEstimado; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}