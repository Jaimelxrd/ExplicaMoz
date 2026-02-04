package mz.dev.lxrd.ExplicaMoz.service;

import mz.dev.lxrd.ExplicaMoz.domain.Content;
import mz.dev.lxrd.ExplicaMoz.dto.ContentDTO;
import mz.dev.lxrd.ExplicaMoz.repository.ContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ContentServiceTest {

    @Mock
    private ContentRepository contentRepository;

    @InjectMocks
    private ContentService contentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGenerateSlugWhenNotProvided() {
        ContentDTO dto = new ContentDTO();
        dto.setTitulo("O Guia de Matemática para a 12ª Classe");
        dto.setDescricao("Descrição longa o suficiente para passar na validação de teste");
        dto.setClasseNumero(12);
        dto.setDisciplina("Matemática");
        dto.setTema("Geral");
        dto.setConteudoHtml("<p>Teste</p>");
        dto.setUrlSlug(""); // Empty slug

        when(contentRepository.findByUrlSlug(anyString())).thenReturn(null);
        when(contentRepository.save(any(Content.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Content saved = contentService.createContent(dto);

        assertNotNull(saved.getUrlSlug());
        assertEquals("o-guia-de-matematica-para-a-12-classe", saved.getUrlSlug());
        verify(contentRepository, times(1)).save(any(Content.class));
    }

    @Test
    void shouldThrowExceptionWhenSlugAlreadyExists() {
        ContentDTO dto = new ContentDTO();
        dto.setTitulo("Teste");
        dto.setUrlSlug("slug-existente");

        when(contentRepository.findByUrlSlug("slug-existente")).thenReturn(new Content());

        assertThrows(RuntimeException.class, () -> contentService.createContent(dto));
    }

    @Test
    void shouldMapDtoToEntityCorrectly() {
        ContentDTO dto = new ContentDTO();
        dto.setTitulo("Título Teste");
        dto.setDescricao("Descrição Teste de 20 caracteres");
        dto.setClasseNumero(10);
        dto.setDisciplina("Física");
        dto.setStatus("PUBLICADO");

        when(contentRepository.findByUrlSlug(any())).thenReturn(null);
        when(contentRepository.save(any(Content.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Content saved = contentService.createContent(dto);

        assertEquals(dto.getTitulo(), saved.getTitulo());
        assertEquals(dto.getDescricao(), saved.getDescricao());
        assertEquals(dto.getClasseNumero(), saved.getClasseNumero());
        assertEquals(dto.getDisciplina(), saved.getDisciplina());
        assertEquals("PUBLICADO", saved.getStatus());
    }
}
