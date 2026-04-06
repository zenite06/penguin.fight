package org.penguinfight.Efeitos;

import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

public class EfeitoCura extends Efeito {

    public EfeitoCura(int acumulos) {
        super("REGENERAÇÃO", acumulos);
    }

    public void usar(Heroi player, Inimigo inimigo, RoundManager manager) {
        IO.println(inimigo.getNome() + " se regenerou e aumentou sua vida!");
        inimigo.setVida(inimigo.getVida() + 5);
        this.addAcumulos(-1);

        if (this.getAcumulos() == 0) { // O efeito acabou!
            inimigo.removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("FIM DO ROUND"))
            usar(manager.getPlayer(), manager.getInimigo(), manager);
    }

    public Efeito clonar() {
        return new EfeitoCura(this.getAcumulos());
    }
}
