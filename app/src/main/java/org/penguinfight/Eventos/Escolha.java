package org.penguinfight.Eventos;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.penguinfight.App;
import org.penguinfight.Cartas.Carta;

public class Escolha extends Evento {
    String story; // Enunciado da escolha
    String img; // Imagem da escolha
    int value; // Valor (dano ou recompensa)

    /*Todas as escolhas possuem uma resposta "certa", que é a que te trará uma recompensa. O tipo 
    da escolha define qual é a resposta certa (true se for "sim" e false se for "não")*/ 
    boolean type; 

    public Escolha(String local, String story, String img, int value, boolean type) {
        super(local);
        this.story = story;
        this.img = img;
        this.value = value;
        this.type = type;
    }

    public boolean iniciar() {
        App.limparTela();
        IO.println(story);
        IO.println(img);
        IO.println("1 - Sim!");
        IO.println("2 - Não...");
        int ans = App.scanner.nextInt();
        App.limparTela();
        return true;
    }
}
