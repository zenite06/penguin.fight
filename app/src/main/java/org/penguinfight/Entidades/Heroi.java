package org.penguinfight.Entidades;

import java.util.List;
import java.util.Stack;

import org.penguinfight.Cartas.Carta;

/**
 * Entidade controlada pelo jogador. Possui um recurso de energia que é 
 * consumido ao utilizar as cartas do baralho.
 */
public class Heroi extends Entidade {

    private static Heroi instance;
    private int energia;
    private int moedas;
    private List <Carta> pilhaDescarte; 
    private Stack <Carta> pilhaCompra;

    private Heroi(String nome, int vida, List <Carta> pilhaDescarte, Stack <Carta> pilhaCompra) {
        super(nome, vida, 0);
        this.energia = 100;
        this.moedas = 0;
        this.pilhaDescarte = pilhaDescarte;
        this.pilhaCompra = pilhaCompra;
    }

    public static Heroi getInstance(String nome, int vida, List <Carta> pilhaDescarte, Stack <Carta> pilhaCompra) {
        if (instance == null) {
            instance = new Heroi(nome, vida, pilhaDescarte, pilhaCompra);
        } 
        return instance;
    }

    public static Heroi getInstance() {
        return instance;
    }

    public int getEnergia() {
        return this.energia;
    }

    public int getMoedas() {
        return this.moedas;
    }

    public void setMoedas(int moedas) {
        this.moedas = moedas;
    }

    public List <Carta> getPilhaDescarte() {
        return this.pilhaDescarte;
    }

    public Stack <Carta> getPilhaCompra() {
        return this.pilhaCompra;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setPilhaDescarte(List <Carta> pilhaDescarte) {
        this.pilhaDescarte = pilhaDescarte;
    }

    public void setPilhaCompra(Stack <Carta> pilhaCompra) {
        this.pilhaCompra = pilhaCompra;
    }

    /**
     * Deduz o custo da barra do herói após a utilização de uma carta.
     */
    public void usarEnergia(int energia) {
        this.energia -= energia;
    }

    public void usarMoedas(int moedas) {
        if (moedas >= this.moedas) 
            this.moedas -= moedas;
    }

    public void ganharMoedas(int moedas) {
        this.moedas += moedas;
    }

    public static void resetInstance() {
        instance = null;
    }
}
