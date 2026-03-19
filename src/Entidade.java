public abstract class Entidade {
    private String nome;
    private int vida;
    private int escudo;

    public Entidade(String nome, int vida, int escudo) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
    }

    public String getNome() {
        return this.nome;
    }

    public int getVida() {
        return this.vida;
    }

    public int getEscudo() {
        return this.escudo;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    public void receberDano(int dano) {
        int dano_efetivo = dano - this.escudo;
        if (dano_efetivo > 0) 
            this.vida -= dano_efetivo;
        else
            dano_efetivo = 0;
        if (this.escudo > 0)
            IO.println(this.nome + " defendeu" + App.ANSI_RED + " (- " + dano_efetivo + " de vida)" + App.ANSI_RESET);
        else 
            IO.println(this.nome + " não defendeu" + App.ANSI_RED + " (- " + dano_efetivo + " de vida)" + App.ANSI_RESET);
    }

    public void ganharEscudo(int escudo) {
        this.escudo += escudo;
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }
}
