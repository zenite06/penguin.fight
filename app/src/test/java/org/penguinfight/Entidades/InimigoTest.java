package org.penguinfight.Entidades;
import org.junit.jupiter.api.Test;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Efeitos.EfeitoAcido;
import org.penguinfight.Cartas.CartaEfeito;
import static org.junit.jupiter.api.Assertions.*;

public class InimigoTest {

    @Test
    public void criarInimigo() {
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "      .'´o)=-      -=(o`'.\n" + //
    "      /.-.'           '.-.\\\\\n" + //
    "     //  |\\    VS     /|  \\\\\\\\\n" + //
    "     ||  |'           '|  ||\n" + //
    "   _,:(_/_             _\\_):,_", "      .'´o)=-      -=(X`'.\n" + //
    "      /.-.'           '.-.\\\\\n" + //
    "     //  |\\    VS     /|  \\\\\\\\\n" + //
    "     ||  |'           '|  ||\n" + //
    "   _,:(_/_             _\\_):,_", "      .'´X)=-      -=(o`'.\n" + //
    "      /.-.'           '.-.\\\\\n" + //
    "     //  |\\    VS     /|  \\\\\\\\\n" + //
    "     ||  |'           '|  ||\n" + //
    "   _,:(_/_             _\\_):,_", new CartaDano("O CHUTE", "Carta de Ataque", 0, 20), new CartaDano("O SOCO", "Carta de Ataque", 0, 10), new CartaEscudo("A ESQUIVA", "Carta de Defesa", 0, 5), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5)));

        assertEquals("Pinguim Malvado", inimigo.getNome());
        assertEquals(50, inimigo.getVida());
        assertEquals("      .'´o)=-      -=(o`'.\n" + //
    "      /.-.'           '.-.\\\\\n" + //
    "     //  |\\    VS     /|  \\\\\\\\\n" + //
    "     ||  |'           '|  ||\n" + //
    "   _,:(_/_             _\\_):,_", inimigo.getC());
        assertEquals("      .'´o)=-      -=(X`'.\n" + //
    "      /.-.'           '.-.\\\\\n" + //
    "     //  |\\    VS     /|  \\\\\\\\\n" + //
    "     ||  |'           '|  ||\n" + //
    "   _,:(_/_             _\\_):,_", inimigo.getCV());
        assertEquals("      .'´X)=-      -=(o`'.\n" + //
    "      /.-.'           '.-.\\\\\n" + //
    "     //  |\\    VS     /|  \\\\\\\\\n" + //
    "     ||  |'           '|  ||\n" + //
    "   _,:(_/_             _\\_):,_", inimigo.getCD());
        assertTrue(inimigo.estaVivo());
    }  

    @Test
    public void testReceberDanoInimigo() {
        // Passamos 'null' nas cartas pois não são necessárias para testar a vida base
        Inimigo inimigo = new Inimigo("Pinguim Malvado", 50, "capa1", "capa2", "capa3", null, null, null, null);
        
        inimigo.receberDano(20);
        assertEquals(30, inimigo.getVida(), "A vida do inimigo deve cair corretamente após o ataque.");
        assertTrue(inimigo.estaVivo());
    }
}