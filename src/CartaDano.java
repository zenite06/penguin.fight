public class CartaDano extends Carta {

    public CartaDano(String nome, String descricao, int custo, int valor) {
        super(nome, descricao, custo, valor);
    }

    public void usar(Heroi heroi, Inimigo inimigo) {
        if (heroi.getEnergia() >= this.getCusto()) {
            IO.println("\n");
            IO.println(heroi.getNome() + " usou " + App.ANSI_PURPLE + this.getNome() + App.ANSI_RESET + " em " + inimigo.getNome() + "!");
            inimigo.receberDano(this.getValor());
            heroi.usarEnergia(this.getCusto());
        }
        else
            IO.println(App.ANSI_RED + "Energia insuficiente!" + App.ANSI_RESET);
    }
}
