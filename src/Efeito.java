public abstract class Efeito {
    private String nome;
    private String dono;
    private int acumulos; // Os acúmulos representam o "valor" do efeito (?)

    public Efeito(String nome, String dono, int acumulos) {
        this.nome = nome;
        this.dono = dono;
        this.acumulos = acumulos;
    }

    public String getNome() {
        return this.nome;
    }

    public String getDono() {
        return this.dono;
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

    public abstract void usar(Entidade entidade, Entidade rival);
}
