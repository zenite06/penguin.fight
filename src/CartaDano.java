public class CartaDano extends Carta {
    private int dano;

    public CartaDano(String nome, String descricao, int custo, int dano) {
        super(nome, descricao, custo);
        this.dano = dano;
    }

    public int getDano() {
        return this.dano;
    }

    public void usar(Heroi heroi, Inimigo inimigo) {
        if (heroi.getEnergia() >= this.getCusto()) {
            IO.println("\n");
            IO.println(heroi.getNome() + " usou " + App.ANSI_PURPLE + this.getNome() + App.ANSI_RESET + " em " + inimigo.getNome() + "!");
            inimigo.receberDano(this.dano);
            heroi.usarEnergia(this.getCusto());
        }
        else
            IO.println(App.ANSI_RED + "Energia insuficiente!" + App.ANSI_RESET);
    }
}
