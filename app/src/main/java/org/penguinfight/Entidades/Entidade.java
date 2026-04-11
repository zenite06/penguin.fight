package org.penguinfight.Entidades;
import java.util.List;
import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Efeitos.EfeitoFaixa;
import org.penguinfight.Efeitos.EfeitoNevasca;
import org.penguinfight.Efeitos.EfeitoCura;

import java.util.ArrayList;

/**
 * Classe base abstrata para todos os combatentes do jogo (Herói e Inimigos).
 * Gerencia o estado vital (vida, escudo) e a lista de efeitos ativos.
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
     * Processa o recebimento de dano, mitigando o valor com base no escudo atual da entidade.
     * @param dano O valor bruto de dano recebido.
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

    /**
     * Registra um novo efeito na entidade. Se o efeito já estiver ativo, soma os acúmulos.
     * Também verifica se o efeito possui uma ação de ativação imediata (ex: Cura instantânea).
     */
    public void aplicarEfeito(Efeito efeito, RoundManager manager) {
        for (Efeito efeito_ativo : this.efeitos)
            if (efeito_ativo.getNome().equals(efeito.getNome())) {
                efeito_ativo.addAcumulos(efeito.getAcumulos());
                if (efeito_ativo instanceof EfeitoFaixa)
                    efeito_ativo.getDono().setEscudo(efeito_ativo.getDono().getEscudo() + (2 * efeito.getAcumulos()));
                return;
            }
        efeito.setDono(this);
        this.efeitos.add(efeito);
        manager.inscrever(efeito);

        // Efeitos com alguma aplicação imediata
        if (efeito instanceof EfeitoFaixa || efeito instanceof EfeitoNevasca || efeito instanceof EfeitoCura) {
            efeito.ativarImediato(efeito.getDono(), manager); 
        }
    }

    public void removerEfeito(Efeito efeito) {
        this.efeitos.remove(efeito);
    }
}
