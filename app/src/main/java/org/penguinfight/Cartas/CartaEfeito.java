package org.penguinfight.Cartas;

import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Efeitos.EfeitoFaixa;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Aplica um efeito na batalha
 */
public class CartaEfeito extends Carta {
    private Efeito efeito;

    public CartaEfeito(String nome, String descricao, int custo, Efeito efeito) {
        super(nome, descricao, custo, efeito.getAcumulos());
        this.efeito = efeito;
    }

    public Efeito getEfeito() {
        return this.efeito;
    }

    public void usar(Heroi player, Inimigo inimigo, RoundManager manager) {
        if (player.getEnergia() >= this.getCusto()) {
            IO.println();
            if (this.efeito.getNome().equals("NEVASCA")) // Mensagem do uso (já que a nevasca não tem ação imediata)
                IO.println(player.getNome() + " invocou uma " + App.ANSI_PURPLE + this.getNome() + App.ANSI_RESET + "!");

            Efeito efeito_copia = this.efeito.clonar();
            player.aplicarEfeito(efeito_copia, manager);

            if (this.efeito.getNome().equals("FAIXA")) {
                IO.println(player.getNome() + " treinou técnicas mais avançadas e aumentou sua faixa!\n");
                player.setEscudo(player.getEscudo() + (2 * this.efeito.getAcumulos()));

                for (Efeito efeito : player.getEfeitos()) {
                    if (efeito.getNome().equals("FAIXA")) {
                        EfeitoFaixa faixa = (EfeitoFaixa) efeito; 
                        faixa.aplicarFaixa(manager);
                        break;
                    }
                }
            }

            if (this.efeito.getNome().equals("FAIXA")) {
                IO.println(player.getNome() + " treinou técnicas mais avançadas e aumentou sua faixa!\n");
                player.setEscudo(player.getEscudo() + (2 * this.efeito.getAcumulos()));

                for (Efeito efeito : player.getEfeitos()) {
                    if (efeito.getNome().equals("FAIXA")) {
                        EfeitoFaixa faixa = (EfeitoFaixa) efeito; 
                        faixa.aplicarFaixa(manager);
                        break;
                    }
                }
            }

            if (this.efeito.getNome().equals("REGENERAÇÃO")) 
                this.efeito.usar(this.efeito.getDono(), manager);

            player.usarEnergia(this.getCusto());
        } else {
            IO.println();
            IO.println(App.ANSI_RED + "Energia insuficiente!" + App.ANSI_RESET);
        }
    }
}
