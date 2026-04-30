package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;
import org.penguinfight.Eventos.Batalha;

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

    public int getDano() {
        return this.dano;
    }

    /**
     * Aplica o dano de ácido ao jogador e consome uma carga do efeito.
     * Remove automaticamente o efeito da entidade caso as cargas cheguem a zero.
     */
    @Override
    public void ativar(Entidade entidade) {
        Batalha batalha = (Batalha) RoundManager.getInstance().getEvento();
        Inimigo inimigo = batalha.getInimigo();
        IO.println(inimigo.getNome() + " jogou ácido em " + entidade.getNome() + "!\n");
        Heroi.getInstance().receberDano(this.dano);
        this.addAcumulos(-1);

        if (this.getAcumulos() == 0) { // O efeito acabou!
            inimigo.removerEfeito(this);
            RoundManager.getInstance().desinscrever(this);
        }
    }

    @Override
    public void serNotificado(String evento) {
        if (evento.equals("INIMIGO ATACOU"))
            ativar(Heroi.getInstance());
    }

    @Override
    public Efeito clonar() {
        return new EfeitoAcido(this.getAcumulos(), this.dano);
    }
}
