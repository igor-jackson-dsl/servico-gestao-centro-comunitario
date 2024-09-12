package dsl.jackson.igor.SGCC.controller;

import dsl.jackson.igor.SGCC.model.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    private final RelatorioService relatorioService;


    public RelatorioController(RelatorioService relatorioService) {
        this.relatorioService = relatorioService;
    }

    @Operation(summary = "Relatorio de Ocupação", description = "Lista todos os centros comunitarios com ocupação maior que 90%.")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Relatorio gerado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados fornecidos são inválidos")})
    @GetMapping(value = "/relatorio-ocupacao", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> gerarRelatorioCentrosComOcupacaoMaiorQue90() {


            // Gerar o relatório em PDF
           byte[] relatorio = relatorioService.gerarRelatorioOcupacao();

           HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio_ocupacao.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Retorna o PDF como ResponseEntity
            return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);

    }

    @Operation(summary = "Relatorio de Recursos", description = "Lista os recursos utilizados pelos centros e mostra a media de utilização")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Relatorio gerado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados fornecidos são inválidos")})
    @GetMapping(value = "/relatorio-recursos", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> gerarRelatorioDeRecursos() {


            // Gerar o relatório em PDF
           byte[] relatorio = relatorioService.gerarRelatorioRecursos();

           HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio_recursos.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Retorna o PDF como ResponseEntity
            return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);

    }
    @Operation(summary = "Relatorio de Intercambio", description = "Lista os intercambios realizados, filtra por data(opcional)")
    @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Relatorio gerado com sucesso"),
    @ApiResponse(responseCode = "400", description = "Dados fornecidos são inválidos")})
    @GetMapping(value = "/relatorio-intercambio", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> gerarRelatorioIntercambio(@RequestParam(required = false) String dataHoraInicio) {


            // Gerar o relatório em PDF
           byte[] relatorio = relatorioService.gerarRelatorioIntercambio(dataHoraInicio);

           HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "relatorio_intercambio.pdf");
            headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");

            // Retorna o PDF como ResponseEntity
            return new ResponseEntity<>(relatorio, headers, HttpStatus.OK);

    }

}
