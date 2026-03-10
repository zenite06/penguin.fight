public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private int dano;
    private String capa; // Capa da luta (Imagem dos personagens)

    public Inimigo(String nome, int vida, int escudo, String capa) { // Setup do inimigo
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.dano = dano;
        this.capa = capa;
    }

    public String getName() {
        return this.nome;
    }

    public String getC() {
        return this.capa;
    }

    public void receberDano(int dano) {
        int dano_efetivo = dano - this.escudo;
        if (dano_efetivo > 0) 
            this.vida -= dano_efetivo;
        else
            dano_efetivo = 0;
        if (this.escudo > 0) {
            IO.println(this.nome + " defendeu (" + dano_efetivo + " de dano efetivo)");
        }
    }

    // Implementar os métodos faltantes!
}
