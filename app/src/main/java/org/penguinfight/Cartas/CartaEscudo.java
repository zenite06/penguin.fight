package org.penguinfight.Cartas;

import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Aumenta o escudo do herói
 */
public class CartaEscudo extends Carta {

    public CartaEscudo(String nome, String descricao, int custo, int valor) {
        super(nome, descricao, custo, valor);
    }

    /**
     * Aumenta o escudo do herói se ele possui energia o suficiente
     */
    public void usar(Heroi heroi, Inimigo inimigo, RoundManager manager) {
        if (heroi.getEnergia() >= this.getCusto()) {
            IO.println();
            IO.println(heroi.getNome() + " se preparou para usar "  + App.ANSI_PURPLE +  this.getNome() + App.ANSI_GREEN + " (+ " + this.getValor() + " de defesa)" + App.ANSI_RESET);
            heroi.ganharEscudo(this.getValor());
            heroi.usarEnergia(this.getCusto());
        } 
        else {
            IO.println();
            IO.println(App.ANSI_RED + "Energia insuficiente!\n" + App.ANSI_RESET);
        }
    }
}
