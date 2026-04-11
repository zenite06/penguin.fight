package org.penguinfight.Efeitos;
import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.Entidades.Inimigo;

/**
 * Efeito que regenera parte da vida da entidade de forma imediata ou periódica.
 */
public class EfeitoCura extends Efeito {

    public EfeitoCura(int acumulos) {
        super("REGENERAÇÃO", acumulos);
    }

    @Override
    public void ativar(Entidade entidade, RoundManager manager) { 
        ativarImediato(entidade, manager);
    }

    @Override
    public void ativarImediato(Entidade entidade, RoundManager manager) {
        IO.println(entidade.getNome() + " se curou e aumentou sua vida!");
        entidade.setVida(entidade.getVida() + this.getAcumulos());
        this.addAcumulos(- this.getAcumulos()); // Consome toda a cura de uma vez

        if (this.getAcumulos() == 0) { // O efeito acabou!
            entidade.removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    @Override
    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("FIM DO ROUND") && this.getDono() instanceof Inimigo)
            ativar(this.getDono(), manager);
    }

    @Override
    public Efeito clonar() {
        return new EfeitoCura(this.getAcumulos());
    }
}
