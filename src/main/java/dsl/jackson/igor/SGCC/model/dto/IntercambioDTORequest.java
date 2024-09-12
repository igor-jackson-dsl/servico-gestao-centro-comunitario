package dsl.jackson.igor.SGCC.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class IntercambioDTORequest {

    private String idCentroOrigem;
    private String idCentroDestino;
    private List<CentroRecursoDTO> recursosOrigem;
    private List<CentroRecursoDTO> recursosDestino;
    private LocalDateTime dataHora;

    // Construtor padrão
    public IntercambioDTORequest() {}

    // Construtor com parâmetros
    public IntercambioDTORequest( String idCentroOrigem, String idCentroDestino,
                          List<CentroRecursoDTO> recursosOrigem, List<CentroRecursoDTO> recursosDestino,
                          int pontosTotais, LocalDateTime dataHora) {

        this.idCentroOrigem = idCentroOrigem;
        this.idCentroDestino = idCentroDestino;
        this.recursosOrigem = recursosOrigem;
        this.recursosDestino = recursosDestino;
        this.dataHora = dataHora;
    }

    public String getIdCentroOrigem() {
        return idCentroOrigem;
    }

    public void setIdCentroOrigem(String idCentroOrigem) {
        this.idCentroOrigem = idCentroOrigem;
    }

    public String getIdCentroDestino() {
        return idCentroDestino;
    }

    public void setIdCentroDestino(String idCentroDestino) {
        this.idCentroDestino = idCentroDestino;
    }

    public List<CentroRecursoDTO> getRecursosOrigem() {
        return recursosOrigem;
    }

    public void setRecursosOrigem(List<CentroRecursoDTO> recursosOrigem) {
        this.recursosOrigem = recursosOrigem;
    }

    public List<CentroRecursoDTO> getRecursosDestino() {
        return recursosDestino;
    }

    public void setRecursosDestino(List<CentroRecursoDTO> recursosDestino) {
        this.recursosDestino = recursosDestino;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
