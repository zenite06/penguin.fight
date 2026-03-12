public class CartaDano {
    private String nome;
    private int custo;
    private int dano;

    public CartaDano(String nome, int custo, int dano) {
        this.nome = nome;
        this.custo = custo;
        this.dano = dano;
    }

    public void usar(Heroi heroi, Inimigo inimigo) {
        if (heroi.getEnergia() >= this.custo) {
            IO.println("\n");
            IO.println(heroi.getName() + " usou " + App.ANSI_PURPLE + this.nome + App.ANSI_RESET + " em " + inimigo.getName() + "!");
            inimigo.receberDano(this.dano);
            heroi.setEnergia(this.custo);
        }
        else
            IO.println(App.ANSI_RED + "Energia insuficiente!" + App.ANSI_RESET);
    }

    public String getName() {
        return this.nome;
    }

    public int getCusto() {
        return this.custo;
    }

    public int getDano() {
        return this.dano;
    }
}