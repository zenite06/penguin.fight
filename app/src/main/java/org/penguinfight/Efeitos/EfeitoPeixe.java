package org.penguinfight.Efeitos;

import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Heroi;

/**
 * Aumenta a energia do jogador em X (acúmulo) para a próxima rodada
 */
public class EfeitoPeixe extends Efeito {
    public EfeitoPeixe(int acumulos) {
        super("PEIXE", acumulos); 
    }

    public void usar(Heroi player, RoundManager manager) {
        IO.println("Delícia! " + player.getNome() + " comeu um peixe e aumentou sua energia para essa rodada\n");
        player.setEnergia(player.getEnergia() + this.getAcumulos()); 
        
        this.getDono().removerEfeito(this); // O efeito acabou!
        manager.desinscrever(this);
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("FIM DO ROUND"))
            usar(manager.getPlayer(), manager);
    }

    public Efeito clonar() {
        return new EfeitoPeixe(this.getAcumulos());
    }
}
