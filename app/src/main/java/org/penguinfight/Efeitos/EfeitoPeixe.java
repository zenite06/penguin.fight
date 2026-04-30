package org.penguinfight.Efeitos;
import org.penguinfight.App;
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
    public void ativar(Entidade entidade) {
        Heroi player = Heroi.getInstance();
        IO.println("Delícia! " + player.getNome() + " comeu um peixe e aumentou sua energia para essa rodada\n");
        player.setEnergia(player.getEnergia() + this.getAcumulos()); 
        
        this.getDono().removerEfeito(this); // O efeito acabou!
        RoundManager.getInstance().desinscrever(this);
    }

    @Override
    public void serNotificado(String evento) {
        if (evento.equals("FIM DO ROUND"))
            ativar(Heroi.getInstance());
    }

    @Override
    public Efeito clonar() {
        return new EfeitoPeixe(this.getAcumulos());
    }
}

