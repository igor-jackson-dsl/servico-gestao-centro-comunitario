package dsl.jackson.igor.SGCC.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "intercambio")
public class Intercambio {
    private String id;
    private String centroOrigemId;
    private String centroDestinoId;
    private Recursos recursosOferecidos;
    private Recursos recursosRecebidos;
    private LocalDateTime dataNegociacao;

    //Construtor padrão
    public Intercambio(){}

    //Construtor com parametros
    public Intercambio(String id, String centroOrigemId, String centroDestinoId,
                      Recursos recursosOferecidos, Recursos recursosRecebidos,
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

    public Recursos getRecursosOferecidos() { return recursosOferecidos; }
    public void setRecursosOferecidos(Recursos recursosOferecidos) { this.recursosOferecidos = recursosOferecidos; }

    public Recursos getRecursosRecebidos() { return recursosRecebidos; }
    public void setRecursosRecebidos(Recursos recursosRecebidos) { this.recursosRecebidos = recursosRecebidos; }

    public LocalDateTime getDataNegociacao() { return dataNegociacao; }
    public void setDataNegociacao(LocalDateTime dataNegociacao) { this.dataNegociacao = dataNegociacao; }

    @Override
    public String toString() {
        return "Negociacao{" +
                "id='" + id + '\'' +
                ", centroOrigemId='" + centroOrigemId + '\'' +
                ", centroDestinoId='" + centroDestinoId + '\'' +
                ", recursosOferecidos=" + recursosOferecidos +
                ", recursosRecebidos=" + recursosRecebidos +
                ", dataNegociacao=" + dataNegociacao +
                '}';
    }
}