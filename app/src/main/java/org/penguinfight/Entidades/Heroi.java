package org.penguinfight.Entidades;

/**
 * Entidade controlada pelo jogador. Possui um recurso de energia que é 
 * consumido ao utilizar as cartas do baralho.
 */
public class Heroi extends Entidade {
    private int energia;
    private int moedas;

    public Heroi(String nome, int vida) {
        super(nome, vida, 0);
        this.energia = 100;
        this.moedas = 0;
    }

    public int getEnergia() {
        return this.energia;
    }

    public int getMoedas() {
        return this.moedas;
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

    public void usarMoedas(int moedas) {
        if (moedas >= this.moedas) 
            this.moedas -= moedas;
    }

    public void ganharMoedas(int moedas) {
        this.moedas += moedas;
    }
}
