package dsl.jackson.igor.SGCC.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "intercambio")
public class Intercambio {

    @Id
    private String id;
    private String idCentroOrigem;
    private String idCentroDestino;
    private List<CentroRecursos> recursosOrigem;
    private List<CentroRecursos> recursosDestino;
    private int pontosTotais;
    private LocalDateTime dataHora;

    // Construtor padrão
    public Intercambio() {}

    // Construtor com parâmetros
    public Intercambio(String idCentroOrigem, String idCentroDestino, List<CentroRecursos> recursosOrigem,
                       List<CentroRecursos> recursosDestino, int pontosTotais, LocalDateTime dataHora) {
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

    public List<CentroRecursos> getRecursosOrigem() {
        return recursosOrigem;
    }

    public void setRecursosOrigem(List<CentroRecursos> recursosOrigem) {
        this.recursosOrigem = recursosOrigem;
    }

    public List<CentroRecursos> getRecursosDestino() {
        return recursosDestino;
    }

    public void setRecursosDestino(List<CentroRecursos> recursosDestino) {
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