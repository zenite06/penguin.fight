package org.penguinfight.Cartas;
import org.penguinfight.App;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Efeitos.EfeitoFaixa;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Carta tática que embute e aplica um status (Efeito) contínuo ou especial 
 * aos combatentes durante a partida.
 */
public class CartaEfeito extends Carta {
    private Efeito efeito;

    public CartaEfeito(String nome, String descricao, int custo, Efeito efeito) {
        super(nome, descricao, custo, efeito.getAcumulos());
        this.efeito = efeito;
    }

    public CartaEfeito(String nome, String descricao, int custo, Efeito efeito, int moedas) {
        super(nome, descricao, custo, efeito.getAcumulos(), moedas);
        this.efeito = efeito;
    }

    public Efeito getEfeito() {
        return this.efeito;
    }

    @Override
    public void usar(Heroi player, Inimigo inimigo) {
        if (player.getEnergia() >= this.getCusto()) {
            Efeito efeito_copia = this.efeito.clonar();
            player.aplicarEfeito(efeito_copia);
            player.usarEnergia(this.getCusto());

            // A cada aplicação é preciso trocar a arte gráfica da faixa
            for (Efeito efeito : player.getEfeitos()) {
                if (efeito.getNome().equals("FAIXA")) {
                    EfeitoFaixa faixa = (EfeitoFaixa) efeito; 
                    faixa.aplicarFaixa();
                    break;
                }
            }
        } else {
            IO.println();
            IO.println(App.ANSI_RED + "Energia insuficiente!" + App.ANSI_RESET);
        }
    }
}
