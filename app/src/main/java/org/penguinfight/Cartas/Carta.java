package org.penguinfight.Cartas;

import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Cartas são elementos que fornecem valores para modificar algum atributo e podem ser usadas pelas entidades
 */
public abstract class Carta {
    private String nome;
    private String descricao;
    private int custo;
    private int valor;

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
 * Realiza a ação da carta caso o player tenha energia suficiente
 * @param heroi
 * @param inimigo
 * @param manager
 */
    public abstract void usar(Heroi heroi, Inimigo inimigo, RoundManager manager);
}
