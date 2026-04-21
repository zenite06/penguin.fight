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
    public void setarDono() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.setDono(player);
        assertEquals(player, efeito.getDono());
    }

    /* @Test
    public void aplicarFaixa() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito cartaEfeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, cartaEfeito);
        Heroi player = new Heroi("Pinguim", 40);
        efeito.setDono(player);
        App.manager.setBattle(new Batalha(inimigo, "Iglu"));
        player.aplicarEfeito(efeito);
        efeito.aplicarFaixa();
        assertEquals("      .'´o)=- \n" + //
                    "      /.-.' \n" + //
                    "     //   |\\    VS \n" + //
                    "     ||" + "\u001B[1;34m" + "===" + "\u001B[0m" + "|'               WWWWW\n" + //
                    "   _,:(_/_                (o-o)", inimigo.getC());
    } */

    @Test
    public void ativar() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.setDono(player);
        efeito.ativar(player);
        assertEquals(10, player.getEscudo());
    }

    @Test
    public void ativarImediato() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.ativarImediato(player);
        assertEquals(10, player.getEscudo());
    }

    @Test
    public void estaSendoNotificado() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        String evento = "FIM DO ROUND";
        efeito.setDono(player);
        efeito.serNotificado(evento);
        assertEquals(10, player.getEscudo());
    }

    @Test
    public void clonar() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Efeito newEfeito = efeito.clonar();
        assertEquals("FAIXA", newEfeito.getNome());
        assertEquals(5, newEfeito.getAcumulos());
    }

    @Test
    public void ativarImediatoComSomaDeAcumulos() {
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
}