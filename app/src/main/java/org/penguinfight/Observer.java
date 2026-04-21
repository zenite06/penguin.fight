package org.penguinfight;

/**
 * Interface para o padrão de projeto Observer.
 * Permite que as classes inscritas reajam de forma assíncrona a eventos do jogo 
 * (como troca de turnos e ataques).
 */
public interface Observer {

    /**
     * Método chamado pelo Publisher para alertar a classe sobre um gatilho ocorrido no combate.
     */
    void serNotificado(String evento);
}
