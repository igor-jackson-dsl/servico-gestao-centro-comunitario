package dsl.jackson.igor.SGCC.model.service;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public interface RelatorioService {

    byte[] gerarRelatorioOcupacao();

    byte[] gerarRelatorioRecursos();

    byte[] gerarRelatorioIntercambio(String dataHoraInicio);

}