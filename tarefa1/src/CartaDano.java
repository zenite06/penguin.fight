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
            IO.println(heroi.getName() + " usou " + this.nome + " em " + inimigo.getName() + "!");
            inimigo.receberDano(this.dano);
            heroi.setEnergia(this.custo);
        }
        else
            IO.println("Energia insuficiente!");
    }
}