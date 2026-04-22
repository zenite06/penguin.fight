package org.penguinfight.Entidades;
import org.junit.jupiter.api.Test;
import org.penguinfight.Entidades.Heroi;
import static org.junit.jupiter.api.Assertions.*;

public class HeroiTest {

    @Test
    public void criarHeroi() {
        Heroi player = new Heroi("Pinguim", 40);

        assertEquals("Pinguim", player.getNome());
        assertEquals(40, player.getVida());
        assertEquals(100, player.getEnergia());
        assertEquals(0, player.getEscudo());
        assertTrue(player.estaVivo());
    }

    @Test
    public void receberDanoSemEscudo() {
        Heroi player = new Heroi("Pinguim", 40);
        player.receberDano(10);

        assertEquals(30, player.getVida());
        assertTrue(player.estaVivo());
    }

    @Test
    public void receberDanoComEscudoParcial() {
        Heroi player = new Heroi("Pinguim", 40);
        player.setEscudo(5);
        player.receberDano(10);

        assertEquals(35, player.getVida());
        assertEquals(5, player.getEscudo());
        assertTrue(player.estaVivo());
    }

    @Test
    public void receberDanoComEscudoTotal() {
        Heroi player = new Heroi("Pinguim", 40);
        player.setEscudo(10);
        player.receberDano(10);

        assertEquals(40, player.getVida());
        assertEquals(10, player.getEscudo());
        assertTrue(player.estaVivo());
    }

    @Test
    public void receberDanoComEscudoExcedente() {
        Heroi player = new Heroi("Pinguim", 40);
        player.setEscudo(15);
        player.receberDano(10);

        assertEquals(40, player.getVida());
        assertEquals(15, player.getEscudo());
        assertTrue(player.estaVivo());
    }

    @Test
    public void usarEnergia() {
        Heroi player = new Heroi("Pinguim", 40);
        player.usarEnergia(30);

        assertEquals(70, player.getEnergia());
        assertTrue(player.estaVivo());
    }

    @Test
    public void morrer() {
        Heroi player = new Heroi("Pinguim", 40);
        player.receberDano(50);
        
        assertEquals(0, player.getVida());
        assertFalse(player.estaVivo());
    }
}