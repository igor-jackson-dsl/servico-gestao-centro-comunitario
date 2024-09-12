package dsl.jackson.igor.SGCC;

import dsl.jackson.igor.SGCC.model.dto.CentroRecursoDTO;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import dsl.jackson.igor.SGCC.model.entity.CentroRecursos;
import dsl.jackson.igor.SGCC.model.entity.Intercambio;
import dsl.jackson.igor.SGCC.model.entity.Recursos;
import dsl.jackson.igor.SGCC.model.repository.CentroComunitarioRepository;
import dsl.jackson.igor.SGCC.model.repository.CentroRecursosRepository;
import dsl.jackson.igor.SGCC.model.repository.IntercambioRepository;
import dsl.jackson.igor.SGCC.model.repository.RecursosRepository;
import dsl.jackson.igor.SGCC.model.service.Implementation.IntercambioServiceImpl;
import dsl.jackson.igor.SGCC.model.service.IntercambioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class IntercambioServiceImplTest {

        @InjectMocks
        private IntercambioServiceImpl intercambioService;

        @Mock
        private CentroComunitarioRepository centroComunitarioRepository;

        @Mock
        private RecursosRepository recursosRepository;

        @Mock
        private CentroRecursosRepository centroRecursosRepository;

        @Mock
        private IntercambioRepository intercambioRepository;

        @BeforeEach
        public void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        public void testRealizarIntercambio_Success() {
            CentroRecursoDTO recursoDTO = new CentroRecursoDTO();
            recursoDTO.setCodRecurso(10);
            recursoDTO.setQuantidadeRecurso(10);

            CentroComunitario centroOrigem = new CentroComunitario();
            centroOrigem.setId("centro1");
            centroOrigem.setPessoasOcupando(90);
            centroOrigem.setCapacidadeMaxima(100);

            CentroComunitario centroDestino = new CentroComunitario();
            centroDestino.setId("centro2");
            centroDestino.setPessoasOcupando(50);
            centroDestino.setCapacidadeMaxima(100);

            Recursos recurso = new Recursos();
            recurso.setId("recurso1");
            recurso.setPontos(10);

            CentroRecursos centroRecursosOrigem = new CentroRecursos();
            centroRecursosOrigem.setQuantidadeRecurso(50);

            CentroRecursos centroRecursosDestino = new CentroRecursos();
            centroRecursosDestino.setQuantidadeRecurso(20);

            when(centroComunitarioRepository.findById("centro1")).thenReturn(Optional.of(centroOrigem));
            when(centroComunitarioRepository.findById("centro2")).thenReturn(Optional.of(centroDestino));
            when(recursosRepository.findByCodRecurso(10)).thenReturn(recurso);
            when(centroRecursosRepository.findByIdRecursoCentro(anyString(), anyString())).thenReturn(centroRecursosOrigem);
            when(centroRecursosRepository.save(any(CentroRecursos.class))).thenReturn(new CentroRecursos());
            when(intercambioRepository.save(any(Intercambio.class))).thenReturn(new Intercambio());

            boolean result = intercambioService.realizarIntercambio("centro1", "centro2", List.of(recursoDTO), List.of(recursoDTO));

            assertTrue(result);
        }

        @Test
        public void testRealizarIntercambio_Failure_EmptyCentroOrigem() {
            when(centroComunitarioRepository.findById(anyString())).thenReturn(Optional.empty());

            boolean result = intercambioService.realizarIntercambio("centro1", "centro2", Collections.emptyList(), Collections.emptyList());

            assertFalse(result);
        }

        @Test
        public void testVerificaQuantSuficientes_Failure() {
            CentroRecursoDTO recursoDTO = new CentroRecursoDTO();
            recursoDTO.setCodRecurso(10);
            recursoDTO.setQuantidadeRecurso(10);

            CentroComunitario centro = new CentroComunitario();
            centro.setId("centro1");

            Recursos recurso = new Recursos();
            recurso.setId("recurso1");

            CentroRecursos centroRecursos = new CentroRecursos();
            centroRecursos.setQuantidadeRecurso(5);

            when(recursosRepository.findByCodRecurso(10)).thenReturn(recurso);
            when(centroRecursosRepository.findByIdRecursoCentro("centro1", "recurso1")).thenReturn(centroRecursos);

            RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
                intercambioService.verificaQuantSuficientes(List.of(recursoDTO), centro);
            });

            assertEquals("quantidade de recurso insuficiente para o intercambio", thrown.getMessage());
        }

        @Test
        public void testVerificaPontoOcupacao_Failure() {
            CentroComunitario centroOrigem = new CentroComunitario();
            centroOrigem.setPessoasOcupando(91);
            centroOrigem.setCapacidadeMaxima(100);

            boolean result = intercambioService.verificaPontoOcupacao(100, 90, centroOrigem.getPessoasOcupando() / centroOrigem.getCapacidadeMaxima());

            assertFalse(result);
        }

        @Test
        public void testListarIntercambios() {
            Intercambio intercambio = new Intercambio();
            intercambio.setIdCentroOrigem("centro1");
            intercambio.setIdCentroDestino("centro2");

            when(intercambioRepository.findAll()).thenReturn(List.of(intercambio));

            List<Intercambio> intercambios = intercambioService.listarIntercambios();

            assertEquals(1, intercambios.size());
            assertEquals("centro1", intercambios.get(0).getIdCentroOrigem());
            assertEquals("centro2", intercambios.get(0).getIdCentroDestino());
        }
    }
