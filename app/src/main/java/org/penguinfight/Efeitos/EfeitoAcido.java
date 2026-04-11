package org.penguinfight.Efeitos;
import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.Entidades.Inimigo;

/**
 * Debuff reativo que causa dano de reflexo/periódico na entidade adversária
 * sempre que o inimigo realiza um ataque.
 */
public class EfeitoAcido extends Efeito {
    private int dano;

    public EfeitoAcido(int acumulos, int dano) {
        super("ÁCIDO", acumulos);
        this.dano = dano;
    }

    /**
     * Aplica o dano de ácido ao jogador e consome uma carga do efeito.
     * Remove automaticamente o efeito da entidade caso as cargas cheguem a zero.
     */
    @Override
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

    @Override
    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("INIMIGO ATACOU"))
            ativar(manager.getPlayer(), manager);
    }

    @Override
    public Efeito clonar() {
        return new EfeitoAcido(this.getAcumulos(), this.dano);
    }
}
