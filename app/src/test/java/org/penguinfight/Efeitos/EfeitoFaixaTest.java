package org.penguinfight.Efeitos;
import org.penguinfight.Entidades.Heroi;
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

    @Test
    public void ativarImediato() {
        EfeitoFaixa efeito = new EfeitoFaixa(5);
        Heroi player = new Heroi("Player", 40);
        efeito.ativarImediato(player);
        assertEquals(10, player.getEscudo());
    }
}