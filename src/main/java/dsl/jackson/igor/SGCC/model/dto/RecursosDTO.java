package dsl.jackson.igor.SGCC.model.dto;

public class RecursosDTO {

    private String id;
    private String nome;
    private int pontos;

    // Construtor padrão
    public RecursosDTO() {}

    // Construtor com parâmetros
    public RecursosDTO(String id, String nome, int pontos) {
        this.id = id;
        this.nome = nome;
        this.pontos = pontos;
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "RecursosDTO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", pontos=" + pontos +
                '}';
    }
}

