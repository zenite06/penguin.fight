package org.penguinfight;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.tree.DefaultMutableTreeNode;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Classe central que gerencia a dinâmica de combate, o fluxo de turnos,
 * a utilização de cartas e atua como o Publisher no padrão Observer.
 */
public class RoundManager {

    private static int level = 0; // Level define o índice do mapa (usado para acessar o inimigo no vetor de inimigos)
    private Heroi player;
    private Inimigo inimigo;
    private List<Observer> subscribers; // Efeitos serão os subscribers desse publisher!
    private Rota rota; // Rota (define o final do jogo)
    private Mapa mapa;

    public RoundManager(Rota rota, Mapa mapa) {
        this.subscribers = new ArrayList<>();
        this.rota = rota;
        this.mapa = mapa;
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

/**

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
/**
 * do gatilho (ex: "INIMIGO VAI ATACAR").
     */
    public void notificar(String evento) {
        List<Observer> subscribers_copia = new ArrayList<>(this.subscribers);
            for (Observer subscriber : subscribers_copia) 
                subscriber.serNotificado(evento, this);
    }

    /**
     * Loop principal da campanha. Interage com o jogador para criar o herói 
     * e itera pelas fases do jogo até o fim da rota.
     */
    public void startGame(Inimigo inimigos[]) { 
        Scanner scanner = App.getScanner();
        App.limparTela();
        IO.println();
        IO.println("Como devemos te chamar?\n");
        String name = scanner.nextLine();
        Heroi player = new Heroi(name);
        setPlayer(player);

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

        DefaultMutableTreeNode fase = mapa.root;
        startLevel(player, inimigos);
        
        for (int i = 0; i < mapa.root.getDepth(); i++) {
            App.limparTela();
            IO.println("O destino é você quem faz. Qual caminho deseja seguir?\n");

            int ans = -1;
            while (ans < 0 || ans >= fase.getChildCount()) {
                for (int j = 0; j < fase.getChildCount(); j++) {
                    DefaultMutableTreeNode filho = (DefaultMutableTreeNode) fase.getChildAt(j);
                    IO.println((j + 1) + " - " + mapa.lugares.get(filho.getUserObject()));
                }

                IO.println("\n");
                ans = scanner.nextInt();
                if (ans >= fase.getChildCount()) 

                    IO.println("Opção inválida! Escolha um dos caminhos a seguir\n");
            }

            fase = (DefaultMutableTreeNode)fase.getChildAt(ans); // Atualiza a fase no mapa
            level = ans; // level agora é o índice do vetor de inimigos para o inimigo atual
            startLevel(player, inimigos);
        }

        App.limparTela();
        rota.gameOver(player);
    } 

    /**
     * Controla o fluxo de um nível: diálogo de encontro inicial, criação e 
     * embaralhamento das pilhas de cartas e o laço de repetição dos rounds.
     */
    public void startLevel(Heroi player, Inimigo inimigos[]) {
        Scanner scanner = App.getScanner();
        App.limparTela();
        IO.println();
        Inimigo inimigo = inimigos[level];
        setInimigo(inimigo);

        IO.println("Nível " + level + "\n"); // MUDAR ISSO PARA O LOCAL DO MAPA!!!!!!
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
            rota.addEscolha(0);
        } else
            rota.addEscolha(1);

        List <Carta> pilha_descarte = App.criaCartas();
        Stack <Carta> pilha_compra = new Stack<>();
        Collections.shuffle(pilha_descarte);
        while (!pilha_descarte.isEmpty())
            pilha_compra.push(pilha_descarte.remove(0)); 
        App.limparTela();

        while (inimigo.estaVivo() && player.estaVivo()) {
            startRound(player, inimigo, pilha_descarte, pilha_compra);
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
        resetLevel(player, inimigo, pilha_compra, pilha_descarte);
    }

    /**
     * Restaura os atributos base do jogador, do inimigo e do baralho, além de 
     * limpar todas as inscrições de efeitos ao término de um combate.
     */
    public void resetLevel(Heroi player, Inimigo inimigo, Stack <Carta> pilha_compra, List <Carta> pilha_descarte) {
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

        for (Efeito efeito : player.getEfeitos())
            desinscrever(efeito);
        for (Efeito efeito : inimigo.getEfeitos())
            desinscrever(efeito);
        player.getEfeitos().clear();
        inimigo.getEfeitos().clear();
        resetCapa(inimigo);
    }

    /**
     * Restaura a arte ASCII de batalha do inimigo correspondente ao nível atual.
     */
    public void resetCapa(Inimigo inimigo) {
        if (getLevel() == 0) {
            inimigo.setCapa("     .'´o)=-      -=(O¬'.\n" + //
                        "     /.-.'           '._.\\\n" + //
                        "    //  |\\    VS    /| V \\\\\n" + //
                        "    ||  |'          '|   ||\n" + //
                        "  _,:(_/_            _\\ _):,_\n");
        } else if (getLevel() == 1) {
            inimigo.setCapa("                     _T_\n" + //
                        "     .'´o)=-      -=(V¬'.\n" + //
                        "     /.-.'           '.-.\\\n" + //
                        "    //  |\\    VS    /|*V*\\\\\n" + //
                        "    ||  |'          '|*_*_||\n" + //
                        "  _,:(_/_            _\\ _):,_");
        } else if (getLevel() == 2) {
            inimigo.setCapa("     .'´o)=- \n" + //
                        "     /.-.' \n" + //
                        "    //  |\\    VS \n" + //
                        "    ||  |'         (V) O O (V)\n" + //
                        "  _,:(_/_            `(, ,)´");
        }
    }

    /**
     * Executa a lógica de um round de combate: distribuição de cartas, 
     * escolhas táticas do jogador e o processamento de ataque/efeito do inimigo.
     */
    public void startRound(Heroi player, Inimigo inimigo, List <Carta> pilha_descarte, Stack <Carta> pilha_compra) {
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
        inimigo.decidirAcao();
        int ataque = (int)(Math.random() * 2); // Define o ataque do inimigo
        while (player.estaVivo()) {
            IO.println();
            inimigo.declarar();
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
                    IO.println(i + " - Carta de Efeito: " + App.ANSI_PURPLE + nadadeira.get(i).getNome() + App.ANSI_RESET + " (" + nadadeira.get(i).getDescricao() + " / Custo = " + nadadeira.get(i).getCusto() + ")");
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
                notificar("INIMIGO VAI ATACAR");
                inimigo.atacar(player); // Manager notifica os efeitos de que o inimigo vai atacar
                inimigo.usarEfeito(this);
                notificar("INIMIGO ATACOU");
                resetRound(inimigo, player);
                break;
            }

            // O jogador escolheu uma carta
            App.limparTela();
            if (nadadeira.get(ans).getDescricao() == "Carta de Ataque")
                notificar("PLAYER VAI ATACAR"); // Manager notifica os efeitos de que o player vai atacar

            nadadeira.get(ans).usar(player, inimigo, this); 
            pilha_descarte.add(nadadeira.remove(ans)); 
            if (!inimigo.estaVivo())
                break;
        }
    }

    /**
     * Zera os escudos temporários e restaura a energia das entidades 
     * para o próximo turno.
     */
    public void resetRound(Inimigo inimigo, Heroi player) {
        inimigo.setEscudo(0);
        player.setEscudo(0);
        player.setEnergia(100);
    }

    public static int getLevel() {
        return level;
    }
}
