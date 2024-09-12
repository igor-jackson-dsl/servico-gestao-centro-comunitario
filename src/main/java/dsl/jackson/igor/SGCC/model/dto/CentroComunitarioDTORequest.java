package dsl.jackson.igor.SGCC.model.dto;

import java.util.List;

public class CentroComunitarioDTORequest {
    private String nome;
    private String endereco;
    private String localizacao;
    private int capacidadeMaxima;
    private int pessoasOcupando;
    private List<CentroRecursoDTO> recursos;

    // Construtor padrão
    public CentroComunitarioDTORequest() {
    }

    // Construtor com parâmetros
    public CentroComunitarioDTORequest( String nome, String endereco, String localizacao,
                                int capacidadeMaxima, int pessoasOcupando, List<CentroRecursoDTO> recursos) {
        this.nome = nome;
        this.endereco = endereco;
        this.localizacao = localizacao;
        this.capacidadeMaxima = capacidadeMaxima;
        this.pessoasOcupando = pessoasOcupando;
        this.recursos = recursos;
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public int getCapacidadeMaxima() {
        return capacidadeMaxima;
    }

    public void setCapacidadeMaxima(int capacidadeMaxima) {
        this.capacidadeMaxima = capacidadeMaxima;
    }

    public int getPessoasOcupando() {
        return pessoasOcupando;
    }

    public void setPessoasOcupando(int pessoasOcupando) {
        this.pessoasOcupando = pessoasOcupando;
    }

    public List<CentroRecursoDTO> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<CentroRecursoDTO> recursos) {
        this.recursos = recursos;
    }

    @Override
    public String toString() {
        return "CentroComunitarioDTO{" +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", capacidadeMaxima=" + capacidadeMaxima +
                ", pessoasOcupando=" + pessoasOcupando +
                ", recursos=" + recursos +
                '}';
    }

}