package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;
import org.penguinfight.Eventos.Batalha;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EfeitoNevascaTest {
    private static RoundManager manager = RoundManager.getInstance();
    private static Heroi player = Heroi.getInstance("Pinguim", 40, null, null);

    @Test
    public void criarEfeitoNevasca() {
        EfeitoNevasca efeito = new EfeitoNevasca(50);

        assertEquals(50, efeito.getAcumulos());
        assertEquals("NEVASCA", efeito.getNome());
    }

    @Test
    public void ativarEReduzirAtaqueDoInimigo() { 
        EfeitoNevasca efeito = new EfeitoNevasca(1);
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito cartaEfeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, cartaEfeito);
        manager.setEvento(new Batalha(inimigo, "Geleira", 0));
        inimigo.decidirAcao();
        efeito.ativar(inimigo);

        assertEquals(0, efeito.getAcumulos());
        if (inimigo.getDecisao(0) == 0)
            assertEquals(1, inimigo.getAtaque(0).getValor());
        else
            assertEquals(2, inimigo.getAtaque(1).getValor());
    }

    @Test
    public void resetarEResetarAtaqueDoInimigo() { 
        EfeitoNevasca efeito = new EfeitoNevasca(1);
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito cartaEfeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, cartaEfeito);
        player.aplicarEfeito(efeito);
        efeito.setDono(player);
        manager.setEvento(new Batalha(inimigo, "Geleira", 0));
        inimigo.decidirAcao();
        efeito.ativar(inimigo);
        efeito.resetar(inimigo);

        assertEquals(0, efeito.getAcumulos());
        if (inimigo.getDecisao(0) == 0)
            assertEquals(2, inimigo.getAtaque(0).getValor());
        else
            assertEquals(4, inimigo.getAtaque(1).getValor());
    }

    @Test
    public void serNotificadoEResetar() { 
        EfeitoNevasca efeito = new EfeitoNevasca(1);
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito cartaEfeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, cartaEfeito);
        player.aplicarEfeito(efeito);
        efeito.setDono(player);
        manager.setEvento(new Batalha(inimigo, "Geleira", 0));
        inimigo.decidirAcao();
        efeito.serNotificado("INIMIGO VAI ATACAR");

        assertEquals(0, efeito.getAcumulos());
        if (inimigo.getDecisao(0) == 0)
            assertEquals(1, inimigo.getAtaque(0).getValor());
        else
            assertEquals(2, inimigo.getAtaque(1).getValor());

        efeito.serNotificado("INIMIGO ATACOU");

        if (inimigo.getDecisao(0) == 0)
            assertEquals(2, inimigo.getAtaque(0).getValor());
        else
            assertEquals(4, inimigo.getAtaque(1).getValor());
    }

    @Test
    public void clonar() {
        EfeitoNevasca efeito = new EfeitoNevasca(50);
        Efeito newEfeito = efeito.clonar();

        assertTrue(newEfeito instanceof EfeitoNevasca);
        assertEquals("NEVASCA", newEfeito.getNome());
        assertEquals(50, newEfeito.getAcumulos());
    }
}