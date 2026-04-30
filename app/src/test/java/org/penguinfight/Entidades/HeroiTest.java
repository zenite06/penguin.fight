package org.penguinfight.Entidades;
import org.junit.jupiter.api.Test;
import org.penguinfight.Entidades.Heroi;
import static org.junit.jupiter.api.Assertions.*;

public class HeroiTest {
    private static Heroi player = Heroi.getInstance("Pinguim", 40, null, null);

    @Test
    public void criarHeroi() {
        assertEquals("Pinguim", player.getNome());
        assertEquals(40, player.getVida());
        assertEquals(100, player.getEnergia());
        assertEquals(0, player.getEscudo());
        assertTrue(player.estaVivo());
    }

    @Test
    public void receberDanoSemEscudo() {
        player.receberDano(10);

        assertEquals(30, player.getVida());
        assertTrue(player.estaVivo());
    }

    @Test
    public void receberDanoComEscudoParcial() {
        player.setEscudo(5);
        player.receberDano(10);

        assertEquals(35, player.getVida());
        assertEquals(5, player.getEscudo());
        assertTrue(player.estaVivo());
    }

    @Test
    public void receberDanoComEscudoTotal() {
        player.setEscudo(10);
        player.receberDano(10);

        assertEquals(40, player.getVida());
        assertEquals(10, player.getEscudo());
        assertTrue(player.estaVivo());
    }

    @Test
    public void receberDanoComEscudoExcedente() {
        player.setEscudo(15);
        player.receberDano(10);

        assertEquals(40, player.getVida());
        assertEquals(15, player.getEscudo());
        assertTrue(player.estaVivo());
    }

    @Test
    public void usarEnergia() {
        player.usarEnergia(30);

        assertEquals(70, player.getEnergia());
        assertTrue(player.estaVivo());
    }

    @Test
    public void morrer() {
        player.receberDano(50);
        
        assertEquals(0, player.getVida());
        assertFalse(player.estaVivo());
    }
}