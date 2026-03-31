public class EfeitoPeixe extends Efeito {
    // O efeito de peixe aumenta a enregia do jogador em X (acúmulo) para a próxima rodada
    public EfeitoPeixe(int acumulos) {
        super("PEIXE", acumulos); 
    }

    public void usar(Heroi player, RoundManager manager) {
        IO.println("Delícia! " + player.getNome() + " comeu um peixe e aumentou sua energia para a próxima rodada\n");
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
