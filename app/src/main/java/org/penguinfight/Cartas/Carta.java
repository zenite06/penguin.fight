package org.penguinfight.Cartas;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Classe abstrata base para todas as ações do baralho.
 * Cartas consomem energia do herói para gerar um impacto no combate.
 */
public abstract class Carta {
    private String nome;
    private String descricao;
    private int custo;
    private int valor;

    /**
     * Executa a mecânica principal da carta caso o herói possua energia suficiente.
     */
    public Carta(String nome, String descricao, int custo, int valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.custo = custo;
        this.valor = valor;
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

    public int getValor() {
        return this.valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    /**
     * Executa a mecânica principal da carta caso o herói possua energia suficiente.
     */
    public abstract void usar(Heroi heroi, Inimigo inimigo);
}
