package org.penguinfight.Efeitos;

import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Aplica um dano ao ser jogado em um rival
 */
public class EfeitoAcido extends Efeito {
    private int dano;

    public EfeitoAcido(int acumulos, int dano) {
        super("ÁCIDO", acumulos);
        this.dano = dano;
    }

    public void usar(Heroi player, Inimigo inimigo, RoundManager manager) {
        IO.println(inimigo.getNome() + " jogou ácido em " + player.getNome() + "!\n");
        manager.getPlayer().receberDano(this.dano);
        this.addAcumulos(-1);

        if (this.getAcumulos() == 0) { // O efeito acabou!
            inimigo.removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("INIMIGO ATACOU"))
            usar(manager.getPlayer(), manager.getInimigo(), manager);
    }

    public Efeito clonar() {
        return new EfeitoAcido(this.getAcumulos(), this.dano);
    }
}
