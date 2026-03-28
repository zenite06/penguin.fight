import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class RoundManager {
    // Nessa classe deverá ser desenvovlvida a lógica de batalha dos métodos do App atuais
    private Heroi player;
    private Inimigo inimigo;
    List <Carta> pilha_descarte;
    Stack <Carta> pilha_compra;
    private List<Efeito> efeitos_ativos = new ArrayList<>();
    public static final String ANSI_RED = "\u001B[1;31m";
    public static final String ANSI_GREEN = "\u001B[1;32m";
    public static final String ANSI_YELLOW = "\033[1;33m";
    public static final String ANSI_PURPLE = "\033[1;35m";
    public static final String ANSI_RESET = "\u001B[0m";

    public RoundManager(Heroi player, Inimigo inimigo, List <Carta> pilha_descarte, Stack <Carta> pilha_compra) {
        this.player = player;
        this.inimigo = inimigo;
        this.pilha_descarte = pilha_descarte;
        this.pilha_compra = pilha_compra;
        this.efeitos_ativos = new ArrayList<Efeito>();
    }

    public void startRound() {
        Scanner scanner = new Scanner(System.in);
        List<Carta> nadadeira = new ArrayList<>();
        
        for (int i = 0; i < 6; i++) {
            if (pilha_compra.isEmpty()) { // Se a pilha de compra estiver vazia, as cartas da pilha de descarte são embaralhadas e passam pro topo da pilha de compra
                Collections.shuffle(pilha_descarte);
                while (!pilha_descarte.isEmpty())
                    pilha_compra.push(pilha_descarte.remove(0)); 
            } 
            nadadeira.add(pilha_compra.pop()); // São compradas cinco cartas da pilha de compra para a mão do jogador (nadadeira)
        }

        int ataque = (int)(Math.random() * 2); // Define o ataque do inimigo
        while (player.estaVivo()) {
            IO.println();
            inimigo.declarar(ataque, player);
            IO.println(ANSI_YELLOW + "                  " + inimigo.getNome() + " (Vida = " + inimigo.getVida() + ")\n" + ANSI_RESET);
            IO.println(inimigo.getC() + "\n");
            IO.println(ANSI_YELLOW + "  " + player.getNome() + " (Vida = " + player.getVida() + " / Defesa = " + player.getEscudo() + ")\n" + ANSI_RESET);
            IO.println("Nas suas nadadeiras existem cartas\nDeseja usá-las?\n");
            IO.println("Energia: " + player.getEnergia());
            IO.println();

            int i = 0;
            for ( ; i < nadadeira.size(); i++) {
                if (nadadeira.get(i).getDescricao() == "Carta de Ataque")
                    IO.println(i + " - " + nadadeira.get(i).getDescricao() + ": " + ANSI_PURPLE + nadadeira.get(i).getNome() + ANSI_RESET + " (Dano = " + nadadeira.get(i).getValor() + " / Custo = " + nadadeira.get(i).getCusto() + ")");
                else
                    IO.println(i + " - " + nadadeira.get(i).getDescricao() + ": " + ANSI_PURPLE + nadadeira.get(i).getNome() + ANSI_RESET + " (Defesa = " + nadadeira.get(i).getValor() + " / Custo = " + nadadeira.get(i).getCusto() + ")");
            }
            IO.println(i + " - Finalizar turno\n");

            int ans = scanner.nextInt();
            scanner.nextLine();

            if (ans > i || ans < 0) { // O jogador escolheu uma opção inválida
                limparTela();
                IO.println(ANSI_YELLOW + "Ei, não tente fugir dessa! Escolha uma das opções disponíveis\n" + ANSI_RESET);
                continue;
            }
            if (ans == i) { // O jogador escolheu a opção "Finalizar turno"
                while (!nadadeira.isEmpty())
                    pilha_descarte.add(nadadeira.remove(0)); 
                limparTela();
                inimigo.atacar(player);
                resetRound(inimigo, player);
                break;
            }
            limparTela();
            nadadeira.get(ans).usar(player, inimigo); // O jogador escolheu uma carta
            pilha_descarte.add(nadadeira.remove(ans)); 
            if (!inimigo.estaVivo())
                break;
        }
        scanner.close();
    }

    public static void resetRound(Inimigo inimigo, Heroi player) {
        inimigo.setEscudo(0);
        player.setEscudo(0);
        player.setEnergia(100);
    }

    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
        }
    }

}
