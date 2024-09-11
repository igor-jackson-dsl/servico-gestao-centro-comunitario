package dsl.jackson.igor.SGCC.controller;

import dsl.jackson.igor.SGCC.model.dto.CentroRecursoDTO;
import dsl.jackson.igor.SGCC.model.dto.IntercambioDTO;
import dsl.jackson.igor.SGCC.model.entity.Intercambio;
import dsl.jackson.igor.SGCC.model.service.IntercambioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/intercambios")
public class IntercambioController {

    IntercambioService intercambioService;

    public IntercambioController(IntercambioService intercambioService) {
        this.intercambioService = intercambioService;
    }

    // Endpoint para realizar um intercâmbio
    @PostMapping(value = "/realizar", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> realizarIntercambio(
        @RequestBody IntercambioDTO intercambioDTO) {

        String idCentroOrigem = intercambioDTO.getIdCentroOrigem();
        String idCentroDestino = intercambioDTO.getIdCentroDestino();
        List<CentroRecursoDTO> recursosCentroOrigem = intercambioDTO.getRecursosOrigem();
        List<CentroRecursoDTO> recursosCentroDestino = intercambioDTO.getRecursosDestino();

        boolean sucesso = intercambioService.realizarIntercambio(idCentroOrigem, idCentroDestino, recursosCentroOrigem, recursosCentroDestino);

        if (sucesso) {
            return ResponseEntity.ok("Intercâmbio realizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao realizar intercâmbio. Verifique os parâmetros.");
        }
    }

    // Endpoint para listar todos os intercâmbios
    @GetMapping(value = "listar")
    public ResponseEntity<List<Intercambio>> listarIntercambios() {
        List<Intercambio> intercambios = intercambioService.listarIntercambios();
        return ResponseEntity.ok(intercambios);
    }
}
