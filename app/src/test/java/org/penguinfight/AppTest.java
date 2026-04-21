package org.penguinfight;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Entidades.Inimigo;

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
        DefaultMutableTreeNode batalhaIglu = (DefaultMutableTreeNode) mapa.getChildAt(0);
        assertEquals(3, batalhaIglu.getChildCount());
        assertEquals(4, mapa.getDepth());
        Object no = batalhaIglu.getUserObject();
        assertTrue(no instanceof Batalha);
    }
}