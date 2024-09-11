package dsl.jackson.igor.SGCC.model.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "centroRecursos")
public class CentroRecursos {
    @Id
    private String id;
    private String idCentro;
    private String idRecurso;
    private int quantidadeRecurso;

    public CentroRecursos (){}

    public CentroRecursos (String id,String idCentro, String idRecurso, int quantidadeRecurso){
        this.id = id;
        this.idCentro = idCentro;
        this.idRecurso = idRecurso;
        this.quantidadeRecurso = quantidadeRecurso;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdCentro() {
        return idCentro;
    }

    public void setIdCentro(String idCentro) {
        this.idCentro = idCentro;
    }

    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getQuantidadeRecurso() {
        return quantidadeRecurso;
    }

    public void setQuantidadeRecurso(int quantidadeRecurso) {
        this.quantidadeRecurso = quantidadeRecurso;
    }
}