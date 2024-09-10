package dsl.jackson.igor.SGCC.model.dto;

public class CentroComunitarioDTO {

    private String id;
    private String nome;
    private String endereco;
    private String localizacao;
    private int capacidadeMaxima;
    private int pessoasOcupando;
    private RecursosDTO recursos;

    // Construtor padrão
    public CentroComunitarioDTO() {
    }

    // Construtor com parâmetros
    public CentroComunitarioDTO(String id, String nome, String endereco, String localizacao,
                                int capacidadeMaxima, int pessoasOcupando, RecursosDTO recursos) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.localizacao = localizacao;
        this.capacidadeMaxima = capacidadeMaxima;
        this.pessoasOcupando = pessoasOcupando;
        this.recursos = recursos;
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

    public RecursosDTO getRecursos() { return recursos; }
    public void setRecursos(RecursosDTO recursos) { this.recursos = recursos; }

    @Override
    public String toString() {
        return "CentroComunitarioDTO{" +
                "id='" + id + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", localizacao='" + localizacao + '\'' +
                ", capacidadeMaxima=" + capacidadeMaxima +
                ", pessoasOcupando=" + pessoasOcupando +
                ", recursos=" + recursos +
                '}';
    }
}