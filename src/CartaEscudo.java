public class CartaEscudo {
    private String nome;
    private int escudo;
    private int custo; 

    public CartaEscudo(String nome, int escudo, int custo) {
        this.nome = nome;
        this.escudo = escudo;
        this.custo = custo;
    }

    public void usar(Heroi heroi, Inimigo inimigo) {
        if (heroi.getEnergia() >= this.custo) {
            IO.println("\n");
            IO.println(heroi.getName() + " se preparou para usar " + this.nome + App.ANSI_GREEN + " (+ " + this.escudo + " de defesa)\n" + App.ANSI_RESET);
            heroi.ganharEscudo(this.escudo);
            heroi.setEnergia(this.custo);
        } else 
            IO.println(App.ANSI_RED + "Energia insuficiente!\n" + App.ANSI_RESET);
    }

    public String getName() {
        return this.nome;
    }

    public int getCusto() {
        return this.custo;
    }

    public int getEscudo() {
        return this.escudo;
    }
}