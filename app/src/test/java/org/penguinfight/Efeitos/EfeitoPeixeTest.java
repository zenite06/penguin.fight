package org.penguinfight.Efeitos;
import org.penguinfight.App;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EfeitoPeixeTest {
    private static Heroi player = Heroi.getInstance("Pinguim", 40, null, null);

    @Test
    public void criarEfeitoPeixe() {
        EfeitoPeixe efeito = new EfeitoPeixe(20);

        assertEquals(20, efeito.getAcumulos());
        assertEquals("PEIXE", efeito.getNome());
    }

    @Test
    public void ativarEAumentarEnergiaDoPlayer() {
        EfeitoPeixe efeito = new EfeitoPeixe(20);
        efeito.setDono(player);
        efeito.ativar(player);

        assertEquals(120, player.getEnergia());
        assertEquals(0, player.getEfeitos().size());
    }

    @Test
    public void serNotificadoEAumentarEnergiaDoPlayer() { 
        EfeitoPeixe efeito = new EfeitoPeixe(20);
        player.aplicarEfeito(efeito);
        efeito.setDono(player);
        efeito.serNotificado("FIM DO ROUND");

        assertEquals(120, player.getEnergia());
    }

    @Test
    public void clonar() {
        EfeitoPeixe efeito = new EfeitoPeixe(20);
        Efeito newEfeito = efeito.clonar();

        assertTrue(newEfeito instanceof EfeitoPeixe);
        assertEquals("PEIXE", newEfeito.getNome());
        assertEquals(20, newEfeito.getAcumulos());
    }
}
