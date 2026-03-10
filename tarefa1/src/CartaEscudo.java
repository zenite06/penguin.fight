public class CartaEscudo {
    private String nome;
    private int escudo;
    private int custo; // Custo em energia da carta

    public CartaEscudo(String nome, int escudo, int custo) {
        this.nome = nome;
        this.escudo = escudo;
        this.custo = custo;
    }

    public void usar(Heroi heroi, Inimigo inimigo) {
        if (heroi.getEnergia() >= this.custo) {
            IO.println(heroi.getName() + "se preparou para usar " + this.nome + "(+ " + this.escudo + " de defesa)\n");
        } else 
            IO.println("Energia insuficiente!\n");
    }
}