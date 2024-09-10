package dsl.jackson.igor.SGCC.controller;

import dsl.jackson.igor.SGCC.model.dto.CentroComunitarioDTO;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;
import dsl.jackson.igor.SGCC.model.service.CentroComunitarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/centros")
@Tag(name = "Centro Comunitário", description = "Operações relacionadas aos centros comunitários")
public class CentroComunitarioController {

    CentroComunitarioService centroComunitarioService;

    CentroComunitarioController (CentroComunitarioService service){
        this.centroComunitarioService = service;
    }

    @Operation(summary = "Adicionar um novo centro comunitário", description = "Cria um novo centro comunitário com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Centro comunitário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados fornecidos são inválidos")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CentroComunitario> adicionarCentroComunitario(@RequestBody CentroComunitarioDTO centroDTO) {
        CentroComunitario centroComunitario = centroComunitarioService.criarCentroComunitario(centroDTO);
        return new ResponseEntity<>(centroComunitario, HttpStatus.CREATED);
    }

    @Operation(summary = "Adicionar um novo centro comunitário", description = "Cria um novo centro comunitário com os dados fornecidos.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Centro comunitário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados fornecidos são inválidos")
    })
    @PutMapping(value = "/{id}/atualizar-ocupacao")
    public ResponseEntity<String> atualizarOcupacao(@PathVariable String id, @RequestParam int novaOcupacao) {
        boolean notificacaoGerada = centroComunitarioService.atualizarOcupacao(id, novaOcupacao);
        if (notificacaoGerada) {
            return ResponseEntity.ok("Ocupação atualizada e notificação gerada.");
        } else {
            return ResponseEntity.ok("Ocupação atualizada.");
        }
    }

}