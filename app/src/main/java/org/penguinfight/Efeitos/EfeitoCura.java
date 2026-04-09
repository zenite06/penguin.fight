package org.penguinfight.Efeitos;

import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;

/**
 * Regenera parte da vida da entidade
 */
public class EfeitoCura extends Efeito {

    public EfeitoCura(int acumulos) {
        super("REGENERAÇÃO", acumulos);
    }

    public void usar(Entidade entidade, RoundManager manager) { 
        IO.println(entidade.getNome() + " se curou e aumentou sua vida!");
        entidade.setVida(entidade.getVida() + this.getAcumulos());
        this.addAcumulos(-this.getAcumulos());

        if (this.getAcumulos() == 0) { // O efeito acabou!
            entidade.removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("FIM DO ROUND"))
            if (this.getDono() == manager.getInimigo())
                usar(this.getDono(), manager);
    }

    public Efeito clonar() {
        return new EfeitoCura(this.getAcumulos());
    }
}
