package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.Batalha;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EfeitoNevascaTest {
    @Test
    public void criarEfeitoNevasca() {
        EfeitoNevasca efeito = new EfeitoNevasca(50);
        assertEquals(50, efeito.getAcumulos());
        assertEquals("NEVASCA", efeito.getNome());
    }

    @Test
    public void ativar() { 
        EfeitoNevasca efeito = new EfeitoNevasca(1);
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito cartaEfeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, cartaEfeito);
        Heroi player = new Heroi("Pinguim", 40);
        App.manager.setBattle(new Batalha(inimigo, "Geleira"));
        App.manager.setPlayer(player);
        int ataqueOriginal = inimigo.getAtaque(inimigo.getDecisao(0)).getValor();
        efeito.ativar(inimigo);
        assertEquals(0, efeito.getAcumulos());
        assertEquals((int) (ataqueOriginal * 0.5), inimigo.getAtaque(inimigo.getDecisao(0)).getValor());
    }

    @Test
    public void resetar() { 
        EfeitoNevasca efeito = new EfeitoNevasca(1);
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito cartaEfeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, cartaEfeito);
        Heroi player = new Heroi("Pinguim", 40);
        player.aplicarEfeito(efeito);
        efeito.setDono(player);
        App.manager.setBattle(new Batalha(inimigo, "Geleira"));
        App.manager.setPlayer(player);
        int ataqueOriginal = inimigo.getAtaque(inimigo.getDecisao(0)).getValor();
        efeito.ativar(inimigo);
        efeito.resetar(inimigo);
        assertEquals(ataqueOriginal, inimigo.getAtaque(inimigo.getDecisao(0)).getValor());
    }

    @Test
    public void estaSendoNotificadoEResetando() { 
        EfeitoNevasca efeito = new EfeitoNevasca(1);
        CartaDano ataque1 = new CartaDano("COTOVELADA", "Carta de Ataque", 0, 2);
        CartaDano ataque2 = new CartaDano("SOCO", "Carta de Ataque", 0, 4);
        CartaEscudo defesa = new CartaEscudo("ESQUIVA", "Carta de Defesa", 0, 2);
        CartaEfeito cartaEfeito = new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(1, 5));
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "Capa", "Capa Vitória", "Capa Derrota", ataque1, ataque2, defesa, cartaEfeito);
        Heroi player = new Heroi("Pinguim", 40);
        player.aplicarEfeito(efeito);
        efeito.setDono(player);
        App.manager.setBattle(new Batalha(inimigo, "Geleira"));
        App.manager.setPlayer(player);
        int ataqueOriginal = inimigo.getAtaque(inimigo.getDecisao(0)).getValor();
        String evento1 = "INIMIGO VAI ATACAR";
        efeito.serNotificado(evento1);
        assertEquals(0, efeito.getAcumulos());
        assertEquals((int) (ataqueOriginal * 0.5), inimigo.getAtaque(inimigo.getDecisao(0)).getValor());
        
        String evento2 = "INIMIGO ATACOU";
        efeito.serNotificado(evento2);
        assertEquals(ataqueOriginal, inimigo.getAtaque(inimigo.getDecisao(0)).getValor());
    }

    @Test
    public void clonar() {
        EfeitoNevasca efeito = new EfeitoNevasca(50);
        Efeito newEfeito = efeito.clonar();
        assertEquals("NEVASCA", newEfeito.getNome());
        assertEquals(50, newEfeito.getAcumulos());
    }
}