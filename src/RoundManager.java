import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;

public class RoundManager {
    // Nessa classe deverá ser desenvovlvida a lógica de batalha dos métodos do App atuais

    private static int level = 1; // O nível define alguns atributos da gameplay
    private static int maxLevel = 10; // Quantos níveis (e inimigos) o jogo terá
    private Heroi player;
    private Inimigo inimigo;
    private List<Observer> subscribers; // Efeitos serão os subscribers desse publisher!

    public RoundManager() {
        this.subscribers = new ArrayList<>();
    }

    public void setPlayer(Heroi player) {
        this.player = player;
    }

    public void setInimigo(Inimigo inimigo) {
        this.inimigo = inimigo;
    }

    public Heroi getPlayer() {
        return this.player;
    }

    public Inimigo getInimigo() {
        return this.inimigo;
    }

/* Métodos de Publisher */
    public void inscrever(Observer subscriber) {
        subscribers.add(subscriber);
    }

    public void desinscrever(Observer subscriber) {
        subscribers.remove(subscriber);
    }

    public void notificar(String evento) {
        List<Observer> subscribers_copia = new ArrayList<>(this.subscribers);
            for (Observer subscriber : subscribers_copia) 
                subscriber.serNotificado(evento, this);
    }

/*Gerenciamento de Jogo */
    public void startGame(Inimigo inimigos[]) { 
        Scanner scanner = App.getScanner();
        App.limparTela();
        IO.println();
        IO.println("Como devemos te chamar?\n");
        String name = scanner.nextLine();
        Heroi player = new Heroi(name);

        App.limparTela();
        IO.println();
        IO.println("    Boa sorte, " + App.ANSI_YELLOW + player.getNome() + App.ANSI_RESET + "!\n" + //
                                                        "                                    -=(o`'. _¬\r\n" + //
                                                        "    Lembre-se: para vencer, use       '.-.\\// \r\n" + //
                                                        "    as cartas ao seu favor            /|  \\\\ \r\n" + //
                                                        "                                      '|  || \r\n" + //
                                                        "                                       _\\_):,_\n");
        IO.println("Digite qualquer coisa para continuar\n");
        String rand = scanner.nextLine();
        
        while (level <= maxLevel) {
            if (level == 4) { // Os próximos níveis serão implementados futuramente
                App.limparTela();
                IO.println();
                IO.println(App.ANSI_YELLOW + "¨_ .'´o)=-\n" + //
                                    " \\\\/.-.'     Agradecemos por jogar!\n" + //
                                    "  //  |\\     Os próximos níveis ainda estão em desenvolvimento, aguarde :)\n" + //
                                    "  ||  |' \n" + //
                                    "_,:(_/_ \n" + App.ANSI_RESET);
                break;
            } 
            else if (level < 4)
                startLevel(player, inimigos);
            else if (level == maxLevel)
                break;
        }
    } 

/* Gerenciamento de Nível */
    public void startLevel(Heroi player, Inimigo inimigos[]) {
        Scanner scanner = App.getScanner();
        App.limparTela();
        IO.println();
        Inimigo inimigo = inimigos[level - 1];

        IO.println("Nível " + level + "\n");
        IO.println(player.getNome() + " acaba de encontrar " + inimigo.getNome() + "\n");
        IO.println(inimigo.getC() + "\n");
        IO.println("Deseja confrontá-lo?\n");
        IO.println("1 - Sim!");
        IO.println("2 - Não...\n");
        int ans = scanner.nextInt();
        scanner.nextLine();

        if (ans == 2) {
            App.limparTela();
            IO.println();
            IO.println("   ! .'´o)=- \n" + //
                                "     /.-.'   Ainda pensando em desistir?!\n" + //
                                "    //  |\\   Siga em frente!\n" + //
                                "    ||  |'  \n" + //
                                "  _,:(_/_   \n"); 
            IO.println("Digite qualquer coisa para continuar\n");
            String rand = scanner.nextLine();
        }

        List <Carta> pilha_descarte = App.criaCartas();
        Stack <Carta> pilha_compra = new Stack<>();
        Collections.shuffle(pilha_descarte);
        while (!pilha_descarte.isEmpty())
            pilha_compra.push(pilha_descarte.remove(0)); 
        App.limparTela();

        while (inimigo.estaVivo() && player.estaVivo()) {
            startRound(player, inimigo, pilha_descarte, pilha_compra, manager);
            notificar("FIM DO ROUND"); // Manager notifica os efeitos de que o round acabou
        }
        App.limparTela();

        // O nível foi finalizado
        if (inimigo.estaVivo()) { // O jogador perdeu
            IO.println("\n");
            IO.println(App.ANSI_YELLOW + "Você perdeu...\n" + App.ANSI_RESET);
            IO.println();
            IO.println(inimigo.getCD());
            IO.println();
            IO.println("Deseja tentar de novo?\n");
            IO.println("1 - Sim!");
            IO.println("2 - Não...\n");
            ans = scanner.nextInt();
            if (ans == 2) {
                App.limparTela();
                IO.println();
                IO.println(App.ANSI_YELLOW + "¨_ .'´o)=-\n" + //
                                    " \\\\/.-.'\n" + //
                                    "  //  |\\     Agradecemos por jogar!\n" + //
                                    "  ||  |' \n" + //
                                    "_,:(_/_ \n" + App.ANSI_RESET);
                level = 10;
            }
        }
        else { // O jogador ganhou
            IO.println("\n");
            IO.println(App.ANSI_YELLOW + "Você ganhou!\n" + App.ANSI_RESET);
            IO.println();
            IO.println(inimigo.getCV());
            IO.println();
            level++;
            IO.println("Deseja continuar nessa aventura?\n");
            IO.println("1 - Sim!");
            IO.println("2 - Não...\n");
            ans = scanner.nextInt();
            if (ans == 2) {
                App.limparTela();
                IO.println();
                IO.println(App.ANSI_YELLOW + "¨_ .'´o)=-\n" + //
                                    " \\\\/.-.'\n" + //
                                    "  //  |\\     Agradecemos por jogar!\n" + //
                                    "  ||  |' \n" + //
                                    "_,:(_/_ \n" + App.ANSI_RESET);
                level = 10;
            }
        }
        resetLevel(player, pilha_compra, pilha_descarte);
    }

    public void resetLevel(Heroi player, Stack <Carta> pilha_compra, List <Carta> pilha_descarte) {
        if (App.getLevel() < 3) 
            player.setVida(40);
        else if (App.getLevel() == 10) 
            player.setVida(100);
        else
            player.setVida(60);
        player.setEscudo(0);
        player.setEnergia(100);

        while (!pilha_compra.isEmpty())
            pilha_descarte.add(pilha_compra.pop());
    }

/* Gerenciamento de Round */
    public void startRound(Heroi player, Inimigo inimigo, List <Carta> pilha_descarte, Stack <Carta> pilha_compra, RoundManager manager) {
        Scanner scanner = App.getScanner();
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
            IO.println(App.ANSI_YELLOW + "                  " + inimigo.getNome() + " (Vida = " + inimigo.getVida() + ")\n" + App.ANSI_RESET);
            IO.println(inimigo.getC() + "\n");
            IO.println(App.ANSI_YELLOW + "  " + player.getNome() + " (Vida = " + player.getVida() + " / Defesa = " + player.getEscudo() + ")\n" + App.ANSI_RESET);
            IO.println("Nas suas nadadeiras existem cartas\nDeseja usá-las?\n");
            IO.println("Energia: " + player.getEnergia());
            IO.println();

            int i = 0;
            for ( ; i < nadadeira.size(); i++) {
                if (nadadeira.get(i).getDescricao().equals("Carta de Ataque"))
                    IO.println(i + " - " + nadadeira.get(i).getDescricao() + ": " + App.ANSI_PURPLE + nadadeira.get(i).getNome() + App.ANSI_RESET + " (Dano = " + nadadeira.get(i).getValor() + " / Custo = " + nadadeira.get(i).getCusto() + ")");
                else if (nadadeira.get(i).getDescricao().equals("Carta de Defesa"))
                    IO.println(i + " - " + nadadeira.get(i).getDescricao() + ": " + App.ANSI_PURPLE + nadadeira.get(i).getNome() + App.ANSI_RESET + " (Defesa = " + nadadeira.get(i).getValor() + " / Custo = " + nadadeira.get(i).getCusto() + ")");
                else
                    IO.println(i + " - Carta de efeito: " + App.ANSI_PURPLE + nadadeira.get(i).getNome() + App.ANSI_RESET + ": " + nadadeira.get(i).getDescricao());
            }
            IO.println(i + " - Finalizar turno\n");

            int ans = scanner.nextInt();
            scanner.nextLine();

            if (ans > i || ans < 0) { // O jogador escolheu uma opção inválida
                App.limparTela();
                IO.println(App.ANSI_YELLOW + "Ei, não tente fugir dessa! Escolha uma das opções disponíveis\n" + App.ANSI_RESET);
                continue;
            }
            if (ans == i) { // O jogador escolheu a opção "Finalizar turno"
                while (!nadadeira.isEmpty())
                    pilha_descarte.add(nadadeira.remove(0)); 

                App.limparTela();
                manager.notificar("INIMIGO VAI ATACAR");
                inimigo.atacar(player); // Manager notifica os efeitos de que o inimigo vai atacar
                resetRound(inimigo, player);
                break;
            }

            // O jogador escolheu uma carta
            App.limparTela();
            if (nadadeira.get(ans).getDescricao() == "Carta de Ataque")
                manager.notificar("PLAYER VAI ATACAR"); // Manager notifica os efeitos de que o player vai atacar

            nadadeira.get(ans).usar(player, inimigo, manager); 
            pilha_descarte.add(nadadeira.remove(ans)); 
            if (!inimigo.estaVivo())
                break;
        }
    }

    public void resetRound(Inimigo inimigo, Heroi player) {
        inimigo.setEscudo(0);
        player.setEscudo(0);
        player.setEnergia(100);
    }
}
