package org.penguinfight;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.HashMap;
import java.util.Map;

public class Mapa {
    DefaultMutableTreeNode root;
    Map <Integer, String> lugares; // Dicionário que associa os números dos nós ao Nome dos locais do mapa
    
    public Mapa() {
        this.root = new DefaultMutableTreeNode(0);
        this.lugares = new HashMap<>();

        DefaultMutableTreeNode l21 = new DefaultMutableTreeNode(1);
        DefaultMutableTreeNode l22 = new DefaultMutableTreeNode(2);
        DefaultMutableTreeNode l23 = new DefaultMutableTreeNode(3);
        DefaultMutableTreeNode l31 = new DefaultMutableTreeNode(4);
        DefaultMutableTreeNode l32 = new DefaultMutableTreeNode(5);
        DefaultMutableTreeNode l33 = new DefaultMutableTreeNode(6);
        DefaultMutableTreeNode l41 = new DefaultMutableTreeNode(7);
        DefaultMutableTreeNode l42 = new DefaultMutableTreeNode(8);
        DefaultMutableTreeNode l43 = new DefaultMutableTreeNode(9);

        this.root.add(l21);
        this.root.add(l22);
        this.root.add(l23);
        l21.add(l31);
        l21.add(l32);
        l22.add(l32);
        l22.add(l33);
        l23.add(l33);
        l31.add(l41);
        l32.add(l42);
        l33.add(l43);

        this.lugares.put(0, "Iglu"); // Puffle
        this.lugares.put(1, "Centro"); // Guitarrista
        this.lugares.put(2, "Plaza"); // Pizzaiolo
        this.lugares.put(3, "Praia"); // Surfista
        this.lugares.put(4, "Estação de Esqui"); // Gary
        this.lugares.put(5, "Forte Nevado"); // Rookie
        this.lugares.put(6, "Casinha da Mina"); // Operário
        this.lugares.put(7, "Montanha"); // Herbert
        this.lugares.put(8, "Pátio do Dojo"); // Sensei
        this.lugares.put(9, "Iceberg"); // Klutzy
    }
}
