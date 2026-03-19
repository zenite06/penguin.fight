public class CartaEscudo extends Carta {
    private int escudo;

    public CartaEscudo(String nome, String descricao, int custo, int escudo) {
        super(nome, descricao, custo);
        this.escudo = escudo;
    }

    public int getEscudo() {
        return this.escudo;
    }

    public void usar(Heroi heroi, Inimigo inimigo) {
        if (heroi.getEnergia() >= this.getCusto()) {
            IO.println("\n");
            IO.println(heroi.getNome() + " se preparou para usar "  + App.ANSI_PURPLE +  this.getNome() + App.ANSI_GREEN + " (+ " + this.escudo + " de defesa)\n" + App.ANSI_RESET);
            heroi.ganharEscudo(this.escudo);
            heroi.usarEnergia(this.getCusto());
        } else 
            IO.println(App.ANSI_RED + "Energia insuficiente!\n" + App.ANSI_RESET);
    }
}
