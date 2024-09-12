package dsl.jackson.igor.SGCC.model.dto;

import java.time.LocalDateTime;
import java.util.List;

public class IntercambioDTO {

    private String id;
    private String idCentroOrigem;
    private String idCentroDestino;
    private List<CentroRecursoDTO> recursosOrigem;
    private List<CentroRecursoDTO> recursosDestino;
    private int pontosTotais;
    private LocalDateTime dataHora;

    // Construtor padrão
    public IntercambioDTO() {}

    // Construtor com parâmetros
    public IntercambioDTO(String id, String idCentroOrigem, String idCentroDestino,
                          List<CentroRecursoDTO> recursosOrigem, List<CentroRecursoDTO> recursosDestino,
                          int pontosTotais, LocalDateTime dataHora) {
        this.id = id;
        this.idCentroOrigem = idCentroOrigem;
        this.idCentroDestino = idCentroDestino;
        this.recursosOrigem = recursosOrigem;
        this.recursosDestino = recursosDestino;
        this.pontosTotais = pontosTotais;
        this.dataHora = dataHora;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public int getPontosTotais() {
        return pontosTotais;
    }

    public void setPontosTotais(int pontosTotais) {
        this.pontosTotais = pontosTotais;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
