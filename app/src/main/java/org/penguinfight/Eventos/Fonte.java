package org.penguinfight.Eventos;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import org.penguinfight.App;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Entidades.Heroi;

public class Fonte extends Evento {

    public Fonte(String local) {
        super(local);
    }
    
    public boolean iniciar() {
        Scanner scanner = App.getScanner();
        App.limparTela();
        IO.println(Heroi.getInstance().getNome() + " acaba de chegar à " + this.local);
        IO.println(App.lerTXT("src/main/resources/Assets/fonte.txt" + "\n\n"));

        int result = 0;
        while (result < 3) {
            IO.println("Deseja nadar nas águas termais e recuperar sua vida?\n");
            IO.println("1 - Sim!");
            IO.println("2 - Não...\n");
            int ans = scanner.nextInt();

            switch (ans) {
                case 1:
                    App.limparTela();
                    IO.println(App.lerTXT("src/main/resources/Assets/f2.txt" + "\n\n"));
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
                    IO.println("\nPronto!");
                    IO.println("Digite qualquer coisa para continuar\n");
                    String rand = scanner.nextLine();
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
                        rand = scanner.nextLine();
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
