package dsl.jackson.igor.SGCC.model.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recursos")
public class Recursos {

    @Id
    private String id;
    private int codRecurso;
    private String nome;
    private int pontos;

    //Construtor padrão
    public Recursos(){}

    //Construtor com parâmetros
    public Recursos(String id,int codRecurso, String nome, int pontos) {
        this.id = id;
        this.codRecurso = codRecurso;
        this.nome = nome;
        this.pontos = pontos;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCodRecurso() {
        return codRecurso;
    }

    public void setCodRecurso(int codRecurso) {
        this.codRecurso = codRecurso;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}