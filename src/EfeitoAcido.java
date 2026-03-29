public class EfeitoAcido extends Efeito {

    public EfeitoAcido(int acumulos) {
        super("ÁCIDO", acumulos); 
    }

    public void usar(Inimigo inimigo, RoundManager manager) {
        IO.println("Inimigo usou ácido (REESCREVER)");
        manager.getPlayer().receberDano(5);
        
        this.addAcumulos(-1);

        if (this.getAcumulos() == 0) { // O efeito acabou definitivamente
            this.getDono().removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("INIMIGO ATACOU"))
            usar(manager.getInimigo(), manager);
    }
}
