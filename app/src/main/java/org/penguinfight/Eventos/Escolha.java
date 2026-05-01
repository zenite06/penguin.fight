package org.penguinfight.Eventos;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.penguinfight.App;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Entidades.Heroi;

/**
 * Evento especializado em escolhas interpretativas. Funciona como um RPG de texto 
 * simplificado inserido dentro do mapa, oferecendo um pequeno dilema que pode ou 
 * não recompensar o jogador.
 */
public class Escolha extends Evento {
    private String story; // Enunciado da escolha
    private String img; // Imagem da escolha
    private int value; // Valor (dano ou recompensa)

    /*Todas as escolhas possuem uma resposta "certa", que é a que te trará uma recompensa. O tipo 
    da escolha define qual é a resposta certa (true se for "sim" e false se for "não") */ 
    private boolean type; 

    public Escolha(String local, String story, String img, int value, boolean type) {
        super(local);
        this.story = story;
        this.img = img;
        this.value = value;
        this.type = type;
    }

    /**
     * Executa o enredo da escolha e aguarda a resposta do herói.
     * 
     * @return true informando ao Manager que a decisão foi tomada.
     */
    public boolean iniciar() {
        App.limparTela();

        boolean over = false;
        while(over == false) {
            IO.println(story + "\n");
            IO.println(img + "\n");
            IO.println("1 - Sim!");
            IO.println("2 - Não...\n");
            int ans = App.scanner.nextInt();
            switch (ans) {
                case 1:
                    if (type == true) {
                        Heroi.getInstance().ganharMoedas(this.value);
                        IO.println("Você ganhou " + App.ANSI_GREEN + this.value + App.ANSI_RESET + " moedas!\n");
                    } else {
                        IO.println("Não foi dessa vez...\n");
                    }
                    over = true;
                    break;
                case 2:
                    if (type == false) {
                        Heroi.getInstance().ganharMoedas(this.value);
                        IO.println("Você ganhou " + App.ANSI_GREEN + this.value + App.ANSI_RESET + " moedas!\n");
                    } else {
                        IO.println("Não foi dessa vez...\n");
                    }
                    over = true;
                    break;
                default:
                    IO.println("Opção inválida! Escolha outra opção");
                    iniciar();
                    break;
            }
        }
        return true;
    }
}
