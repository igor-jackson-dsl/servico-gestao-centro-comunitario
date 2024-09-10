package dsl.jackson.igor.SGCC.model.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "recursos")
public class Recursos {

    private int medicos;
    private int voluntarios;
    private int kitsSuprimentos;
    private int veiculos;
    private int cestasBasicas;

    //Construtor padrão
    public Recursos() {}

    //Construtor com parâmetros
    public Recursos(int medicos, int voluntarios, int kitsSuprimentos, int veiculos, int cestasBasicas) {
        this.medicos = medicos;
        this.voluntarios = voluntarios;
        this.kitsSuprimentos = kitsSuprimentos;
        this.veiculos = veiculos;
        this.cestasBasicas = cestasBasicas;
    }

    // Getters e Setters
    public int getMedicos() { return medicos; }
    public void setMedicos(int medicos) { this.medicos = medicos; }

    public int getVoluntarios() { return voluntarios; }
    public void setVoluntarios(int voluntarios) { this.voluntarios = voluntarios; }

    public int getKitsSuprimentos() { return kitsSuprimentos; }
    public void setKitsSuprimentos(int kitsSuprimentos) { this.kitsSuprimentos = kitsSuprimentos; }

    public int getVeiculos() { return veiculos; }
    public void setVeiculos(int veiculos) { this.veiculos = veiculos; }

    public int getCestasBasicas() { return cestasBasicas; }
    public void setCestasBasicas(int cestasBasicas) { this.cestasBasicas = cestasBasicas; }

    @Override
    public String toString() {
        return "Recursos{" +
                "medicos=" + medicos +
                ", voluntarios=" + voluntarios +
                ", kitsSuprimentos=" + kitsSuprimentos +
                ", veiculos=" + veiculos +
                ", cestasBasicas=" + cestasBasicas +
                '}';
    }
}