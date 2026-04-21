package org.penguinfight.Cartas;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Efeitos.EfeitoFaixa;
import org.penguinfight.Efeitos.EfeitoPeixe;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

public class CartaEfeitoTest {

    @Test
    public void criarCartaEfeito() {
        EfeitoPeixe efeito = new EfeitoPeixe(10);
        CartaEfeito carta = new CartaEfeito("BACALHAU", "Aumenta em 10 pontos a energia para a próxima rodada", 10, efeito);
        assertEquals("BACALHAU", carta.getNome());
        assertEquals("Aumenta em 10 pontos a energia para a próxima rodada", carta.getDescricao());
        assertEquals(10, carta.getCusto());
        assertEquals(efeito, carta.getEfeito());
    }

    @Test
    public void usarCartaEfeitoComEnergiaSuficiente() {
        Heroi player = new Heroi("Pinguim", 40);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", null, null, null, null);
        EfeitoPeixe efeito = new EfeitoPeixe(10);
        CartaEfeito carta = new CartaEfeito("PEIXE", "Aumenta em 10 pontos a energia para a próxima rodada", 10, efeito);
        carta.usar(player, inimigo);
        assertEquals(90, player.getEnergia());
        boolean encontrou = false;
        for (Efeito efeitoAtivo : player.getEfeitos()) {
            if (efeitoAtivo.getNome().equals(efeito.getNome()) && efeitoAtivo.getAcumulos() == efeito.getAcumulos()) {
                encontrou = true;
                break;
            }
        }
        assertTrue(encontrou); 
    }

    @Test
    public void usarCartaEfeitoSemEnergiaSuficiente() {
        Heroi player = new Heroi("Pinguim", 40);
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "", "", "", null, null, null, null);
        EfeitoPeixe efeito = new EfeitoPeixe(10);
        CartaEfeito carta = new CartaEfeito("PEIXE", "Aumenta em 10 pontos a energia para a próxima rodada", 10, efeito);
        player.setEnergia(5);
        carta.usar(player, inimigo);
        assertEquals(5, player.getEnergia());
        boolean encontrou = false;
        for (Efeito efeitoAtivo : player.getEfeitos()) {
            if (efeitoAtivo.getNome().equals(efeito.getNome()) && efeitoAtivo.getAcumulos() == efeito.getAcumulos()) {
                encontrou = true;
                break;
            }
        }
        assertFalse(encontrou); 
    }
}
