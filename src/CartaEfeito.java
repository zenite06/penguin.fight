public class CartaEfeito extends Carta {
    private Efeito efeito;

    public CartaEfeito(String nome, String descricao, int custo, int acumulo, Efeito efeito) {
        super(nome, descricao, custo, acumulo);
        this.efeito = efeito;
    }

    public void usar(Heroi player, Inimigo inimigo, RoundManager manager) {
        if (player.getEnergia() >= this.getCusto()) {
            IO.println();
            IO.println(player.getNome() + " usou " + App.ANSI_PURPLE + this.getNome() + App.ANSI_RESET + "!");
            player.aplicarEfeito(this.efeito, manager);
            player.usarEnergia(this.getCusto());
        }
        else {
            IO.println();
            IO.println(App.ANSI_RED + "Energia insuficiente!" + App.ANSI_RESET);
        }
    }
}
