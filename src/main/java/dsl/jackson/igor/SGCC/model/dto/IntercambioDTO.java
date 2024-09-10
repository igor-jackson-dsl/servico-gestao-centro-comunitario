package dsl.jackson.igor.SGCC.model.dto;

import java.time.LocalDateTime;

public class IntercambioDTO {

    private String id;
    private String centroOrigemId;
    private String centroDestinoId;
    private RecursosDTO recursosOferecidos;
    private RecursosDTO recursosRecebidos;
    private LocalDateTime dataNegociacao;

    // Construtor padrão
    public IntercambioDTO() {
    }

    // Construtor com parâmetros
    public IntercambioDTO(String id, String centroOrigemId, String centroDestinoId,
                          RecursosDTO recursosOferecidos, RecursosDTO recursosRecebidos,
                          LocalDateTime dataNegociacao) {
        this.id = id;
        this.centroOrigemId = centroOrigemId;
        this.centroDestinoId = centroDestinoId;
        this.recursosOferecidos = recursosOferecidos;
        this.recursosRecebidos = recursosRecebidos;
        this.dataNegociacao = dataNegociacao;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCentroOrigemId() { return centroOrigemId; }
    public void setCentroOrigemId(String centroOrigemId) { this.centroOrigemId = centroOrigemId; }

    public String getCentroDestinoId() { return centroDestinoId; }
    public void setCentroDestinoId(String centroDestinoId) { this.centroDestinoId = centroDestinoId; }

    public RecursosDTO getRecursosOferecidos() { return recursosOferecidos; }
    public void setRecursosOferecidos(RecursosDTO recursosOferecidos) { this.recursosOferecidos = recursosOferecidos; }

    public RecursosDTO getRecursosRecebidos() { return recursosRecebidos; }
    public void setRecursosRecebidos(RecursosDTO recursosRecebidos) { this.recursosRecebidos = recursosRecebidos; }

    public LocalDateTime getDataNegociacao() { return dataNegociacao; }
    public void setDataNegociacao(LocalDateTime dataNegociacao) { this.dataNegociacao = dataNegociacao; }

    @Override
    public String toString() {
        return "IntercambioDTO{" +
                "id='" + id + '\'' +
                ", centroOrigemId='" + centroOrigemId + '\'' +
                ", centroDestinoId='" + centroDestinoId + '\'' +
                ", recursosOferecidos=" + recursosOferecidos +
                ", recursosRecebidos=" + recursosRecebidos +
                ", dataNegociacao=" + dataNegociacao +
                '}';
    }
}