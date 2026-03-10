public class Heroi {
    private String nome;
    private int vida;
    private int escudo; // Obs: O escudo do jogador não é cumulativo ao longo dos turnos
    private int energia;

    public Heroi(String nome) { // Setup do herói
        this.nome = nome;

    // A quantidade de vida do herói depende do nível em que ele está
        if (App.getLevel() < 3) 
            this.vida = 40;
        else if (App.getLevel() == 10) 
            this.vida = 100;
        else
            this.vida = 60;

        this.escudo = 0;
        this.energia = 100; // Energia máxima
    }

    public void receberDano(int dano) {
        int dano_efetivo = dano - this.escudo;
        if (dano_efetivo > 0) 
            this.vida -= dano_efetivo;
        else
            dano_efetivo = 0;
        if (this.escudo > 0)
            IO.println(this.nome + " defendeu (- " + dano_efetivo + " de vida)");
        else 
            IO.println(this.nome + " não defendeu (- " + dano_efetivo + " de vida)");
    }

    public void ganharEscudo(int escudo) {
        this.escudo = escudo;
    }

    public boolean estarVivo() {
        if (this.vida > 0) 
            return true;
        return false;
    }

    public String getName() {
        return this.nome;
    }

    public int getEnergia() {
        return this.energia;
    }

    public void setEnergia(int valor) {
        this.energia -= valor;
    }

    public void setVida() {
        
    }
}
