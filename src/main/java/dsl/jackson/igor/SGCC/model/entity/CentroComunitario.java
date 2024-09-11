package dsl.jackson.igor.SGCC.model.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "centros")
public class CentroComunitario {

    @Id
    private String id;
    private String nome;
    private String endereco;
    private String localizacao;
    private int capacidadeMaxima;
    private int pessoasOcupando;

    // Construtor padrão
    public CentroComunitario() {
    }

    // Construtor com parâmetros
    public CentroComunitario(String id, String nome, String endereco, String localizacao,
                                   int capacidadeMaxima, int pessoasOcupando) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.localizacao = localizacao;
        this.capacidadeMaxima = capacidadeMaxima;
        this.pessoasOcupando = pessoasOcupando;
    }

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public int getCapacidadeMaxima() { return capacidadeMaxima; }
    public void setCapacidadeMaxima(int capacidadeMaxima) { this.capacidadeMaxima = capacidadeMaxima; }

    public int getPessoasOcupando() { return pessoasOcupando; }
    public void setPessoasOcupando(int pessoasOcupando) { this.pessoasOcupando = pessoasOcupando; }

    @Override
    public String toString() {
        return "CentroComunitario{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", capacidadeMaxima=" + capacidadeMaxima +
                ", pessoasOcupando=" + pessoasOcupando +
                '}';
    }
}
