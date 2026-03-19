import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Collections;

public class App {
    private static int level = 1; // O nível define alguns atributos da gameplay
    private static int maxLevel = 10; // Quantos níveis (e inimigos) o jogo terá
    static Scanner scanner = new Scanner(System.in);
    public static final String ANSI_RED = "\u001B[1;31m";
    public static final String ANSI_GREEN = "\u001B[1;32m";
    public static final String ANSI_YELLOW = "\033[1;33m";
    public static final String ANSI_PURPLE = "\033[1;35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static void main(String[] args) throws Exception {
        limparTela();
        IO.println("  ____                        _          __ _       _     _    __             __\n" + //
                        " |  _ \\ ___ _ __   __ _ _   _(_)_ __    / _(_) __ _| |__ | |_ / /  -=(o`'.    \\ \\ \n" + //
                        " | |_) / _ \\ '_ \\ / _` | | | | | '_ \\  | |_| |/ _` | '_ \\| __| |     '.-.\\     | |\n" + //
                        " |  __/  __/ | | | (_| | |_| | | | | |_|  _| | (_| | | | | |_| |     /|  \\\\    | |\n" + //
                        " |_|   \\___|_| |_|\\__, |\\__,_|_|_| |_(_)_| |_|\\__, |_| |_|\\__| |     '|  ||    | |\n" + //
                        "                  |___/                       |___/           \\_\\     _\\_):,_ /_/ \n");
        
        IO.println("Saudações, pinguim! A ilha está em apuros e precisa da sua ajuda, só lhe resta lutar para encontrar o verdadeiro inimigo. Aceita essa aventura?\n");  
        IO.println("1 - Sim!");
        IO.println("2 - Não...\n");

        int answer = scanner.nextInt();
        scanner.nextLine();

        if (answer != 1) {
            limparTela();
            IO.println();
            IO.println("  -=(o`'. ?!\r\n" + //
                                "    '.-.\\ \r\n" + //
                                "    /|  \\\\     Tarde demais para desistir agora. Aguente firme!\r\n" + //
                                "    '|  || \r\n" + //
                                "     _\\_):,_\n");
            IO.println("Digite qualquer coisa para continuar\n");
            String rand = scanner.nextLine();
        }
        Inimigo inimigos[] = criaInimigos(); // Deverão ser passados às funções subsequentes!
        startGame(inimigos);
        scanner.close();
    }

    public static Inimigo[] criaInimigos() {
        // Nesse método serão instanciados todos os inimigos do jogo
        Inimigo inimigos[] = new Inimigo[maxLevel];
        inimigos[0] = new Inimigo("Gary", 40, 15, "     .'´o)=-      -=(O¬'.\n" + //
                        "     /.-.'           '._.\\\n" + //
                        "    //  |\\    VS    /| V \\\\\n" + //
                        "    ||  |'          '|   ||\n" + //
                        "  _,:(_/_            _\\ _):,_\n", "    Consegui! \n" + //
                                                            "       V\n" + //
                                                            "     .'´o)=-      -=(X¬'.\n" + //
                                                            "     /.-.'           '._.\\\n" + //
                                                            "    //  |\\    VS    /| V \\\\\n" + //
                                                            "    ||  |'          '|   ||\n" + //
                                                            "  _,:(_/_            _\\ _):,_\n", "            Mais sorte na próxima!\n" + //
                                                                                                "                      V\n" + //
                                                                                                "     .'´X)=-      -=(O¬'. \n" + //
                                                                                                "     /.-.'           '._.\\ \n" + //
                                                                                                "    //  |\\    VS    /| V \\\\\n" + //
                                                                                                "    ||  |'          '|   ||\n" + //
                                                                                                "  _,:(_/_            _\\ _):,_", new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 15)); 
        
        inimigos[1] = new Inimigo("Rookie", 50, 13, "                     _T_\n" + //
                        "     .'´o)=-      -=(V¬'.\n" + //
                        "     /.-.'           '.-.\\\n" + //
                        "    //  |\\    VS    /|*V*\\\\\n" + //
                        "    ||  |'          '|*_*_||\n" + //
                        "  _,:(_/_            _\\ _):,_", "    Consegui!\n" + //
                                                        "        V            _T_\n" + //
                                                        "     .'´o)=-      -=(X¬'.\n" + //
                                                        "     /.-.'           '.-.\\\n" + //
                                                        "    //  |\\    VS    /|*V*\\\\\n" + //
                                                        "    ||  |'          '|*_*_||\n" + //
                                                        "  _,:(_/_            _\\ _):,_", "            Mais sorte na próxima!   \n" + //
                                                                                                                        "                      V\n" + //
                                                                                                                        "                     _T_\n" + //
                                                                                                                        "     .'´X)=-      -=(V¬'.\n" + //
                                                                                                                        "     /.-.'           '.-.\\\n" + //
                                                                                                                        "    //  |\\    VS    /|*V*\\\\\n" + //
                                                                                                                        "    ||  |'          '|*_*_||\n" + //
                                                                                                                        "  _,:(_/_            _\\ _):,_", new CartaDano("A VOADORA ESTILOSA", "Carta de Ataque", 0, 15), new CartaDano("A NADADEIRA SÔNICA", "Carta de Ataque", 0, 16)); 
        
        inimigos[2] = new Inimigo("Klutzy", 70, 20, "     .'´o)=- \n" + //
                        "     /.-.' \n" + //
                        "    //  |\\    VS \n" + //
                        "    ||  |'         (V) O O (V)\n" + //
                        "  _,:(_/_            `(, ,)´", "   Consegui!\n" + //
                                                        "       V\n" + //
                                                        "     .'´o)=- \n" + //
                                                        "     /.-.' \n" + //
                                                        "    //  |\\    VS \n" + //
                                                        "    ||  |'         (V) X X (V)\n" + //
                                                        "  _,:(_/_            `(, ,)´", "     .'´X)=- \n" + //
                                                                                                                        "     /.-.'    Mais sorte na próxima!\n" + //
                                                                                                                        "    //  |\\    VS        V\n" + //
                                                                                                                        "    ||  |'         (V) O O (V)\n" + //
                                                                                                                        "  _,:(_/_            `(, ,)´", new CartaDano("O CORTE AFIADO", "Carta de Ataque", 0, 15), new CartaDano("O BELISCÃO DE AÇO", "Carta de Ataque", 0, 20)); 
        return inimigos;
    }

    public static List <Carta> criaCartas() {
        // Nesse método serão instanciados todas as cartas do baralho
        List<Carta> cartas = new ArrayList<>();
        cartas.add(new CartaDano("A COTOVELADA IMPROVISADA", "Carta de Ataque", 10, 2));
        cartas.add(new CartaDano("O SOCO VOADOR", "Carta de Ataque", 20, 5));
        cartas.add(new CartaDano("O CHUTE PERICULOSO", "Carta de Ataque", 40, 10));
        cartas.add(new CartaDano("A JOELHADA TRIUNFAL", "Carta de Ataque", 70, 20));
        cartas.add(new CartaDano("A IMOBILIZAÇÃO FATAL", "Carta de Ataque", 90, 30));
        cartas.add(new CartaEscudo("A ESQUIVA DESAJEITADA", "Carta de Defesa", 10, 2));
        cartas.add(new CartaEscudo("A ESQUIVA NORMAL", "Carta de Defesa", 30, 2));
        cartas.add(new CartaEscudo("A ESQUIVA PERFEITA", "Carta de Defesa", 50, 8));
        cartas.add(new CartaEscudo("O BLOQUEIO BRUTAL", "Carta de Defesa", 70, 10));
        cartas.add(new CartaEscudo("O BLOQUEIO MILENAR", "Carta de Defesa", 90, 15));
        return cartas;
    }

    public static void startGame(Inimigo inimigos[]) { 
        limparTela();
        IO.println();
        IO.println("Como devemos te chamar?\n");
        String name = scanner.nextLine();
        Heroi player = new Heroi(name);

        limparTela();
        IO.println();
        IO.println("    Boa sorte, " + ANSI_YELLOW + player.getNome() + ANSI_RESET + "!\n" + //
                                                        "                                    -=(o`'. _¬\r\n" + //
                                                        "    Lembre-se: para vencer, use       '.-.\\// \r\n" + //
                                                        "    as cartas ao seu favor            /|  \\\\ \r\n" + //
                                                        "                                      '|  || \r\n" + //
                                                        "                                       _\\_):,_\n");
        IO.println("Digite qualquer coisa para continuar\n");
        String rand = scanner.nextLine();
        
        while (level <= maxLevel) {
            if (level == 4) { // Os próximos níveis serão implementados futuramente
                limparTela();
                IO.println();
                IO.println(ANSI_YELLOW + "¨_ .'´o)=-\n" + //
                                    " \\\\/.-.'     Agradecemos por jogar!\n" + //
                                    "  //  |\\     Os próximos níveis ainda estão em desenvolvimento, aguarde :)\n" + //
                                    "  ||  |' \n" + //
                                    "_,:(_/_ \n" + ANSI_RESET);
                break;
            } 
            else if (level < 4)
                startLevel(player, inimigos);
            else if (level == maxLevel)
                break;
        }
    }

    public static void startLevel(Heroi player, Inimigo inimigos[]) {
        limparTela();
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
            limparTela();
            IO.println();
            IO.println("   ! .'´o)=- \n" + //
                                "     /.-.'   Ainda pensando em desistir?!\n" + //
                                "    //  |\\   Siga em frente!\n" + //
                                "    ||  |'  \n" + //
                                "  _,:(_/_   \n"); 
            IO.println("Digite qualquer coisa para continuar\n");
            String rand = scanner.nextLine();
        }

        List <Carta> pilha_descarte = criaCartas();
        Stack <Carta> pilha_compra = new Stack<>();
        Collections.shuffle(pilha_descarte);
        while (!pilha_descarte.isEmpty())
            pilha_compra.push(pilha_descarte.remove(0)); 
        limparTela();

        while (inimigo.estaVivo() && player.estaVivo()) {
            startRound(player, inimigo, pilha_descarte, pilha_compra);
        }
        limparTela();

        // O nível foi finalizado
        if (inimigo.estaVivo()) { // O jogador perdeu
            IO.println("\n");
            IO.println(ANSI_YELLOW + "Você perdeu...\n" + ANSI_RESET);
            IO.println();
            IO.println(inimigo.getCD());
            IO.println();
            IO.println("Deseja tentar de novo?\n");
            IO.println("1 - Sim!");
            IO.println("2 - Não...\n");
            ans = scanner.nextInt();
            if (ans == 2) {
                limparTela();
                IO.println();
                IO.println(ANSI_YELLOW + "¨_ .'´o)=-\n" + //
                                    " \\\\/.-.'\n" + //
                                    "  //  |\\     Agradecemos por jogar!\n" + //
                                    "  ||  |' \n" + //
                                    "_,:(_/_ \n" + ANSI_RESET);
                level = 10;
            }
        }
        else { // O jogador ganhou
            IO.println("\n");
            IO.println(ANSI_YELLOW + "Você ganhou!\n" + ANSI_RESET);
            IO.println();
            IO.println(inimigo.getCV());
            IO.println();
            level++;
            IO.println("Deseja continuar nessa aventura?\n");
            IO.println("1 - Sim!");
            IO.println("2 - Não...\n");
            ans = scanner.nextInt();
            if (ans == 2) {
                limparTela();
                IO.println();
                IO.println(ANSI_YELLOW + "¨_ .'´o)=-\n" + //
                                    " \\\\/.-.'\n" + //
                                    "  //  |\\     Agradecemos por jogar!\n" + //
                                    "  ||  |' \n" + //
                                    "_,:(_/_ \n" + ANSI_RESET);
                level = 10;
            }
        }
        resetLevel(player, pilha_compra, pilha_descarte);
    }

    public static void startRound(Heroi player, Inimigo inimigo, List <Carta> pilha_descarte, Stack <Carta> pilha_compra) {
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
    }

    public static void resetRound(Inimigo inimigo, Heroi player) {
        inimigo.setEscudo(0);
        player.setEscudo(0);
        player.setEnergia(100);
    }

    public static void resetLevel(Heroi player, Stack <Carta> pilha_compra, List <Carta> pilha_descarte) {
        if (getLevel() < 3) 
            player.setVida(40);
        else if (getLevel() == 10) 
            player.setVida(100);
        else
            player.setVida(60);
        player.setEscudo(0);
        player.setEnergia(100);

        while (!pilha_compra.isEmpty())
            pilha_descarte.add(pilha_compra.pop());
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

    public static int getLevel() {
        return level;
    }
}
