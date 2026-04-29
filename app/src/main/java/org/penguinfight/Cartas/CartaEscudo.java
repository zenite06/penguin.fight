package org.penguinfight.Cartas;
import org.penguinfight.App;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Carta de defesa que gera uma barreira temporária para mitigar 
 * ou anular os danos recebidos no turno atual.
 */
public class CartaEscudo extends Carta {

    public CartaEscudo(String nome, String descricao, int custo, int valor) {
        super(nome, descricao, custo, valor);
    }

    public CartaEscudo(String nome, String descricao, int custo, int valor, int moedas) {
        super(nome, descricao, custo, valor, moedas);
    }

    @Override
    public void usar(Heroi heroi, Inimigo inimigo) {
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
