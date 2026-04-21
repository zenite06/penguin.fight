package org.penguinfight.Entidades;
import org.junit.jupiter.api.Test;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Efeitos.EfeitoAcido;
import org.penguinfight.Cartas.CartaEfeito;
import static org.junit.jupiter.api.Assertions.*;

public class InimigoTest {

    @Test
    public void criarInimigo() {
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", null, null, null, null);
        assertEquals("Pinguim Malvado", inimigo.getNome());
        assertEquals(50, inimigo.getVida());
        assertEquals("Capa", inimigo.getC());
        assertEquals("Capa Vitória", inimigo.getCV());
        assertEquals("Capa Derrota", inimigo.getCD());
        assertTrue(inimigo.estaVivo());
    } 

    @Test
    public void decidirEDeclararAcaoValida() {
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito efeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, efeito);
        inimigo.decidirAcao();
        inimigo.declarar();
        assertTrue(inimigo.getDecisao(0) == 0 || inimigo.getDecisao(0) == 1);
        assertTrue(inimigo.getDecisao(1) == 0 || inimigo.getDecisao(1) == 1);
        assertTrue(inimigo.getDecisao(2) >= 0 && inimigo.getDecisao(2) < 6);
    }

    @Test
    public void atacar() {
        Heroi player = new Heroi("Pinguim", 40);
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito efeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, efeito);
        inimigo.decidirAcao();
        inimigo.atacar(player);
        if (inimigo.getDecisao(0) == 0)
            assertEquals(38, player.getVida());
        else
            assertEquals(36, player.getVida());
    }

    @Test
    public void usarEfeito() {
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito efeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, efeito);
        inimigo.decidirAcao();
        inimigo.usarEfeito();
        boolean encontrou = false;
        for (Efeito efeitoAtivo : inimigo.getEfeitos()) {
            if (efeitoAtivo.getNome().equals(efeito.getEfeito().getNome()) && efeitoAtivo.getAcumulos() == efeito.getEfeito().getAcumulos()) {
                encontrou = true;
                break;
            }
        }
        if (inimigo.getDecisao(2) == 0)
            assertTrue(encontrou);
        else
            assertFalse(encontrou);
    }
}