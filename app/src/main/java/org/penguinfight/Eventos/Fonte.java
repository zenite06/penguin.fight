package org.penguinfight.Eventos;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.penguinfight.App;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Entidades.Heroi;

/**
 * Classe que estende Evento para criar um sistema de descanso e melhoria (Fogueira/Fonte).
 * Oferece ao jogador uma área segura no mapa para restaurar sua vida.
 */
public class Fonte extends Evento {

    public Fonte(String local) {
        super(local);
    }
    
    /**
     * Apresenta a interface narrativa e as opções interativas do evento.
     * Executa a animação progressiva da recuperação da barra de vida 
     * no console caso o herói aceite descansar.
     * 
     * @return true, sinalizando que a exploração do evento foi completada com sucesso.
     */
    public boolean iniciar() {
        Scanner scanner = App.getScanner();
        App.limparTela();
        IO.println(Heroi.getInstance().getNome() + " acaba de chegar a " + this.local + "\n");
        IO.println(App.lerTXT("src/main/resources/Assets/fonte.txt") + "\n\n");

        int result = 0;
        while (result < 3) {
            IO.println("Deseja nadar nas águas termais e recuperar sua vida?\n");
            IO.println("1 - Sim!");
            IO.println("2 - Não...\n");
            int ans = scanner.nextInt();

            switch (ans) {
                case 1:
                    App.limparTela();
                    IO.println(App.lerTXT("src/main/resources/Assets/f2.txt") + "\n\n");
                    IO.println("Recuperando a vida...\n");

                    for (int i = 0; i < Heroi.getInstance().getVida(); i++) 
                        IO.print("|");

                    int vidaFaltante = Heroi.getInstance().getMaxVida() - Heroi.getInstance().getVida();
                    for (int i = 0; i < vidaFaltante; i ++) {
                        IO.print(App.ANSI_GREEN + "|" + App.ANSI_RESET);
                        try {
                            TimeUnit.SECONDS.sleep(1);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    Heroi.getInstance().setVida(Heroi.getInstance().getMaxVida());
                    App.limparTela();
                    IO.println("\nPronto!\n");
                    result = 3;
                    break;
                case 2:
                    if (result == 0) {
                        App.limparTela();
                        IO.println("Não quer mesmo aproveitar a fonte?");
                        result++;
                    } else if (result == 1) {
                        App.limparTela();
                        IO.println("*Águas quentinhas* Quer mesmo perder isso?");
                        result++;
                    } else if (result == 2) {
                        App.limparTela();
                        IO.println("Tudo bem, eu avisei");
                        IO.println("Digite qualquer coisa para continuar\n");
                        result++;
                    }
                    break;
                default:
                    IO.println("Opção inválida! Escolha outra opção");
                    break;
            }
        }
        return true;
    }
}
