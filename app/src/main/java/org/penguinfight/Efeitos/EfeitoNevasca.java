package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.Entidades.Inimigo;
import org.penguinfight.Eventos.Batalha;

/**
 * Debuff ambiental que reduz temporariamente o poder do ataque iminente do inimigo em 50%.
 */
public class EfeitoNevasca extends Efeito {
    public EfeitoNevasca(int acumulos) {
        super("NEVASCA", acumulos); // Default
    }
    
    /**
     * Aplica a penalidade cortando o dano do ataque planejado pela metade.
     */
    @Override
    public void ativar(Entidade entidade) {
        Batalha batalha = (Batalha) App.manager.getEvento();
        Inimigo inimigo = batalha.getInimigo();
        IO.println("Uma grande nevasca atrapalhou o ataque de " + entidade.getNome() + "...");
        inimigo.getAtaque(inimigo.getDecisao(0)).setValor((int)(inimigo.getAtaque(inimigo.getDecisao(0)).getValor() * 0.5));
        this.addAcumulos(-1);
    }

    @Override
    public void ativarImediato(Entidade entidade) {
        IO.println(this.getDono().getNome() + " invocou uma " + App.ANSI_PURPLE + this.getNome() + App.ANSI_RESET + "!");
    }

    /**
     * Restaura o dano original do ataque do inimigo após a resolução do turno
     * e verifica se a nevasca deve ser dissipada.
     */
    public void resetar(Inimigo inimigo) {
        inimigo.getAtaque(inimigo.getDecisao(0)).setValor((int)(inimigo.getAtaque(inimigo.getDecisao(0)).getValor() * 2));
        if (this.getAcumulos() == 0) { // O efeito acabou!
            this.getDono().removerEfeito(this);
            App.manager.desinscrever(this);
        }
    }

    @Override
    public void serNotificado(String evento) {
        Batalha batalha = (Batalha) App.manager.getEvento();
        if (evento.equals("INIMIGO VAI ATACAR"))
            ativar(batalha.getInimigo());
        else if (evento.equals("INIMIGO ATACOU"))
            resetar(batalha.getInimigo());
    }

    @Override
    public Efeito clonar() {
        return new EfeitoNevasca(this.getAcumulos());
    }
}
