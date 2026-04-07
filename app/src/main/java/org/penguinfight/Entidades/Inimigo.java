package org.penguinfight.Entidades;

import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Efeitos.Efeito;

/**
 * A entidade que representa um rivais do jogador
 */
public class Inimigo extends Entidade {
    private int dano;
    private String capa; // Capa da luta (Imagem dos personagens)
    private String capa_v; // Capa da vitória
    private String capa_d; // Capa da derrota
    private CartaDano ataques[] = new CartaDano[2];
    private CartaEscudo defesas[] = new CartaEscudo[1];
    private CartaEfeito efeitos[] = new CartaEfeito[1];
    private int decisao[] = new int[3]; // Vetor com as decisões do inimigo para a rodada

    /* 
    decisão[0]: indica qual ataque o inimigo escolheu
        0 = ataque1
        1 = ataque2

    decisão[1]: indica se o inimigo vai defender
        0 = vai defender
        1 = não vai defender

    decisão[2]: indica se o inimigo vai usar um efeito 
        0 = vai usar um efeito
        1, ..., 5 = não vai usar um efeito 
    */

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

    public void decidirAcao() {
        int ataque = (int)(Math.random() * 2); // Define o ataque do inimigo
        this.decisao[0] = ataque;

        int defesa = (int)(Math.random() * 2); // Define se o inimigo vai defender
        this.decisao[1] = defesa;
        if (defesa == 0)
            this.setEscudo(defesas[0].getValor());

        int efeito = (int)(Math.random() * 6); // Define se o inimigo vai usar um efeito
        this.decisao[2] = efeito;   
    }

    public void declarar() {
        IO.println(this.getNome() + " se preparou para usar " + App.ANSI_PURPLE +  this.ataques[this.decisao[0]].getNome() + App.ANSI_RED + " (Dano = " + this.ataques[this.decisao[0]].getValor() + ")\n" + App.ANSI_RESET);
        if (decisao[1] == 0) // O inimigo vai defender
            IO.println(this.getNome() + " se preparou para usar " + App.ANSI_PURPLE +  this.defesas[0].getNome() + App.ANSI_RED + " (Defesa = " + this.defesas[0].getValor() + ")\n" + App.ANSI_RESET);
        if (decisao[2] == 0) // O inimigo vai usar um efeito
            IO.println(this.getNome() + " se preparou para usar " + App.ANSI_PURPLE +  this.efeitos[0].getNome() + App.ANSI_RED + " (" + this.efeitos[0].getDescricao() + ")\n" + App.ANSI_RESET);
    } 

    public void atacar(Heroi player) {
        IO.println();
        IO.println(this.getNome() + " atacou!\n");
        player.receberDano(this.ataques[this.decisao[0]].getValor());
    }

    public void usarEfeito(RoundManager manager) {
        if (decisao[2] == 0) {
            Efeito efeito_copia = this.efeitos[0].getEfeito().clonar();
            this.aplicarEfeito(efeito_copia, manager);
        }
    }


}
