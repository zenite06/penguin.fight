import java.util.List;
import java.util.ArrayList;

public class RoundManager {
    // Nessa classe deverá ser desenvovlvida a lógica de batalha dos métodos do App atuais

    private Entidade player;
    private Entidade inimigo;
    private List<Efeito> efeitos_ativos; // Efeitos serão os subscribers desse publisher!

    public RoundManager(Entidade player, Entidade inimigo) {
        this.player = player;
        this.inimigo = inimigo;
        this.efeitos_ativos = new ArrayList<Efeito>();
    }

    public void inscrever(Efeito efeito) {
        efeitos_ativos.add(efeito)
    }

    public void desinscrever(int i) {
        efeitos_ativos.remove(i);
    }

    public void notificar() {
        if (!player.estaVivo() || !inimigo.estaVivo()) {
            for (int i = 0; i < efeitos_ativos.size(); i++) 
                efeitos_ativos[i].serNotificado();
        }
    }

}
