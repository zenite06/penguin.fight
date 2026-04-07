package org.penguinfight.Entidades;

import java.util.List;

import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Efeitos.Efeito;

import java.util.ArrayList;

/**
 * Entidades representam personagens que interagem e tomam decisões no jogo
 */
public abstract class Entidade {
    private String nome;
    private int vida;
    private int escudo;
    private List<Efeito> efeitos;

    public Entidade(String nome, int vida, int escudo) {
        this.nome = nome;
        this.vida = vida;
        this.escudo = escudo;
        this.efeitos = new ArrayList<>();
    }

    public String getNome() {
        return this.nome;
    }

    public int getVida() {
        return this.vida;
    }

    public int getEscudo() {
        return this.escudo;
    }

    public List<Efeito> getEfeitos() { 
        return efeitos;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public void setEscudo(int escudo) {
        this.escudo = escudo;
    }

    /**
     * Método ???
     * @param dano
     */
    public void receberDano(int dano) {
        int dano_efetivo = dano - this.escudo;
        if (dano_efetivo > 0) 
            this.vida -= dano_efetivo;
        else
            dano_efetivo = 0;
        if (this.escudo > 0)
            IO.println(this.nome + " se defendeu" + App.ANSI_RED + " (- " + dano_efetivo + " de vida)\n" + App.ANSI_RESET);
        else 
            IO.println(this.nome + " não se defendeu" + App.ANSI_RED + " (- " + dano_efetivo + " de vida)\n" + App.ANSI_RESET);
    }

    public void ganharEscudo(int escudo) {
        this.escudo += escudo;
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    public void aplicarEfeito(Efeito efeito, RoundManager manager) {
        for (Efeito efeito_ativo : this.efeitos)
            if (efeito_ativo.getNome().equals(efeito.getNome())) {
                efeito_ativo.addAcumulos(efeito.getAcumulos());
                return;
            }
        efeito.setDono(this);
        this.efeitos.add(efeito);
        manager.inscrever(efeito);
    }

    public void removerEfeito(Efeito efeito) {
        this.efeitos.remove(efeito);
    }
}
