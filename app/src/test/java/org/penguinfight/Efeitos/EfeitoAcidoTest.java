package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;
import org.penguinfight.Eventos.Batalha;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EfeitoAcidoTest {
    private static RoundManager manager = RoundManager.getInstance();
    private static Heroi player = Heroi.getInstance("Pinguim", 40, null, null);

    @Test
    public void criarEfeitoAcido() {
        EfeitoAcido efeito = new EfeitoAcido(3, 5);

        assertEquals("ÁCIDO", efeito.getNome());
        assertEquals(3, efeito.getAcumulos());
        assertEquals(5, efeito.getDano());
    }

    @Test
    public void ativarECausarDanoEmPlayer() {
        EfeitoAcido efeito = new EfeitoAcido(3, 5);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", null, null, null, null);
        RoundManager.getInstance().setEvento(new Batalha(inimigo, "Geleira", 0));
        efeito.ativar(player);

        assertEquals(2, efeito.getAcumulos());
        assertEquals(35, player.getVida());
    }

    @Test
    public void removerEfeitoQuandoAcaba() {
        EfeitoAcido efeito = new EfeitoAcido(1, 5);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", new CartaDano("O CHUTE", "Carta de Ataque", 0, 20), new CartaDano("O SOCO", "Carta de Ataque", 0, 10), new CartaEscudo("A ESQUIVA", "Carta de Defesa", 0, 5), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, efeito));
        RoundManager.getInstance().setEvento(new Batalha(inimigo, "Geleira", 0));
        efeito.ativar(player);

        assertEquals(0, inimigo.getEfeitos().size());

    }

    @Test
    public void serNotificadoECausarDanoEmPlayer() {
        EfeitoAcido efeito = new EfeitoAcido(3, 5);
        efeito.serNotificado("INIMIGO ATACOU");

        assertEquals(35, player.getVida());
    }

    @Test
    public void clonar() {
        EfeitoAcido efeito = new EfeitoAcido(3, 5);
        Efeito newEfeito = efeito.clonar();

        assertTrue(newEfeito instanceof EfeitoAcido);
        assertEquals("ÁCIDO", newEfeito.getNome());
        assertEquals(3, newEfeito.getAcumulos());
        assertEquals(5, ((EfeitoAcido) newEfeito).getDano());
    }
} 