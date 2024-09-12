package dsl.jackson.igor.SGCC;

import dsl.jackson.igor.SGCC.controller.IntercambioController;
import dsl.jackson.igor.SGCC.model.dto.CentroRecursoDTO;
import dsl.jackson.igor.SGCC.model.dto.IntercambioDTO;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import dsl.jackson.igor.SGCC.model.entity.Intercambio;
import dsl.jackson.igor.SGCC.model.entity.Recursos;
import dsl.jackson.igor.SGCC.model.repository.CentroComunitarioRepository;
import dsl.jackson.igor.SGCC.model.repository.CentroRecursosRepository;
import dsl.jackson.igor.SGCC.model.repository.IntercambioRepository;
import dsl.jackson.igor.SGCC.model.repository.RecursosRepository;
import dsl.jackson.igor.SGCC.model.service.IntercambioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(IntercambioController.class)
public class IntercambioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IntercambioService intercambioService;

    @Test
    public void testListarIntercambios() throws Exception {
        Intercambio intercambio = new Intercambio();
        intercambio.setIdCentroOrigem("centro1");
        intercambio.setIdCentroDestino("centro2");

        when(intercambioService.listarIntercambios()).thenReturn(Collections.singletonList(intercambio));

        mockMvc.perform(get("/api/intercambios/listar")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].idCentroOrigem").value("centro1"))
                .andExpect(jsonPath("$[0].idCentroDestino").value("centro2"));
    }
}
