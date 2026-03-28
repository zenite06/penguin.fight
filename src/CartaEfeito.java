public class CartaEfeito extends Carta{
    private Efeito efeito;
    public CartaEfeito(String nome, String descricao, int custo, int acumulo, Efeito efeito) {
        super(nome, descricao, custo, acumulo);
        this.efeito = efeito;
    }

    public void usar(Heroi player, Inimigo inimigo) {
    }
}
