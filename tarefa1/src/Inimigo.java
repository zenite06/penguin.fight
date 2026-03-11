public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private int dano;
    private String capa; // Capa da luta (Imagem dos personagens)
    private String capa_v; // Capa da vitória
    private String capa_d; // Capa da derrota

    public Inimigo(String nome, int vida, int escudo, int dano, String capa, String capa_v, String capa_d) { // Setup do inimigo
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.dano = dano;
        this.capa = capa;
        this.capa_v = capa_v;
        this.capa_d = capa_d;
    }

    public String getName() {
        return this.nome;
    }

    public String getC() {
        return this.capa;
    }

    public String getCV() {
        return this.capa_v;
    }

    public String getCD() {
        return this.capa_d;
    }

    public int getVida() {
        return this.vida;
    }

    public int getDano() {
        return this.dano;
    }

    public void receberDano(int dano) {
        int p = (int)(Math.random() * 2); // Define aleatoriamente se o inimigo conseguiu se defender

        if (p == 1) {
            int dano_efetivo = dano - this.escudo;
            if (dano_efetivo > 0) 
                this.vida -= dano_efetivo;
            else
                dano_efetivo = 0;
            if (this.escudo > 0)
                IO.println(this.nome + " defendeu (- " + dano_efetivo + " de vida)");
            else 
                IO.println(this.nome + " não defendeu (- " + dano_efetivo + " de vida)");
        } else {
            this.vida -= dano;
            IO.println(this.nome + " não defendeu (- " + dano + " de vida)");
        }
    }

    public void atacar(Heroi heroi) {
        IO.println(this.nome + " atacou!");
        heroi.receberDano(this.dano);
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }
}
