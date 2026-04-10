package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.Entidades.Inimigo;

/**
 * Reduz em 50% o ataque do rival
 */
public class EfeitoNevasca extends Efeito {
    public EfeitoNevasca(int acumulos) {
        super("NEVASCA", acumulos); // Default
    }
    
    public void ativar(Entidade entidade, RoundManager manager) {
        Inimigo inimigo = manager.getInimigo();
        IO.println("Uma grande nevasca atrapalhou o ataque de " + entidade.getNome() + "...");
        inimigo.getAtaque(inimigo.getDecisao(0)).setValor((int)(inimigo.getAtaque(inimigo.getDecisao(0)).getValor() * 0.5));
        this.addAcumulos(-1);
    }

    @Override
    public void ativarImediato(Entidade entidade, RoundManager manager) {
        IO.println(this.getDono().getNome() + " invocou uma " + App.ANSI_PURPLE + this.getNome() + App.ANSI_RESET + "!");
    }

    public void resetar(Inimigo inimigo, RoundManager manager) {
        inimigo.getAtaque(inimigo.getDecisao(0)).setValor((int)(inimigo.getAtaque(inimigo.getDecisao(0)).getValor() * 2));
        if (this.getAcumulos() == 0) { // O efeito acabou!
            this.getDono().removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("INIMIGO VAI ATACAR"))
            ativar(manager.getInimigo(), manager);
        else if (evento.equals("INIMIGO ATACOU"))
            resetar(manager.getInimigo(), manager);
    }

    public Efeito clonar() {
        return new EfeitoNevasca(this.getAcumulos());
    }
}
