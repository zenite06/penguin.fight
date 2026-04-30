package org.penguinfight;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.tree.DefaultMutableTreeNode;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;
import org.penguinfight.Eventos.Evento;

/**
 * Classe central que gerencia o percurso de batalhas. Possui a implementação dos métodos de Observer,
 * criação do player e navegação na árvore de batalhas. 
 */
public class RoundManager {

    private static RoundManager instance;
    private static Heroi player;
    private Evento evento;
    private List<Observer> subscribers; // Efeitos serão os subscribers desse publisher!

    private RoundManager() {
        this.subscribers = new ArrayList<>();
    }

    public static RoundManager getInstance() {
        if ( instance == null) {
            instance = new RoundManager();
        }
        return instance;
    }

    public Evento getEvento() {
        return this.evento;
    }
    
    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    /* Métodos de Publisher */

    /**
     * Inscreve um novo Observer (geralmente um Efeito) para escutar os eventos do combate.
     */
    public void inscrever(Observer subscriber) {
        subscribers.add(subscriber);
    }

    public void desinscrever(Observer subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Notifica todos os efeitos inscritos sobre um gatilho ocorrido no combate.
     * @param evento Identificador
     * do gatilho (ex: "INIMIGO VAI ATACAR").
     */
    public void notificar(String evento) {
        List<Observer> subscribers_copia = new ArrayList<>(this.subscribers);
            for (Observer subscriber : subscribers_copia) 
                subscriber.serNotificado(evento);
    }

    /**
     * Loop principal da campanha. Interage com o jogador para criar o herói 
     * e navega pela árvore de batalhas do jogo até o fim da rota.
     */
    public void startGame() {
        List <Carta> pilhaDescarte = App.getCartas();
        Stack <Carta> pilhaCompra = new Stack<>();
        Collections.shuffle(pilhaDescarte);
        while (!pilhaDescarte.isEmpty())
            pilhaCompra.push(pilhaDescarte.remove(0));
        
        Scanner scanner = App.getScanner();
        App.limparTela();
        IO.println();
        IO.println("Como devemos te chamar?\n");
        String name = scanner.nextLine();
        player = Heroi.getInstance(name, 40, pilhaDescarte, pilhaCompra);

        App.limparTela();
        IO.println();
        IO.println("    Boa sorte, " + App.ANSI_YELLOW + player.getNome() + App.ANSI_RESET + App.lerTXT("src/main/resources/Assets/boasorte.txt"));
        IO.println("Digite qualquer coisa para continuar\n");
        String rand = scanner.nextLine();

        DefaultMutableTreeNode noAtual = App.getMapa(); 
        
        while (true) {
            App.limparTela();
            IO.println("O destino é você quem faz. Qual caminho deseja seguir?\n");

            int ans = -1;
            while (ans < 0 || ans >= noAtual.getChildCount()) {
                for (int j = 0; j < noAtual.getChildCount(); j++) {
                    DefaultMutableTreeNode noFilho = (DefaultMutableTreeNode) noAtual.getChildAt(j);
                    Evento evento = (Evento) noFilho.getUserObject();
                    IO.println((j) + " - " + evento.getLocal());
                }

                IO.println("\n");
                ans = scanner.nextInt();
                if (ans >= noAtual.getChildCount()) 
                    IO.println("Opção inválida! Escolha um dos caminhos a seguir\n");
            }

            DefaultMutableTreeNode no = (DefaultMutableTreeNode) noAtual.getChildAt(ans);
            Evento evento = (Evento) no.getUserObject(); // Atualiza a fase no mapa
            noAtual = no;
            setEvento(evento);
            if (evento.iniciar()) { // O player venceu a batalha
                if (!no.isLeaf()) {
                    IO.println("Deseja continuar nessa aventura?\n");
                    IO.println("1 - Sim!");
                    IO.println("2 - Não...\n");
                    ans = scanner.nextInt();
                    if (ans == 2) {
                        App.run(); // TROCAR
                        return;
                    }
                }
                else {
                    App.limparTela();
                    App.getRota().gameOver(player);
                    // Provavelmente vai ter que resetar o jogo (para resetar os produtos nas lojas, por exemplo)
                    return;
                }
            }
            else { // O player perdeu a batalha
                IO.println("Deseja tentar de novo?\n");
                IO.println("1 - Sim!");
                IO.println("2 - Não...\n");
                ans = scanner.nextInt();
                if (ans == 2) {               
                    App.run(); // TROCAR
                    return;
                } 
                player.setVida(player.getMaxVida());
                noAtual = App.criaMapa();
                List <Carta> newPilhaDescarte = App.getCartas();
                Stack <Carta> newPilhaCompra = new Stack<>();
                Collections.shuffle(newPilhaDescarte);
                while (!newPilhaDescarte.isEmpty())
                    newPilhaCompra.push(newPilhaDescarte.remove(0));
                player.setPilhaDescarte(newPilhaDescarte);
                player.setPilhaCompra(newPilhaCompra);
            }
        }
    }

    /**
     * Restaura a arte ASCII de batalha do inimigo correspondente à batalha atual.
     */
    public void resetCapa(Inimigo inimigo) {
        if (getEvento().getLocal() == "Iglu") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo0_capa.txt"));
        } else if (getEvento().getLocal() == "Centro") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo1_capa.txt"));
        } else if (getEvento().getLocal() == "Plaza") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo2_capa.txt"));
        } else if (getEvento().getLocal() == "Praia") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo3_capa.txt"));
        } else if (getEvento().getLocal() == "Estação de Esqui") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo4_capa.txt"));
        } else if (getEvento().getLocal() == "Forte Nevado") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo5_capa.txt"));
        } else if (getEvento().getLocal() == "Casinha da Mina") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo6_capa.txt"));
        } else if (getEvento().getLocal() == "Montanha") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo7_capa.txt"));
        } else if (getEvento().getLocal() == "Pátio do Dojo") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo8_capa.txt"));
        } else if (getEvento().getLocal() == "Iceberg") {
            inimigo.setCapa(App.lerTXT("src/main/resources/Assets/inimigo9_capa.txt"));
        }
    }
}
