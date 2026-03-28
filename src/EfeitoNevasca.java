public class EfeitoNevasca extends Efeito {
    // O efeito de nevasca reduz o ataque do rival em 50% (o acúmulo representa essa porcentagem) 
    public EfeitoNevasca() {
        super("NEVASCA", 50); // Default
    }
    
    public void usar(Heroi player, Inimigo inimigo) {
        IO.println("Uma grande nevasca atrapalhou o ataque de " + inimigo.getNome() + "...\n");
        inimigo.setDano((int)(inimigo.getDano() * 0.5));
    }
}
