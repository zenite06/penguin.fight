package org.penguinfight.Efeitos;

import org.penguinfight.RoundManager;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.Entidades.Observer;

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

    public void addAcumulos(int add) {
        this.acumulos += add;
    }

    public abstract void serNotificado(String evento, RoundManager manager);

    public abstract Efeito clonar();
}
