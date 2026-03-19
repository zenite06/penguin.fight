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
        IO.println("  ____                        _          __ _       _     _    __             __\n" + //
                        " |  _ \\ ___ _ __   __ _ _   _(_)_ __    / _(_) __ _| |__ | |_ / /  -=(o`'.    \\ \\ \n" + //
                        " | |_) / _ \\ '_ \\ / _` | | | | | '_ \\  | |_| |/ _` | '_ \\| __| |     '.-.\\     | |\n" + //
                        " |  __/  __/ | | | (_| | |_| | | | | |_|  _| | (_| | | | | |_| |     /|  \\\\    | |\n" + //
                        " |_|   \\___|_| |_|\\__, |\\__,_|_|_| |_(_)_| |_|\\__, |_| |_|\\__| |     '|  ||    | |\n" + //
                        "                  |___/                       |___/           \\_\\     _\\_):,_ /_/ \n");
        
        IO.println("Saudações, pinguim! A ilha está em apuros e precisa da sua ajuda, só lhe resta lutar para encontrar o verdadeiro inimigo. Aceita essa aventura?\n");  
        IO.println("1 Sim!");
        IO.println("2 Não...\n");

        int answer = scanner.nextInt();
        scanner.nextLine();
        IO.println("\n");

        if (answer != 1) {
            IO.println("  -=(o`'. ?!\r\n" + //
                                "    '.-.\\ \r\n" + //
                                "    /|  \\\\     Tarde demais para desistir agora. Aguente firme!\r\n" + //
                                "    '|  || \r\n" + //
                                "     _\\_):,_\n");
            IO.println("Digite qualquer coisa para continuar");
            String rand = scanner.nextLine();
            IO.println("\n");
        }
        Inimigo inimigos[] = criaInimigos(); // Deverão ser passados às funções subsequentes!
        List <CartaEscudo> escudos = criaCartasEscudo();
        List <CartaDano> danos = criaCartasDano();
        startGame(inimigos, danos, escudos);
        scanner.close();
    }

    public static Inimigo[] criaInimigos() {
        // Nesse método serão instanciados todos os inimigos do jogo
        Inimigo inimigos[] = new Inimigo[maxLevel];
        inimigos[0] = new Inimigo("Gary", 40, 5, 15, "     .'´o)=-      -=(O¬'.\n" + //
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
                                                                                                "  _,:(_/_            _\\ _):,_"); 
        // Inimigo da primeira fase
        return inimigos;
    }

    public static List <CartaDano> criaCartasDano() {
        List<CartaDano> danos = new ArrayList<>();
        danos.add(0, new CartaDano("A COTOVELADA IMPROVISADA", "Carta de Ataque", 10, 2));
        danos.add(1, new CartaDano("O SOCO VOADOR", "Carta de Ataque", 20, 5));
        danos.add(2, new CartaDano("O CHUTE PERICULOSO", "Carta de Ataque", 40, 10));
        danos.add(3, new CartaDano("A JOELHADA TRIUNFAL", "Carta de Ataque", 70, 20));
        danos.add(4, new CartaDano("A IMOBILIZAÇÃO FATAL", "Carta de Ataque", 90, 30));
        return danos;
    }

    public static List <CartaEscudo> criaCartasEscudo() {
        List <CartaEscudo> escudos = new ArrayList<>();
        escudos.add(0, new CartaEscudo("A ESQUIVA DESAJEITADA", "Carta de Defesa", 2, 10));
        escudos.add(1, new CartaEscudo("A ESQUIVA NORMAL", "Carta de Defesa", 4, 30));
        escudos.add(2, new CartaEscudo("A ESQUIVA PERFEITA", "Carta de Defesa", 8, 50));
        escudos.add(3, new CartaEscudo("O BLOQUEIO BRUTAL", "Carta de Defesa", 10, 70));
        escudos.add(4, new CartaEscudo("O BLOQUEIO MILENAR", "Carta de Defesa", 15, 90));
        return escudos;
    }

    public static void startGame(Inimigo inimigos[], List <CartaDano> danos, List <CartaEscudo> escudos) { 

        limparTela();
        IO.println();
        IO.println("Como devemos te chamar?\n");
        String name = scanner.nextLine();
        IO.println("\n");
        Heroi player = new Heroi(name);

        IO.println("    Boa sorte, " + ANSI_YELLOW + player.getNome() + ANSI_RESET + "!\n" + //
                                                        "                                    -=(o`'. _¬\r\n" + //
                                                        "    Lembre-se: para vencer, use       '.-.\\// \r\n" + //
                                                        "    as cartas ao seu favor            /|  \\\\ \r\n" + //
                                                        "                                      '|  || \r\n" + //
                                                        "                                       _\\_):,_\n");
        IO.println("Digite qualquer coisa para continuar");
        String rand = scanner.nextLine();
        IO.println("\n");
        
        for (int i = 1; i <= maxLevel; i++) {
            if (level > 1) { // Os próximos níveis serão implementados futuramente
                IO.println(ANSI_YELLOW + "¨_ .'´o)=-\n" + //
                                    " \\\\/.-.'     Agradecemos por jogar!\n" + //
                                    "  //  |\\     Os próximos níveis ainda estão em desenvolvimento, aguarde :)\n" + //
                                    "  ||  |' \n" + //
                                    "_,:(_/_ \n" + ANSI_RESET);
                break;
            } 
            startLevel(player, inimigos, danos, escudos);
        }

    }

    public static void startLevel(Heroi player, Inimigo inimigos[], List <CartaDano> danos, List <CartaEscudo> escudos) {

        limparTela();
        Inimigo inimigo = inimigos[level - 1];

        IO.println();
        IO.println("Nível " + level + "\n");
        IO.println(player.getNome() + " acaba de encontrar " + inimigo.getNome() + "\n");
        IO.println(inimigo.getC());
        IO.println("Deseja confrontá-lo?\n");
        IO.println("1 Sim!");
        IO.println("2 Não...\n");
        int ans = scanner.nextInt();
        scanner.nextLine();
        if (ans == 2) {
            IO.println("   ! .'´o)=- \n" + //
                                "     /.-.'   Ainda pensando em desistir?!\n" + //
                                "    //  |\\   Siga em frente!\n" + //
                                "    ||  |'  \n" + //
                                "  _,:(_/_   \n"); 
            IO.println("Digite qualquer coisa para continuar");
            String rand = scanner.nextLine();
            IO.println("\n");
        }

        limparTela();
        while (inimigo.estaVivo() && player.estaVivo()) {
            startRound(player, inimigo, danos, escudos);
        }

        limparTela();
        if (inimigo.estaVivo()) { // Você perdeu
            IO.println("\n");
            IO.println(ANSI_YELLOW + "Você perdeu...\n" + ANSI_RESET);
            IO.println();
            IO.println(inimigo.getCD());
            IO.println();
            IO.println("Deseja tentar de novo?");
            IO.println("1 Sim!");
            IO.println("2 Não...\n");
            IO.println();
            ans = scanner.nextInt();
            if (ans == 2)
                level++;
        }
        else { // Você ganhou
            IO.println("\n");
            IO.println(ANSI_YELLOW + "Você ganhou!\n" + ANSI_RESET);
            IO.println();
            IO.println(inimigo.getCV());
            IO.println();
            level ++;
        }
        resetLevel(player);
    }

    public static void startRound(Heroi player, Inimigo inimigo, List <CartaDano> danos, List <CartaEscudo> escudos) {

        List<Integer> cartas = new ArrayList<>();
        int[] nadadeira = new int[4]; // Vetor com os índices das cartas na "mão" do jogador (as duas primeiras são de dano e as duas últimas de escudo)
        Stack<Integer> pilha_compra = new Stack<>();
        Stack<Integer> pilha_descarte = new Stack<>();

        for (int i = 0; i < 5; i++) 
            cartas.add(i, i);
        Collections.shuffle(cartas);

        for (Integer item : cartas) {
            pilha_compra.push(item);
        }

        for (int i = 0; i < 5; i++) 
            nadadeira[i] = pilha_compra.pop();

        /* O processo abaixo aleatoriza as cartas a cada rodada */
        int last = -1;
        int j = -1;
        for(int i = 0; i < 1; i++) {
            while(j == last)
                j = (int)(Math.random() * 5);
            nadadeira[i] = j;
            last = j;
        }

        last = -1;
        j = -1;
        for(int i = 2; i < 4; i++) {
            while(j == last)
                j = (int)(Math.random() * 5);
            nadadeira[i] = j;
            last = j;
        }

        round:
        while (player.estaVivo()) {
            IO.println();
            IO.println(ANSI_YELLOW + "                  " + inimigo.getNome() + " (Vida = " + inimigo.getVida() + ")\n" + ANSI_RESET);
            IO.println(inimigo.getC());
            IO.println(ANSI_YELLOW + "  " + player.getNome() + " (Vida = " + player.getVida() + " / Defesa = " + player.getEscudo() + ")\n" + ANSI_RESET);
            IO.println("Nas suas nadadeiras existem 4 cartas\nDeseja usá-las?\n");
            IO.println("Energia: " + player.getEnergia());
            IO.println();

            IO.println("1 - Carta de dano: " + ANSI_PURPLE + danos.get(nadadeira[0]).getNome() + ANSI_RESET + " (Dano = " + danos.get(nadadeira[0]).getDano() + " / Custo = " + danos.get(nadadeira[0]).getCusto() + ")");
            IO.println("2 - Carta de dano: " + ANSI_PURPLE + danos.get(nadadeira[1]).getNome() + ANSI_RESET + " (Dano = " + danos.get(nadadeira[1]).getDano() + " / Custo = " + danos.get(nadadeira[1]).getCusto() + ")");
            IO.println("3 - Carta de defesa: " + ANSI_PURPLE + escudos.get(nadadeira[2]).getNome() + ANSI_RESET + " (Defesa = " + escudos.get(nadadeira[2]).getEscudo() + " / Custo = " + escudos.get(nadadeira[2]).getCusto() + ")");
            IO.println("4 - Carta de defesa: " + ANSI_PURPLE + escudos.get(nadadeira[3]).getNome() + ANSI_RESET + " (Defesa = " + escudos.get(nadadeira[3]).getEscudo() + " / Custo = " + escudos.get(nadadeira[3]).getCusto() + ")");
            IO.println("5 - Finalizar turno\n");

            int ans = scanner.nextInt();
            scanner.nextLine();
            if (ans == 5)
                break;
            if ((ans < 1 || ans > 4) && ans != 5)
                ans = 6;
            else if (ans != 5 && nadadeira[ans - 1] < 0)
                ans = 6;

            switch(ans) {
                case 1:
                    limparTela();
                    danos.get(nadadeira[0]).usar(player, inimigo);
                    if (! inimigo.estaVivo())
                        break round;
                    nadadeira[0] = -1;
                    break;

                case 2:
                    limparTela();
                    danos.get(nadadeira[1]).usar(player, inimigo);
                    if (! inimigo.estaVivo())
                        break round;
                    nadadeira[1] = -1;
                    break;

                case 3:
                    limparTela();
                    escudos.get(nadadeira[2]).usar(player, inimigo);
                    nadadeira[2] = -1;
                    break;

                case 4:
                    limparTela();
                    escudos.get(nadadeira[3]).usar(player, inimigo);
                    nadadeira[3] = -1;
                    break;

                case 5:
                    // Finalização da luta
                    break round;

                default:
                    limparTela();
                    IO.println(ANSI_YELLOW + "Ei, não tente fugir dessa! Escolha uma das opções disponíveis\n" + ANSI_RESET);
                    break;
            }
        }
        limparTela();
        IO.println("\n");
        player.receberDano(inimigo.getDano());
        resetRound(inimigo, player);
        IO.println();
    }

    public static void resetRound(Inimigo inimigo, Heroi player) {
        inimigo.setEscudo(0);
        player.setEscudo(0);
        player.setEnergia(100);
    }

    public static void resetLevel(Heroi player) {
        if (getLevel() < 3) 
            player.setVida(40);
        else if (getLevel() == 10) 
            player.setVida(100);
        else
            player.setVida(60);
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

    public static int getLevel() {
        return level;
    }
}
