import java.util.ArrayList;

public class Inimigo extends Entidade {
    private int dano;
    private String capa; // Capa da luta (Imagem dos personagens)
    private String capa_v; // Capa da vitória
    private String capa_d; // Capa da derrota
    private CartaDano ataques[] = new CartaDano[2];

    public Inimigo(String nome, int vida, int dano, String capa, String capa_v, String capa_d, CartaDano ataque1, CartaDano ataque2) { // Setup do inimigo
        super(nome, vida, 0, new ArrayList<Efeito>());
        this.dano = dano;
        this.capa = capa;     
        this.capa_v = capa_v; 
        this.capa_d = capa_d; 
        this.ataques[0] = ataque1;
        this.ataques[1] = ataque2;
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

    public void setDano(int dano) {
        this.dano = dano;
    }

    public void declarar(int i, Heroi player) {
        IO.println(this.getNome() + " se preparou para usar " + App.ANSI_PURPLE +  this.ataques[i].getNome() + App.ANSI_RED + " (Dano = " + this.ataques[i].getValor() + ")\n" + App.ANSI_RESET);
    } 

    public void atacar(Heroi heroi) {
        IO.println();
        IO.println(this.getNome() + " atacou!");
        heroi.receberDano(this.dano);
    }
}
