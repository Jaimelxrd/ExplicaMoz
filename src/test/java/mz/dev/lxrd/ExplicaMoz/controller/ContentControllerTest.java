package mz.dev.lxrd.ExplicaMoz.controller;

import mz.dev.lxrd.ExplicaMoz.domain.Content;
import mz.dev.lxrd.ExplicaMoz.repository.ContentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
class ContentControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ContentRepository contentRepository;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        Content c1 = new Content();
        c1.setTitulo("Conteudo 1");
        c1.setDescricao("Descricao do conteudo 1 com mais de vinte caracteres");
        c1.setClasseNumero(10);
        c1.setDisciplina("Matematica");
        c1.setConteudoHtml("<p>Ola</p>");
        c1.setUrlSlug("conteudo-1");
        c1.setStatus("PUBLICADO");
        contentRepository.save(c1);
    }

    @Test
    void shouldReturnPublishedContent() throws Exception {
        mockMvc.perform(get("/api/v1/conteudos/publicados")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].titulo").value("Conteudo 1"));
    }

    @Test
    void shouldReturnContentBySlug() throws Exception {
        mockMvc.perform(get("/api/v1/conteudos/slug/conteudo-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.titulo").value("Conteudo 1"));
    }

    @Test
    void shouldReturn404ForInvalidSlug() throws Exception {
        mockMvc.perform(get("/api/v1/conteudos/slug/invalido"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Resource not found"));
    }
}
