package org.penguinfight.Efeitos;
import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.Entidades.Heroi;

/**
 * Efeito de consumo rápido que restaura a energia do herói para o próximo turno.
 */
public class EfeitoPeixe extends Efeito {
    public EfeitoPeixe(int acumulos) {
        super("PEIXE", acumulos); 
    }

    /**
     * Aumenta a energia do jogador com base na potência do efeito e o descarta imediatamente.
     */
    @Override
    public void ativar(Entidade entidade, RoundManager manager) {
        Heroi player = manager.getPlayer();
        IO.println("Delícia! " + player.getNome() + " comeu um peixe e aumentou sua energia para essa rodada\n");
        player.setEnergia(player.getEnergia() + this.getAcumulos()); 
        
        this.getDono().removerEfeito(this); // O efeito acabou!
        manager.desinscrever(this);
    }

    @Override
    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("FIM DO ROUND"))
            ativar(manager.getPlayer(), manager);
    }

    @Override
    public Efeito clonar() {
        return new EfeitoPeixe(this.getAcumulos());
    }
}
