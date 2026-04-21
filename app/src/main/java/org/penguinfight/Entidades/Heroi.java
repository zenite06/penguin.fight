package org.penguinfight.Entidades;

/**
 * Entidade controlada pelo jogador. Possui um recurso de energia que é 
 * consumido ao utilizar as cartas do baralho.
 */
public class Heroi extends Entidade {
    private int energia;

    public Heroi(String nome, int vida) {
        super(nome, vida, 0);
        this.energia = 100;
    }

    public int getEnergia() {
        return this.energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    /**
     * Deduz o custo da barra do herói após a utilização de uma carta.
     */
    public void usarEnergia(int energia) {
        this.energia -= energia;
    }
}
