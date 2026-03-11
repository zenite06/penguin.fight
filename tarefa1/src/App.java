import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    private static int level = 1; // O nível define alguns atributos da gameplay
    private static int maxLevel = 10; // Quantos níveis (e inimigos) o jogo terá
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
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
        IO.println("\n");

        if (answer != 1)  
            IO.println("  -=(o`'. ?!\r\n" + //
                                "    '.-.\\ \r\n" + //
                                "    /|  \\\\     Tarde demais para desistir agora. Aguente firme!\r\n" + //
                                "    '|  || \r\n" + //
                                "     _\\_):,_\n");

        Inimigo inimigos[] = criaInimigos(); // Deverão ser passados às funções subsequentes!
        CartaEscudo escudos[] = criaCartasEscudo();
        CartaDano danos[] = criaCartasDano();
        startGame(inimigos, danos, escudos);
        scanner.close();
    }

    public static Inimigo[] criaInimigos() {
        // Nessa "função" serão instanciados todos os inimigos do jogo
        Inimigo inimigos[] = new Inimigo[maxLevel];
        inimigos[0] = new Inimigo("Gary", 40, 2, 3, "     .'´o)=-      -=(O¬'.\n" + //
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

    public static CartaDano[] criaCartasDano() {
        CartaDano danos[] = new CartaDano[5];
        danos[0] = new CartaDano("a cotovelada improvisada", 10, 2);
        danos[1] = new CartaDano("o soco voador", 20, 5);
        danos[2] = new CartaDano("o chute periculoso", 40, 10);
        danos[3] = new CartaDano("a joelhada triunfal", 70, 20);
        danos[4] = new CartaDano("a imobilização fatal", 90, 30);
        return danos;
    }

    public static CartaEscudo[] criaCartasEscudo() {
        CartaEscudo escudos[] = new CartaEscudo[5];
        escudos[0] = new CartaEscudo("a esquiva desajeitada", 2, 10);
        escudos[1] = new CartaEscudo("a esquiva normal", 4, 30);
        escudos[2] = new CartaEscudo("a esquiva perfeita", 8, 50);
        escudos[3] = new CartaEscudo("o bloqueio brutal", 6, 70);
        escudos[4] = new CartaEscudo("o bloqueio milenar", 10, 90);
        return escudos;
    }

    public static void startGame(Inimigo inimigos[], CartaDano danos[], CartaEscudo escudos[]) { 
        Scanner scanner = new Scanner(System.in);

        IO.println("Como devemos te chamar?\n");
        String name = scanner.nextLine();
        IO.println("\n");
        Heroi player = new Heroi(name);

        IO.println("    Boa sorte, " + player.getName() + "!\n" + //
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
                IO.println("¨_ .'´o)=-\n" + //
                                    " \\\\/.-.'     Agradecemos por jogar!\n" + //
                                    "  //  |\\     Os próximos níveis ainda estão em desenvolvimento, aguarde :)\n" + //
                                    "  ||  |' \n" + //
                                    "_,:(_/_ \n");
                break;
            } 
            startLevel(player, inimigos, danos, escudos);
        }

        scanner.close();
    }

    public static void startLevel(Heroi player, Inimigo inimigos[], CartaDano danos[], CartaEscudo escudos[]) {
        Scanner scanner = new Scanner(System.in);

        int[] cartas = new int[4]; // Vetor com os índices das cartas (as duas primeiras são de dano e as duas últimas de escudo)

        /* O processo abaixo aleatoriza as cartas a cada nível */
        int last = -1;
        int j = -1;
        for(int i = 0; i < 1; i++) {
            while(j == last)
                j = (int)(Math.random() * 4);
            cartas[i] = j;
            last = j;
        }

        last = -1;
        j = -1;
        for(int i = 2; i < 4; i++) {
            while(j == last)
                j = (int)(Math.random() * 4);
            cartas[i] = j;
            last = j;
        }

        IO.println("Nível " + level + "\n");
        IO.println(player.getName() + " acaba de encontrar " + inimigos[level - 1].getName() + "\n");
        IO.println(inimigos[level - 1].getC());
        IO.println("Deseja confrontá-lo?\n");
        IO.println("1 Sim!");
        IO.println("2 Não...\n"); // Fazer switch para englobar a resposta negativa
        int ans = scanner.nextInt();

        while (ans != 5 && player.estaVivo() == true && inimigos[level - 1].estaVivo() == true) {
            IO.println();
            IO.println(player.getName() + " (Vida = " + player.getVida() + ")");
            IO.println(inimigos[level - 1].getName() + " (Vida = " + inimigos[level - 1].getVida() + ")\n");
            IO.println("Como quer se preparar?\n");
            IO.println("Energia: " + player.getEnergia());
            IO.println();

            if (cartas[0] >= 0)
                IO.println("1 - Carta de dano: " + danos[cartas[0]].getName() + " (Dano = " + danos[cartas[0]].getDano() + " / Custo = " + danos[cartas[0]].getCusto() + ")");
            if (cartas[1] >= 0)
                IO.println("2 - Carta de dano: " + danos[cartas[1]].getName() + " (Dano = " + danos[cartas[1]].getDano() + " / Custo = " + danos[cartas[1]].getCusto() + ")");
            if (cartas[2] >= 0)
                IO.println("3 - Carta de defesa: " + escudos[cartas[2]].getName() + " (Defesa = " + escudos[cartas[2]].getEscudo() + " / Custo = " + escudos[cartas[2]].getCusto() + ")");
            if (cartas[3] >= 0)
                IO.println("4 - Carta de defesa: " + escudos[cartas[3]].getName() + " (Defesa = " + escudos[cartas[3]].getEscudo() + " / Custo = " + escudos[cartas[3]].getCusto() + ")");
            IO.println("5 - Finalizar turno\n");

            try { // Isso não está funcionando como deveria!
                ans = scanner.nextInt();
                IO.println();
            } catch (InputMismatchException e) {
                ans = 6;
            }

            switch(ans) {
                case 1:
                    danos[cartas[0]].usar(player, inimigos[level - 1]);
                    cartas[0] = -1;
                    break;

                case 2:
                    danos[cartas[1]].usar(player, inimigos[level - 1]);
                    cartas[1] = -1;
                    break;

                case 3:
                    escudos[cartas[2]].usar(player, inimigos[level - 1]);
                    cartas[2] = -1;
                    break;

                case 4:
                    escudos[cartas[3]].usar(player, inimigos[level - 1]);
                    cartas[3] = -1;
                    break;

                case 5:
                    // Finalização da luta
                    IO.println(inimigos[level - 1].getC());
                    IO.println(inimigos[level - 1].getCV());
                    IO.println(inimigos[level - 1].getCD());
                    break;

                default:
                    IO.println("Ei, não tente fugir dessa! Escolha uma das opções disponíveis\n");
                    break;
            }
            player.receberDano(inimigos[level-1].getDano());
            player.resetRound();
        }

        IO.println("\n");
        level++;
        scanner.close();
    }

    public static int getLevel() {
        return level;
    }
}
