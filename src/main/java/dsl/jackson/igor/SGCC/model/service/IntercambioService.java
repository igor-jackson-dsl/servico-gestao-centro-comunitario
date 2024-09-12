package dsl.jackson.igor.SGCC.model.service;

import dsl.jackson.igor.SGCC.model.dto.CentroRecursoDTO;
import dsl.jackson.igor.SGCC.model.entity.Intercambio;

import java.util.List;

public interface IntercambioService {
    boolean realizarIntercambio(String idCentroOrigem, String idCentroDestino, List<CentroRecursoDTO> recursosCentroOrigem, List<CentroRecursoDTO> recursosCentroDestino);
    List<Intercambio> listarIntercambios();
}