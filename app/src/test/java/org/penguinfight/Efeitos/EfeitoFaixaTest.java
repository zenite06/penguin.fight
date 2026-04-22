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

public class EfeitoFaixaTest {

    @Test
    public void criarEfeitoFaixa() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);

        assertEquals(5, efeito.getAcumulos());
        assertEquals("FAIXA", efeito.getNome());
    }

    @Test
    public void setPlayerComoDono() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.setDono(player);

        assertEquals(player, efeito.getDono());
    }

    @Test
    public void ativarEAumentarEscudoDoPlayer() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.setDono(player);
        efeito.ativar(player);

        assertEquals(10, player.getEscudo());
    }

    @Test
    public void ativarImediatoEAumentarEscudoDoPlayer() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.ativarImediato(player);

        assertEquals(10, player.getEscudo());
    }

    @Test
    public void serNotificadoEAumentarEscudoDoPlayer() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.setDono(player);
        efeito.serNotificado("FIM DO ROUND");

        assertEquals(10, player.getEscudo());
    }

    @Test
    public void ativarImediatoEAumentarEscudoDoPlayerComSomaDeAcumulos() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.setDono(player);
        efeito.ativarImediato(player);

        assertEquals(10, player.getEscudo());

        efeito.addAcumulos(2);

        assertEquals(7, efeito.getAcumulos());

        player.setEscudo(0);
        efeito.ativarImediato(player);

        assertEquals(14, player.getEscudo());
    }

    @Test
    public void clonar() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Efeito newEfeito = efeito.clonar();

        assertTrue(newEfeito instanceof EfeitoFaixa);
        assertEquals("FAIXA", newEfeito.getNome());
        assertEquals(5, newEfeito.getAcumulos());
    }
}