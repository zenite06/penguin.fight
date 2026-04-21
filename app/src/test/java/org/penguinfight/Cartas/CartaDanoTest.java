package org.penguinfight.Cartas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

public class CartaDanoTest {

    @Test
    public void criarCartaDano() {
        CartaDano carta = new CartaDano("COTOVELADA", "Carta de Ataque", 10, 2);
        assertEquals("COTOVELADA", carta.getNome());
        assertEquals("Carta de Ataque", carta.getDescricao());
        assertEquals(10, carta.getCusto());
        assertEquals(2, carta.getValor());
    }

    @Test
    public void usarCartaDanoComEnergiaSuficiente() {
        Heroi player = new Heroi("Pinguim", 40);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", null, null, null, null);
        CartaDano carta = new CartaDano("COTOVELADA", "Carta de Ataque", 10, 2);
        carta.usar(player, inimigo);
        assertEquals(90, player.getEnergia());
        assertEquals(48, inimigo.getVida());
    }

    @Test
    public void usarCartaDanoSemEnergiaSuficiente() {
        Heroi player = new Heroi("Pinguim", 40);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", null, null, null, null);
        CartaDano carta = new CartaDano("COTOVELADA", "Carta de Ataque", 10, 2);
        player.setEnergia(5);
        carta.usar(player, inimigo);
        assertEquals(5, player.getEnergia());
        assertEquals(50, inimigo.getVida());
    }
}