public abstract class Carta {
    private String nome;
    private String descricao;
    private int custo;

    public Carta(String nome, String descricao, int custo) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getCusto() {
        return this.custo;
    }

    public abstract void usar(Heroi heroi, Inimigo inimigo);
}
