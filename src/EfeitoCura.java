public class EfeitoCura extends Efeito {
    public EfeitoCura(int acumulos) {
        super("KIT MÉDICO", acumulos); 
    }

    public void usar(Entidade dono, RoundManager manager) {
        IO.println("Fulano usou kit médico (REESCREVER)");
        
        dono.setVida(dono.getVida() + 10);
        
        this.addAcumulos(-1);

        if (this.getAcumulos() == 0) { // O efeito acabou definitivamente
            this.getDono().removerEfeito(this);
            manager.desinscrever(this);
        }
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("INIMIGO VAI ATACAR"))
            usar(manager.getInimigo(), manager);
    }
}
