package org.penguinfight.Efeitos;
import org.penguinfight.Observer;
import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;

/**
 * Modificadores de status atrelados às Entidades.
 * Reagem ao ciclo de combate via padrão Observer ou aplicam efeitos imediatos.
 */
public abstract class Efeito implements Observer {
    private String nome;
    private Entidade dono;
    private int acumulos; // Os acúmulos representam o "valor" do efeito e/ou sua duração em rounds

    public Efeito(String nome, int acumulos) {
        this.nome = nome;
        this.acumulos = acumulos;
    }

    public String getNome() {
        return this.nome;
    }

    public Entidade getDono() {  
        return this.dono;
    }

    public int getAcumulos() {
        return this.acumulos;
    }

    public String getString() {
        return "O efeito " + this.nome + " tem " + this.acumulos + " acúmulos";
    }

    public void setDono(Entidade entidade) {
        this.dono = entidade;
    }

    /**
     * Manipula a contagem do efeito (use valores negativos para reduzir a duração/cargas).
     */
    public void addAcumulos(int add) {
        this.acumulos += add;
    }

    @Override
    public abstract void serNotificado(String evento, RoundManager manager);

    /**
     * Executa a lógica principal do efeito em resposta a um evento do jogo (Notificação Observer).
     */
    public abstract void ativar(Entidade entidade, RoundManager manager);

    /**
     * Método de ativação imediata que é utilizado no exato momento em que o efeito é aplicado na entidade.
     * Pode ser sobrescrito por efeitos que têm impacto instantâneo (ex: Cura, Faixa).
     */
    public void ativarImediato(Entidade entidade, RoundManager manager) {}; // Só se aplica às classes que possuem ativação imediata de um efeito

    /**
     * Desacopla instâncias de efeitos para que não compartilhem a mesma referência de memória.
     */
    public abstract Efeito clonar();
}
