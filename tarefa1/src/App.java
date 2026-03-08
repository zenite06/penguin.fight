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
        startGame(inimigos);
        scanner.close();
    }

    public static Inimigo[] criaInimigos() {
        // Nessa "função" serão instanciados todos os inimigos do jogo
        Inimigo inimigos[] = new Inimigo[maxLevel];
        inimigos[0] = new Inimigo("Gary", 40, 2, "     .'´o)=-      -=(O¬'.\n" + //
                        "     /.-.'           '._.\\\n" + //
                        "    //  |\\    VS    /| V \\\\\n" + //
                        "    ||  |'          '|   ||\n" + //
                        "  _,:(_/_            _\\ _):,_\n"); 
        // Inimigo da primeira fase
        return inimigos;
    }

    public static void startGame(Inimigo inimigos[]) { 
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
            startLevel(player, inimigos);
        }

        scanner.close();
    }

    public static void startLevel(Heroi player, Inimigo inimigos[]) {
        Scanner scanner = new Scanner(System.in);

        IO.println("Nível " + level + "\n");
        IO.println(player.getName() + " acaba de encontrar " + inimigos[level - 1].getName() + "\n");
        IO.println(inimigos[level - 1].getC());
        IO.println("Deseja confrontá-lo?\n");
        IO.println("1 Sim!");
        IO.println("2 Não...\n"); 

        int ans = scanner.nextInt();
        IO.println("\n");
        level++;
        scanner.close();
    }

    public static int getLevel() {
        return level;
    }
}
