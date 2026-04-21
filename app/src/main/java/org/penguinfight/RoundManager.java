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

/**
 * Classe central que gerencia a dinâmica de combate, o fluxo de turnos,
 * a utilização de cartas e atua como o Publisher no padrão Observer.
 */
public class RoundManager {

    private Heroi player;
    private Batalha battle;
    private List<Observer> subscribers; // Efeitos serão os subscribers desse publisher!

    public RoundManager() {
        this.subscribers = new ArrayList<>();
    }

    public Heroi getPlayer() {
        return this.player;
    }

    public Batalha getBattle() {
        return this.battle;
    }

    public void setPlayer(Heroi player) {
        this.player = player;
    }
    
    public void setBattle(Batalha battle) {
        this.battle = battle;
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
     * e itera pelas fases do jogo até o fim da rota.
     */
    public void startGame() { 
        Scanner scanner = App.getScanner();
        App.limparTela();
        IO.println();
        IO.println("Como devemos te chamar?\n");
        String name = scanner.nextLine();
        Heroi player = new Heroi(name);
        setPlayer(player);

        List <Carta> pilhaDescarte = App.getCartas();
        Stack <Carta> pilhaCompra = new Stack<>();
        Collections.shuffle(pilhaDescarte);
        while (!pilhaDescarte.isEmpty())
            pilhaCompra.push(pilhaDescarte.remove(0));

        App.limparTela();
        IO.println();
        IO.println("    Boa sorte, " + App.ANSI_YELLOW + player.getNome() + App.ANSI_RESET + App.lerTXT("src/main/resources/Assets/boasorte.txt"));
        IO.println("Digite qualquer coisa para continuar\n");
        String rand = scanner.nextLine();

        DefaultMutableTreeNode battle = App.getMapa(); 
        
        while (true) {
            App.limparTela();
            IO.println("O destino é você quem faz. Qual caminho deseja seguir?\n");

            int ans = -1;
            while (ans < 0 || ans >= battle.getChildCount()) {
                for (int j = 0; j < battle.getChildCount(); j++) {
                    DefaultMutableTreeNode filho = (DefaultMutableTreeNode) battle.getChildAt(j);
                    Batalha batalha = (Batalha) filho.getUserObject();
                    IO.println((j) + " - " + batalha.getLocal());
                }

                IO.println("\n");
                ans = scanner.nextInt();
                if (ans >= battle.getChildCount()) 

                    IO.println("Opção inválida! Escolha um dos caminhos a seguir\n");
            }

            battle = (DefaultMutableTreeNode)battle.getChildAt(ans); // Atualiza a fase no mapa
            Batalha actualBattle = (Batalha) battle.getUserObject();
            actualBattle.setPlayer(player);
            setBattle(actualBattle);
            if (actualBattle.startBattle(pilhaDescarte, pilhaCompra)) { // O player venceu a batalha
                if (!battle.isLeaf()) {
                    IO.println("Deseja continuar nessa aventura?\n");
                    IO.println("1 - Sim!");
                    IO.println("2 - Não...\n");
                    ans = scanner.nextInt();
                    if (ans == 2) {
                        App.limparTela();
                        IO.println();
                        IO.println(App.ANSI_YELLOW + App.lerTXT("src/main/resources/Assets/agradecimento.txt") + App.ANSI_RESET);
                        return;
                    }
                }
                else {
                    App.limparTela();
                    App.getRota().gameOver(player);
                    return;
                }
            }
            else { // O player perdeu a batalha
                IO.println("Deseja tentar de novo?\n");
                IO.println("1 - Sim!");
                IO.println("2 - Não...\n");
                ans = scanner.nextInt();
                if (ans == 2) {               
                    App.limparTela();
                    IO.println();
                    IO.println(App.ANSI_YELLOW + App.lerTXT("src/main/resources/Assets/agradecimento.txt") + App.ANSI_RESET);
                    return;
                } 
                player.setVida(player.getMaxVida());
                battle = (DefaultMutableTreeNode) App.getMapa().getRoot();
            }
        }
    } 

    /**
     * Restaura a arte ASCII de batalha do inimigo correspondente ao nível atual.
     */
    public void resetCapa(Inimigo inimigo) {
        if (true) {
            inimigo.setCapa("     .'´o)=-      -=(O¬'.\n" + //
                        "     /.-.'           '._.\\\n" + //
                        "    //  |\\    VS    /| V \\\\\n" + //
                        "    ||  |'          '|   ||\n" + //
                        "  _,:(_/_            _\\ _):,_\n");
        } else if (true) {
            inimigo.setCapa("                     _T_\n" + //
                        "     .'´o)=-      -=(V¬'.\n" + //
                        "     /.-.'           '.-.\\\n" + //
                        "    //  |\\    VS    /|*V*\\\\\n" + //
                        "    ||  |'          '|*_*_||\n" + //
                        "  _,:(_/_            _\\ _):,_");
        } else if (true) {
            inimigo.setCapa("     .'´o)=- \n" + //
                        "     /.-.' \n" + //
                        "    //  |\\    VS \n" + //
                        "    ||  |'         (V) O O (V)\n" + //
                        "  _,:(_/_            `(, ,)´");
        }
    }
}
