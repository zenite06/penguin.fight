package org.penguinfight.Cartas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

public class CartaEscudoTest {
    private static Heroi player = Heroi.getInstance("Pinguim", 40, null, null);

    @Test
    public void criarCartaEscudo() {
        CartaEscudo carta = new CartaEscudo("ESQUIVA", "Carta de Defesa", 10, 2);
        
        assertEquals("ESQUIVA", carta.getNome());
        assertEquals("Carta de Defesa", carta.getDescricao());
        assertEquals(10, carta.getCusto());
        assertEquals(2, carta.getValor());
    }

    @Test
    public void usarCartaEscudoComEnergiaSuficiente() {
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", null, null, null, null);
        CartaEscudo carta = new CartaEscudo("ESQUIVA", "Carta de Defesa", 10, 2);
        carta.usar(player, inimigo);
        
        assertEquals(90, player.getEnergia());
        assertEquals(2, player.getEscudo());
    }

    @Test
    public void usarCartaEscudoSemEnergiaSuficiente() {
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", null, null, null, null);
        CartaEscudo carta = new CartaEscudo("ESQUIVA", "Carta de Defesa", 10, 2);
        player.setEnergia(5);
        carta.usar(player, inimigo);
        
        assertEquals(5, player.getEnergia());
        assertEquals(0, player.getEscudo());
    }
}