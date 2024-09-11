package dsl.jackson.igor.SGCC.model.dto;

public class CentroRecursoDTO {

    private int codRecurso;
    private int quantidadeRecurso;

    public CentroRecursoDTO (){}

    public CentroRecursoDTO (int codRecurso, int quantidadeRecurso){
        this.codRecurso = codRecurso;
        this.quantidadeRecurso = quantidadeRecurso;
    }

    public int getCodRecurso() {
        return codRecurso;
    }

    public void setCodRecurso(int codRecurso) {
        this.codRecurso = codRecurso;
    }

    public int getQuantidadeRecurso() {
        return quantidadeRecurso;
    }

    public void setQuantidadeRecurso(int quantidadeRecurso) {
        this.quantidadeRecurso = quantidadeRecurso;
    }
}