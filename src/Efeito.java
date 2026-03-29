public abstract class Efeito implements Observer {
    private String nome;
    private Entidade dono;
    private int acumulos; // Os acúmulos representam o "valor" do efeito (?)

    public Efeito(String nome, int acumulos) {
        this.nome = nome;
        this.acumulos = acumulos;
    }

    public String getNome() {
        return this.nome;
    }

    public Entidade getDono() {  // Não sei se será necessário...
        return this.dono;
    }

    public void setDono(Entidade entidade) {
        this.dono = entidade;
    }

    public int getAcumulos() {
        return this.acumulos;
    }

    public void addAcumulos(int add) {
        this.acumulos += add;
    }

    public String getString() {
        return this.nome + this.acumulos; // Melhorar para uma apresentação mais elegante!
    }

    public abstract void serNotificado(String evento, RoundManager manager);
}
