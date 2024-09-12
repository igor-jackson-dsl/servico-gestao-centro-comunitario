package dsl.jackson.igor.SGCC.model.dto;

public class RecursoSomaDTO {
    private String idRecurso;
    private int totalQuantidade;

    // Getters e Setters
    public String getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(String idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getTotalQuantidade() {
        return totalQuantidade;
    }

    public void setTotalQuantidade(int totalQuantidade) {
        this.totalQuantidade = totalQuantidade;
    }
}
