package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;
import org.penguinfight.Eventos.Batalha;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EfeitoCuraTest {
    
    @Test
    public void criarEfeitoCura() {
        EfeitoCura efeito = new EfeitoCura(10);

        assertEquals("REGENERAÇÃO", efeito.getNome());
        assertEquals(10, efeito.getAcumulos());
    }

    @Test
    public void ativarImediatoECurar() {
        EfeitoCura efeito = new EfeitoCura(10);
        Heroi player = new Heroi("Pinguim", 40);
        player.setVida(30);
        efeito.ativarImediato(player);

        assertEquals(40, player.getVida());
        assertEquals(0, efeito.getAcumulos());
    }

    @Test
    public void ativarImediatoECurarSemUltrapassarAVidaMaxima() {
        EfeitoCura efeito = new EfeitoCura(10);
        Heroi player = new Heroi("Pinguim", 40);
        efeito.ativarImediato(player);

        assertEquals(40, player.getVida());
        assertEquals(0, efeito.getAcumulos());
    }

    @Test
    public void removerEfeitoQuandoAcaba() { 
        EfeitoCura efeito = new EfeitoCura(10);
        Heroi player = new Heroi("Pinguim", 40);
        player.aplicarEfeito(efeito);
        efeito.ativarImediato(player);

        assertEquals(0, player.getEfeitos().size());
    }
    
    @Test
    public void serNotificadoECurarInimigo() {
        EfeitoCura efeito = new EfeitoCura(10);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", new CartaDano("O CHUTE", "Carta de Ataque", 0, 20), new CartaDano("O SOCO", "Carta de Ataque", 0, 10), new CartaEscudo("A ESQUIVA", "Carta de Defesa", 0, 5), new CartaEfeito("REGENERAÇÃO", "Carta de Efeito", 0, efeito));
        inimigo.setVida(40);
        efeito.setDono(inimigo);
        efeito.serNotificado("FIM DO ROUND");

        assertEquals(50, inimigo.getVida());
    }

    @Test
    public void clonar() {
        EfeitoCura efeito = new EfeitoCura(10);
        Efeito newEfeito = efeito.clonar();
        
        assertTrue(newEfeito instanceof EfeitoCura);
        assertEquals("REGENERAÇÃO", newEfeito.getNome());
        assertEquals(10, newEfeito.getAcumulos());
    }
}
