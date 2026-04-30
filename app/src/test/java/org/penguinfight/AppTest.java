package org.penguinfight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Entidades.Inimigo;
import org.penguinfight.Eventos.Batalha;

public class AppTest {

    @Test
    public void criarCartas() {
        List<Carta> cartas = App.criaCartas();
        
        assertNotNull(cartas);
        assertEquals(17, cartas.size());
    }

    @Test
    public void criarMapa() {
        DefaultMutableTreeNode mapa = App.criaMapa();

        assertNotNull(mapa);
        assertEquals(1, mapa.getChildCount());

        DefaultMutableTreeNode iglu = (DefaultMutableTreeNode) mapa.getChildAt(0);

        assertEquals(3, iglu.getChildCount());
        assertEquals(6, mapa.getDepth());
        assertTrue(iglu.getUserObject() instanceof Batalha);
    }
}