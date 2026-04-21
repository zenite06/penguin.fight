package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.Batalha;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EfeitoAcidoTest {
    @Test
    public void criarEfeitoAcido() {
        EfeitoAcido efeito = new EfeitoAcido(3, 5);
        assertEquals(3, efeito.getAcumulos());
        assertEquals("ÁCIDO", efeito.getNome());
    }

    @Test
    public void ativar() {
        EfeitoAcido efeito = new EfeitoAcido(3, 5);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", null, null, null, null);
        Heroi player = new Heroi("Pinguim", 40);
        App.manager.setBattle(new Batalha(inimigo, "Geleira"));
        App.manager.setPlayer(player);
        efeito.ativar(player);
        assertEquals(2, efeito.getAcumulos());
        assertEquals(35, player.getVida());
    }

    @Test
    public void removerEfeitoQuandoAcaba() {
        EfeitoAcido efeito = new EfeitoAcido(1, 5);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", new CartaDano("O CHUTE", "Carta de Ataque", 0, 20), new CartaDano("O SOCO", "Carta de Ataque", 0, 10), new CartaEscudo("A ESQUIVA", "Carta de Defesa", 0, 5), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, efeito));
        Heroi player = new Heroi("Pinguim", 40);
        App.manager.setBattle(new Batalha(inimigo, "Geleira"));
        App.manager.setPlayer(player);
        efeito.ativar(player);
        assertEquals(0, efeito.getAcumulos());
        assertEquals(35, player.getVida());
        assertEquals(0, inimigo.getEfeitos().size());

    }

    @Test
    public void estaSendoNotificado() {
        EfeitoAcido efeito = new EfeitoAcido(3, 5);
        Heroi player = new Heroi("Pinguim", 40);
        App.manager.setPlayer(player);
        String evento = "INIMIGO ATACOU";
        efeito.serNotificado(evento);
        assertEquals(35, player.getVida());
    }

    @Test
    public void clonar() {
        EfeitoAcido efeito = new EfeitoAcido(3, 5);
        Efeito newEfeito = efeito.clonar();
        assertEquals("ÁCIDO", newEfeito.getNome());
        assertEquals(3, newEfeito.getAcumulos());
    }
} 