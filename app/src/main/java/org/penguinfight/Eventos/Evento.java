package org.penguinfight.Eventos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import org.penguinfight.App;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Classe base abstrata que modela todos os possíveis encontros e nós no mapa.
 * Estabelece a infraestrutura polimórfica para que Batalhas, Lojas, Fontes 
 * e Escolhas possam ser tratados de forma uniforme no laço principal do jogo.
 */
public abstract class Evento {
    protected String local;

    public Evento(String local) {
        this.local = local;
    }

    public String getLocal() {
        return this.local;
    }

    /**
     * Método abstrato de gatilho de evento. 
     * @return true se o jogador for bem sucedido (ou o evento for pacífico), false caso perca uma batalha.
     */
    public abstract boolean iniciar();
}
