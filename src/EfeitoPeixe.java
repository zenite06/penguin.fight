public class EfeitoPeixe extends Efeito{
    // O efeito de peixe aumenta a enregia do jogador em X (acúmulo) para a próxima rodada
    public EfeitoPeixe(int acumulos) {
        super("PEIXE", acumulos); 
    }

    public void usar(Heroi player, Inimigo inimigo) {
        IO.println("Delícia! " + player.getNome() + " comeu um peixe e aumentou sua energia para a próxima rodada\n");
        player.setEnergia(player.getEnergia() + this.getAcumulos()); 
    }
}
