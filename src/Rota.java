import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class Rota {
    private Scanner scanner = App.getScanner();
    private RoundManager manager;
    private List<Integer> escolhas; // As escolhas do jogador definiram o final do jogo
    List<String> frames_rota0 = new ArrayList<>(); // Cutscenes da rota 0
    List<String> frames_rota1 = new ArrayList<>(); // Cutscenes da rota 1

    public Rota() {
        this.manager = App.getManager();
        this.escolhas = new ArrayList<Integer>();
        this.frames_rota0.add("\n\n\n    *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "     .'´o)=- \n" + //
                        "     /.-.' \n" + //
                        "    //  |\\ \n" + //
                        "    ||  |' \n" + //
                        "  _,:(_/¬");
        this.frames_rota0.add("\n\n\n    *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "         .'´o)=- \n" + //
                        "         /.-.' \n" + //
                        "        //  |\\ \n" + //
                        "        ||  |' \n" + //
                        "      _,:(¬/_");
        this.frames_rota0.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "             .'´o)=- \n" + //
                        "             /.-.' \n" + //
                        "            //  |\\ \n" + //
                        "            ||  |' \n" + //
                        "          _,:(_/¬ ");
        this.frames_rota0.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                 .'´o)=- \n" + //
                        "                 /.-.' \n" + //
                        "                //  |\\ \n" + //
                        "                ||  |' \n" + //
                        "              _,:(¬/_ ");
        this.frames_rota0.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                      .'´o)=- \n" + //
                        "                      /.-.' \n" + //
                        "                     //  |\\ \n" + //
                        "                     ||  |' \n" + //
                        "                   _,:(_/¬ ");
        this.frames_rota0.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                          .'´o)=- \n" + //
                        "                          /.-.' \n" + //
                        "                         //  |\\ \n" + //
                        "                         ||  |' \n" + //
                        "                       _,:(¬/_");
        this.frames_rota0.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                              .'´o)=- \n" + //
                        "                              /.-.' \n" + //
                        "                             //  |\\ \n" + //
                        "                             ||  |' \n" + //
                        "                           _,:(_/¬");
        this.frames_rota0.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                                  .'´o)=- \n" + //
                        "                                  /.-.' \n" + //
                        "                                 //  |\\ \n" + //
                        "                                 ||  |' \n" + //
                        "                               _,:(¬/_");
        this.frames_rota0.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                                      .'´o)=- \n" + //
                        "                                      /.-.' \n" + //
                        "                                     //  |\\ \n" + //
                        "                                     ||  |' \n" + //
                        "                                   _,:(_/¬");
        this.frames_rota0.add("\n" + //
                        "\n\n\n                                       !  .'´o)=- \n" + //
                        "                                          /.-.' \n" + //
                        "                                         //  |\\ \n" + //
                        "                                         ||  |' \n" + //
                        "                                       _,:(_/_");
        this.frames_rota0.add("\n\n\n      .'´o)=-   Notei algo sobre você...\n" + //
                        "      /.-.'\n");
        this.frames_rota0.add("\n\n\n      .'`o)=-   Tentou evitar conflitos a todo custo\n" + //
                        "      /.-.'     Fugiu de todos os combates!\n");
        this.frames_rota0.add("\n\n\n      .'´o)=-   Seria fácil te chamar de covarde\n" + //
                        "      /.-.'\n");
        this.frames_rota0.add("\n\n\n      .'´-)=-   Mas...\n" + //
                        "      /.-.'\n");
        this.frames_rota0.add("\n\n\n      .'´o)=-   Talvez você tenha razão\n" + //
                        "      /.-.'\n");
        this.frames_rota0.add("\n\n\n   ¨_ .'´o)=-   Por que lutamos até aqui?\n" + //
                        "    \\\\/.-.'     O que ganhamos com isso??\n");
        this.frames_rota0.add("\n\n\n      .'´o)=-   A verdadeira arte milenar não é sobre violência...\n" + //
                        "      /.-.'\n");
        this.frames_rota0.add("\n\n\n      .'´o)=-   É sobre *propósito*\n" + //
                        "      /.-.'\n");
        this.frames_rota0.add("\n\n\n      .'´-)=-   Talvez eu tenha te impedido de encontrar o seu...\n" + //
                        "      /.-.'\n");
        this.frames_rota0.add("\n\n\n      .'´o)=-   Mas ainda existe uma chance\n" + //
                        "      /.-.'\n");
        this.frames_rota0.add("\n\n\n      .'´o)=-   Quer tentar fazer a coisa certa?\n" + //
                        "      /.-.' \n");
    }

    public void addEscolha(int i) {
        // O valor de i depende se o jogador escolheu lutar/continuar (1) ou não (0)
        this.escolhas.add(i);
    }

    public void gameOver(Heroi player, Inimigo[] inimigos) {
        int rota = 0; // A rota é definida pelo vetor de escolhas do jogador

        for (int i = 0; i < this.escolhas.size(); i++)
            if (this.escolhas.get(i) == 1) { // O jogador não relutou em nenhuma escolha, portanto seguirá a rota padrão
                rota = 1; 
                break;
            }
        // Caso todas as escolhas tenham sido relutantes, o jogador seguirá a rota relutante (0)

        if (rota == 0) { // Rota relutante
        for (int i = 0; i < this.frames_rota0.size(); i++) { // Cutscene
            App.limparTela();
            IO.println(frames_rota0.get(i));
            try {
                if (i < 9)
                    TimeUnit.MILLISECONDS.sleep(500);
                else
                    TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        IO.println("Deseja reencontrar seus inimigos?\n");
        IO.println("1- Sim (chance de fazer a coisa certa)");
        IO.println("2 - Não...\n");
        int ans = scanner.nextInt();
        scanner.nextLine();

        if (ans == 1) {
            lutaFinal(player, inimigos);
        } else {
            IO.println(""); // Frustração do pinguim
        }
        }
    }

    public void lutaFinal(Heroi player, Inimigo[] inimigos) {

        IO.println("Nível Final\n");
        IO.println(player.getNome() + " acaba de encontrar TODOS os inimigos!");
        IO.println(""); // Capa da luta final
        IO.println("Deseja confrontá-los?\n");
        IO.println("1 - Sim!");
        IO.println("2 - Não...\n");
        int ans = scanner.nextInt();
        scanner.nextLine();

        if (ans == 2) {

        } else {

        }
    }

    public void finalRound(Heroi player, Inimigo[] inimigos_original, List <Carta> pilha_descarte, Stack <Carta> pilha_compra) {
        Scanner scanner = App.getScanner();
        List<Carta> nadadeira = new ArrayList<>();

        List<Inimigo> inimigos = new  ArrayList<Inimigo>(); // Lista com os inimigos vivos
        System.arraycopy(inimigos_original, 0, inimigos, 0, inimigos_original.length);
        
        for (int i = 0; i < 6; i++) {
            if (pilha_compra.isEmpty()) { // Se a pilha de compra estiver vazia, as cartas da pilha de descarte são embaralhadas e passam pro topo da pilha de compra
                Collections.shuffle(pilha_descarte);
                while (!pilha_descarte.isEmpty())
                    pilha_compra.push(pilha_descarte.remove(0)); 
            } 
            nadadeira.add(pilha_compra.pop()); // São compradas cinco cartas da pilha de compra para a mão do jogador (nadadeira)
        }

        int ataque = (int)(Math.random() * 2); // Define o ataque do inimigo
        boolean game = true;
        while (player.estaVivo() && game) {
            IO.println();
            for (int i = 0; i < inimigos.size(); i++) {
                inimigos.get(i).declarar(ataque, player);
                IO.println(App.ANSI_YELLOW + "                  " + inimigos.get(i).getNome() + " (Vida = " + inimigos.get(i).getVida() + ")\n" + App.ANSI_RESET);
            }
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
                manager.notificar("INIMIGOS VÃO ATACAR");
                for (i = 0; i < inimigos.size(); i++)
                    inimigos.get(i).atacar(player); // Manager notifica os efeitos de que o inimigo vai atacar
                resetRound(inimigos, player);
                break;
            }

            // O jogador escolheu uma carta
            App.limparTela();
            if (nadadeira.get(ans).getDescricao() == "Carta de Ataque")
                manager.notificar("PLAYER VAI ATACAR"); // Manager notifica os efeitos de que o player vai atacar

            while (true) {
                App.limparTela();
                IO.println("Qual inimigo deseja atacar?\n");
                for (i = 0; i < inimigos.size(); i++); 
                    IO.println(i + " - " + inimigos.get(i).getNome());

                ans = scanner.nextInt();
                scanner.nextLine();

                if (ans >= inimigos.size() || ans < 0) { // O jogador escolheu uma opção inválida
                    App.limparTela();
                    IO.println(App.ANSI_YELLOW + "Ei, não tente fugir dessa! Escolha uma das opções disponíveis\n" + App.ANSI_RESET);
                    break;
                }
            }

            nadadeira.get(ans).usar(player, inimigos.get(ans), manager); 
            pilha_descarte.add(nadadeira.remove(ans)); 

            for (i = 0; i < inimigos.size(); i++) {
                if (!inimigos.get(i).estaVivo()) 
                    inimigos.remove(i);
            }

            if (inimigos.size() < 1)
                game = false;
        }
    }

    public void resetRound(List<Inimigo> inimigos, Heroi player) {
        for (int i = 0; i < inimigos.size(); i++)
            inimigos.get(i).setEscudo(0);
        player.setEscudo(0);
        player.setEnergia(100);
    }
}



