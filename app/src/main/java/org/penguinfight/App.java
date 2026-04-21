package org.penguinfight;
import java.util.Scanner;
import javax.swing.tree.DefaultMutableTreeNode;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Cartas.CartaEscudo;
import org.penguinfight.Efeitos.EfeitoAcido;
import org.penguinfight.Efeitos.EfeitoCura;
import org.penguinfight.Efeitos.EfeitoFaixa;
import org.penguinfight.Efeitos.EfeitoNevasca;
import org.penguinfight.Efeitos.EfeitoPeixe;
import org.penguinfight.Entidades.Inimigo;
import java.util.List;
import java.util.ArrayList;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;

/**
 * Classe principal responsável pela inicialização do jogo Penguin Fight.
 * Configura o ambiente, instancia os inimigos, as cartas e gerencia o fluxo inicial.
 */
public class App {
    public static final String ANSI_RED = "\u001B[1;31m";
    public static final String ANSI_GREEN = "\u001B[1;32m";
    public static final String ANSI_YELLOW = "\033[1;33m";
    public static final String ANSI_PURPLE = "\033[1;35m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static Scanner scanner = new Scanner(System.in);
    public static RoundManager manager = new RoundManager(); // Gerenciador de batalhas
    private static DefaultMutableTreeNode mapa; // Mapa de batalhas e inimigos
    private static List<Carta> cartas; // Lista com todas as cartas do jogo
    private static Rota rota = new Rota(); // Rota do jogador

    /**
     * Ponto de entrada do programa. Apresenta o menu inicial e direciona
     * o jogador para a rota do jogo baseada em sua escolha.
     */
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
            rota.addEscolha(0);
            String rand = scanner.nextLine();
        } else  
            rota.addEscolha(1);

        setGame();
        manager.startGame();
        scanner.close();
    }

    /**
     * Instancia e configura todos os elementos do jogo (mapa, lugares, inimigos, cartas)
     */
    public static void setGame() {
        mapa = new DefaultMutableTreeNode();
        
        DefaultMutableTreeNode l11 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Puffle", 20, lerTXT("src/main/java/org/penguinfight/Assets/inimigo0_capa.txt"), lerTXT("src/main/java/org/penguinfight/Assets/inimigo0_capa_v.txt"), lerTXT("src/main/java/org/penguinfight/Assets/inimigo0_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Iglu")); // Primeira batalha (início do jogo)
        DefaultMutableTreeNode l21 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Guitarrista", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo1_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo1_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo1_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Centro"));
        DefaultMutableTreeNode l22 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Pizzaiolo", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo2_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo2_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo2_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Plaza"));
        DefaultMutableTreeNode l23 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Surfista", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo3_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo3_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo3_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Praia"));
        DefaultMutableTreeNode l31 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Gary", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo4_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo4_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo4_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Estação de Esqui"));
        DefaultMutableTreeNode l32 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Rookie", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo5_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo5_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo5_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Forte Nevado"));
        DefaultMutableTreeNode l33 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Operário", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo6_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo6_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo6_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Casinha da Mina"));
        DefaultMutableTreeNode l41 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Herbert", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo7_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo7_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo7_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Montanha"));
        DefaultMutableTreeNode l42 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Sensei", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo8_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo8_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo8_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Pátio do Dojo"));
        DefaultMutableTreeNode l43 = new DefaultMutableTreeNode(new Batalha(new Inimigo("Klutzy", 20, lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo9_capa.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo9_capa_v.txt"), lerTXT("app/src/main/java/org/penguinfight/Assets/inimigo9_capa_d.txt"), new CartaDano("A BOLA DE NEVE SUPERSÔNICA", "Carta de Ataque", 0, 10), new CartaDano("O CHUTE QUÂNTICO", "Carta de Ataque", 0, 14), new CartaEscudo("A ESQUIVA ANALÍTICA", "Carta de Defesa", 0, 4), new CartaEfeito("ÁCIDO", "Carta de Efeito", 0, new EfeitoAcido(3, 5))), "Iceberg"));

        l33.add(l43);
        l32.add(l42);
        l31.add(l41);
        l23.add(l33);
        l22.add((DefaultMutableTreeNode) l33.clone());
        l22.add(l32);
        l21.add((DefaultMutableTreeNode) l32.clone());
        l21.add(l31);
        l11.add(l23);
        l11.add(l22);
        l11.add(l21);
        mapa.add(l11);

        cartas = criaCartas();
    }

    /**
     * Instancia o baralho base do jogo contendo as cartas de dano, defesa e efeitos 
     */
    public static List <Carta> criaCartas() {
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
        cartas.add(new CartaEfeito("AUMENTAR FAIXA", "Treino moderado para aumentar habilidades (aumenta em 2 pontos sua defesa!)", 10, new EfeitoFaixa(1)));
        cartas.add(new CartaEfeito("AUMENTAR FAIXA X 2", "Treino SÉRIO para aumentar habilidades (aumenta em 4 pontos sua defesa!)", 20, new EfeitoFaixa(2)));
        cartas.add(new CartaEfeito("SARDINHA", "Aumenta em 20 pontos a energia para a próxima rodada", 20, new EfeitoPeixe(20)));
        cartas.add(new CartaEfeito("ANCHOVA", "Aumenta em 30 pontos a energia para a próxima rodada", 30, new EfeitoPeixe(30)));
        cartas.add(new CartaEfeito("NEVASCA", "Não consigo ver! Reduz o ataque do inimigo em 50%", 50, new EfeitoNevasca(1)));
        cartas.add(new CartaEfeito("KIT MÉDICO", "Que alívio! Aumenta sua vida em 10 pontos", 50, new EfeitoCura(10)));
        cartas.add(new CartaEfeito("BÁLSAMO MILAGROSO", "*Composição misteriosa* Aumenta sua vida em 20 pontos", 70, new EfeitoCura(20)));
        return cartas;
    }

    /**
     * Utilitário multiplataforma para limpar a tela do console.
     */
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

    public static RoundManager getManager() {
        return manager;
    }

    public static DefaultMutableTreeNode getMapa() {
        return mapa;
    }

    public static Rota getRota() {
        return rota;
    }

    public static List<Carta> getCartas() {
        return cartas;
    }

    public static String lerTXT(String s_path) {
        Path path = Path.of(s_path);
        try {
            byte[] bytes = Files.readAllBytes(path);
            return new String(bytes, Charset.defaultCharset());
        } catch (IOException e) {
            try {
                return Files.readString(path, Charset.forName("ISO-8859-1"));
            } catch (IOException e2) {
                return "Erro crítico: " + e2.getMessage();
            }
        }
    }
}
