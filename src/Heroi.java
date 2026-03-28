import java.util.ArrayList;

public class Heroi extends Entidade {
    private int energia;

    public Heroi(String nome) {
        super(nome, 40, 0, new ArrayList<Efeito>());
        this.energia = 100;
    }

    public int getEnergia() {
        return this.energia;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void usarEnergia(int energia) {
        this.energia -= energia;
    }
}
