package dsl.jackson.igor.SGCC.model.service;

import dsl.jackson.igor.SGCC.model.dto.CentroComunitarioDTO;
import dsl.jackson.igor.SGCC.model.entity.CentroComunitario;

import java.util.List;

public interface CentroComunitarioService {

    CentroComunitario criarCentroComunitario(CentroComunitarioDTO centro);

    boolean atualizarOcupacao(String id, int novaOcupacao);

    List<CentroComunitario>listarCentros();

}