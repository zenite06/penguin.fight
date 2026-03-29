public class EfeitoNevasca extends Efeito {
    // O efeito de nevasca reduz o ataque do rival em 50% (o acúmulo representa essa porcentagem) 
    public EfeitoNevasca() {
        super("NEVASCA", 1); // Default
    }
    
    public void usar(Heroi player, Inimigo inimigo, RoundManager manager) {
        IO.println("Uma grande nevasca atrapalhou o ataque de " + inimigo.getNome() + "...\n");
        inimigo.setDano((int)(inimigo.getDano() * 0.5));
        this.addAcumulos(-1);

        if (this.getAcumulos() == 0) { // O efeito acabou definitivamente
            this.getDono().removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("INIMIGO VAI ATACAR"))
            usar(manager.getPlayer(), manager.getInimigo(), manager);
    }
}
