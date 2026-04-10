package org.penguinfight.Efeitos;
import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.Entidades.Inimigo;

/**
 * Aplica dano na entidade rival ao final do turno por um número de turnos
 */
public class EfeitoAcido extends Efeito {
    private int dano;

    public EfeitoAcido(int acumulos, int dano) {
        super("ÁCIDO", acumulos);
        this.dano = dano;
    }

    /**
     * Aplica dano na entidade rival ao final do turno por um número de turnos se a entidade possui energia o suficiente
     */
    public void ativar(Entidade entidade, RoundManager manager) {
        Inimigo inimigo = manager.getInimigo();
        IO.println(inimigo.getNome() + " jogou ácido em " + entidade.getNome() + "!\n");
        manager.getPlayer().receberDano(this.dano);
        this.addAcumulos(-1);

        if (this.getAcumulos() == 0) { // O efeito acabou!
            inimigo.removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("INIMIGO ATACOU"))
            ativar(manager.getPlayer(), manager);
    }

    public Efeito clonar() {
        return new EfeitoAcido(this.getAcumulos(), this.dano);
    }
}
