import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class App {
    static Scanner scanner = new Scanner(System.in);

    public static RoundManager manager;
    public static final int maxLevel = 10; // Quantos níveis (e inimigos) o jogo terá
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

        Rota rota = new Rota(); // Rota (define o final do jogo)
        if (answer != 1) {
            limparTela();
            IO.println();
            IO.println("  -=(o`'. ?!\r\n" + //
                                "    '.-.\\ \r\n" + //
                                "    /|  \\\\     Tarde demais para desistir agora. Aguente firme!\r\n" + //
                                "    '|  || \r\n" + //
                                "     _\\_):,_\n");
            IO.println("Digite qualquer coisa para continuar\n");
            rota.addEscolha(0);
            String rand = scanner.nextLine();
        } else  
            rota.addEscolha(1);

        Inimigo inimigos[] = criaInimigos(); // Deverão ser passados às funções subsequentes!
        manager = new RoundManager(rota);
        manager.startGame(inimigos);
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
                                                                                                "  _,:(_/_            _\\ _):,_", new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 15), new CartaEfeito("???", "???", 0, 1, new EfeitoAcido(1))); 
        
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
                                                                                                                        "  _,:(_/_            _\\ _):,_", new CartaDano("A VOADORA ESTILOSA", "Carta de Ataque", 0, 15), new CartaDano("A NADADEIRA SÔNICA", "Carta de Ataque", 0, 16), new CartaEfeito("???", "???", 0, 1, new EfeitoAcido(1))); 
        
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
                                                                                                                        "  _,:(_/_            `(, ,)´", new CartaDano("O CORTE AFIADO", "Carta de Ataque", 0, 15), new CartaDano("O BELISCÃO DE AÇO", "Carta de Ataque", 0, 20), new CartaEfeito("???", "???", 0, 1, new EfeitoAcido(1))); 
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
        cartas.add(new CartaEfeito("AUMENTAR FAIXA", "Carta de Efeito", 10, 1, new EfeitoFaixa(1)));
        cartas.add(new CartaEfeito("AUMENTAR FAIXA X 2", "Carta de Efeito", 20, 2, new EfeitoFaixa(2)));
        cartas.add(new CartaEfeito("SARDINHA", "Aumenta em 20 pontos a energia para a próxima rodada", 20, 20, new EfeitoPeixe(20)));
        cartas.add(new CartaEfeito("ANCHOVA", "Aumenta em 30 pontos a energia para a próxima rodada", 30, 30, new EfeitoPeixe(30)));
        cartas.add(new CartaEfeito("NEVASCA", "Não consigo ver! Reduz o ataque do inimigo em 50%", 50, 1, new EfeitoNevasca()));
        return cartas;
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

    public static Scanner getScanner() {
        return scanner;
    }

    public static int getmaxLevel() {
        return maxLevel;
    }

    public static RoundManager getManager() {
        return manager;
    }
}
