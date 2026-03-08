public class Inimigo {
    private String nome;
    private int vida;
    private int escudo;
    private String capa; // Capa da luta (Imagem dos personagens)

    public Inimigo(String nome, int vida, int escudo, String capa) { // Setup do inimigo
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.capa = capa;
    }

    public String getName() {
        return this.nome;
    }

    public String getC() {
        return this.capa;
    }

    // Implementar os métodos faltantes!
}
