package org.penguinfight.Entidades;

import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Efeitos.Efeito;

/**
 * Entidade controlada pelo sistema que atua como oponente do jogador.
 * Baseada em aleatoriedade (RNG) para definir
 * suas ações (ataques, defesas e efeitos) a cada turno.
 */
public class Inimigo extends Entidade {
    private int dano;
    private String capa; // Capa da luta (Imagem dos personagens)
    private String capa_v; // Capa da vitória
    private String capa_d; // Capa da derrota
    private CartaDano ataques[] = new CartaDano[2];
    private CartaEscudo defesas[] = new CartaEscudo[1];
    private CartaEfeito efeitos[] = new CartaEfeito[1];

    /* Vetor com as decisões para a rodada atual:
    decisão[0]: 0 = ataque1, 1 = ataque2
    decisão[1]: 0 = defende, 1 = não defende
    decisão[2]: 0 = usa efeito, 1 a 5 = não usa efeito 
    */
    private int decisao[] = new int[3];

    public Inimigo(String nome, int vida, String capa, String capa_v, String capa_d, CartaDano ataque1, CartaDano ataque2, CartaEscudo defesa, CartaEfeito efeito) { // Setup do inimigo
        super(nome, vida, 0);
        this.capa = capa;     
        this.capa_v = capa_v; 
        this.capa_d = capa_d; 
        this.ataques[0] = ataque1;
        this.ataques[1] = ataque2;
        this.defesas[0] = defesa;
        this.efeitos[0] = efeito;
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

    public CartaDano getAtaque(int i) {
        return this.ataques[i];
    }

    public int getDecisao(int i) {
        return this.decisao[i];
    }

    public void setCapa(String capa) { // Aplica uma nova capa de batalha no uso de efeitos
        this.capa = capa;
    }

    /**
     * Sorteia randomicamente quais ações 
     * (ataque, defesa e efeito) serão tomadas neste turno.
     */
    public void decidirAcao() {
        int ataque = (int)(Math.random() * 2);
        this.decisao[0] = ataque;

        int defesa = (int)(Math.random() * 2);
        this.decisao[1] = defesa;
        if (defesa == 0)
            this.setEscudo(defesas[0].getValor());

        int efeito = (int)(Math.random() * 6);
        this.decisao[2] = efeito;   
    }

    /**
     * Exibe no terminal as intenções do inimigo para o turno atual, 
     * dando ao jogador a chance de reagir e planejar suas defesas.
     */
    public void declarar() {
        IO.println(this.getNome() + " se preparou para usar " + App.ANSI_PURPLE +  this.ataques[this.decisao[0]].getNome() + App.ANSI_RED + " (Dano = " + this.ataques[this.decisao[0]].getValor() + ")\n" + App.ANSI_RESET);
        if (decisao[1] == 0)
            IO.println(this.getNome() + " se preparou para usar " + App.ANSI_PURPLE +  this.defesas[0].getNome() + App.ANSI_RED + " (Defesa = " + this.defesas[0].getValor() + ")\n" + App.ANSI_RESET);
        if (decisao[2] == 0)
            IO.println(this.getNome() + " se preparou para usar " + App.ANSI_PURPLE +  this.efeitos[0].getNome() + App.ANSI_RED + " (" + this.efeitos[0].getDescricao() + ")\n" + App.ANSI_RESET);
    } 

    /**
     * Executa a ação de ataque previamente sorteada contra o jogador.
     */
    public void atacar(Heroi player) {
        IO.println();
        IO.println(this.getNome() + " atacou!\n");
        player.receberDano(this.ataques[this.decisao[0]].getValor());
    }

    /**
     * Aplica o efeito sorteado pelo inimigo durante a fase de decisão.
     */
    public void usarEfeito(RoundManager manager) {
        if (decisao[2] == 0) {
            Efeito efeito_copia = this.efeitos[0].getEfeito().clonar();
            this.aplicarEfeito(efeito_copia, manager);
        }
    }


}
