package dsl.jackson.igor.SGCC;

import dsl.jackson.igor.SGCC.controller.CentroComunitarioController;
import dsl.jackson.igor.SGCC.model.dto.CentroComunitarioDTO;
import dsl.jackson.igor.SGCC.model.dto.CentroComunitarioDTORequest;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import dsl.jackson.igor.SGCC.model.service.CentroComunitarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Collections;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CentroComunitarioController.class)
public class CentroComunitarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CentroComunitarioService centroComunitarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarCentros() throws Exception {
        CentroComunitario centro = new CentroComunitario();
        centro.setId("1");
        centro.setNome("Centro Teste");

        when(centroComunitarioService.listarCentros()).thenReturn(Collections.singletonList(centro));

        mockMvc.perform(get("/api/centros/listar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("Centro Teste"));
    }

    @Test
    public void testAdicionarCentroComunitario() throws Exception {

        CentroComunitario centro = new CentroComunitario();
        centro.setId("1");
        centro.setNome("Novo Centro");
        centro.setCapacidadeMaxima(10);
        centro.setLocalizacao("");
        centro.setEndereco("");
        centro.setPessoasOcupando(10);

        when(centroComunitarioService.criarCentroComunitario(any(CentroComunitarioDTORequest.class))).thenReturn(centro);

        mockMvc.perform(post("/api/centros")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Novo Centro\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nome").value("Novo Centro"));
    }

    @Test
    public void testAtualizarOcupacao() throws Exception {
        when(centroComunitarioService.atualizarOcupacao("1", 75)).thenReturn(true);

        mockMvc.perform(put("/api/centros/1/atualizar-ocupacao")
                .param("novaOcupacao", "75")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Ocupacao atualizada e notificacao gerada."));
    }

}
