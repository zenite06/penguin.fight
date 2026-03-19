<<<<<<< HEAD
    public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
=======
public class Inimigo extends Entidade {
>>>>>>> 48ff5fb35d105a5079ac28ac974a7a961add0e9d
    private int dano;
    private String capa; // Capa da luta (Imagem dos personagens)
    private String capa_v; // Capa da vitória
    private String capa_d; // Capa da derrota

    public Inimigo(String nome, int vida, int dano, String capa, String capa_v, String capa_d) { // Setup do inimigo
        super(nome, vida, 0);
        this.dano = dano;
        this.capa = capa;     
        this.capa_v = capa_v; 
        this.capa_d = capa_d; 
    }

    public int getDano() {
        return this.dano;
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

    public void atacar(Heroi heroi) {
        IO.println(this.getNome() + " atacou!");
        heroi.receberDano(this.dano);
    }
}
