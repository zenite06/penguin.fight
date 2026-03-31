import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Rota {
    private Scanner scanner = App.getScanner();
    private RoundManager manager;
    private List<Integer> escolhas; // As escolhas do jogador definiram o final do jogo
    List<String> frames = new ArrayList<>(); // Cutscenes da rota 0
    List<String> frames_rota1 = new ArrayList<>(); // Cutscenes da rota 1

    public Rota() {
        this.manager = App.getManager();
        this.escolhas = new ArrayList<Integer>();
        this.frames.add("\n\n\n    *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "     .'´o)=- \n" + //
                        "     /.-.' \n" + //
                        "    //  |\\ \n" + //
                        "    ||  |' \n" + //
                        "  _,:(_/¬");
        this.frames.add("\n\n\n    *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "         .'´o)=- \n" + //
                        "         /.-.' \n" + //
                        "        //  |\\ \n" + //
                        "        ||  |' \n" + //
                        "      _,:(¬/_");
        this.frames.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "             .'´o)=- \n" + //
                        "             /.-.' \n" + //
                        "            //  |\\ \n" + //
                        "            ||  |' \n" + //
                        "          _,:(_/¬ ");
        this.frames.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                 .'´o)=- \n" + //
                        "                 /.-.' \n" + //
                        "                //  |\\ \n" + //
                        "                ||  |' \n" + //
                        "              _,:(¬/_ ");
        this.frames.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                      .'´o)=- \n" + //
                        "                      /.-.' \n" + //
                        "                     //  |\\ \n" + //
                        "                     ||  |' \n" + //
                        "                   _,:(_/¬ ");
        this.frames.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                          .'´o)=- \n" + //
                        "                          /.-.' \n" + //
                        "                         //  |\\ \n" + //
                        "                         ||  |' \n" + //
                        "                       _,:(¬/_");
        this.frames.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                              .'´o)=- \n" + //
                        "                              /.-.' \n" + //
                        "                             //  |\\ \n" + //
                        "                             ||  |' \n" + //
                        "                           _,:(_/¬");
        this.frames.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                                  .'´o)=- \n" + //
                        "                                  /.-.' \n" + //
                        "                                 //  |\\ \n" + //
                        "                                 ||  |' \n" + //
                        "                               _,:(¬/_");
        this.frames.add("\n\n\n     *Barulhos de pinguim andando*\n" + //
                        "\n" + //
                        "                                      .'´o)=- \n" + //
                        "                                      /.-.' \n" + //
                        "                                     //  |\\ \n" + //
                        "                                     ||  |' \n" + //
                        "                                   _,:(_/¬");
        this.frames.add("\n" + //
                        "\n\n\n                                       !  .'´o)=- \n" + //
                        "                                          /.-.' \n" + //
                        "                                         //  |\\ \n" + //
                        "                                         ||  |' \n" + //
                        "                                       _,:(_/_");
        this.frames.add("\n\n\n      .'´o)=-   Notei algo sobre você...\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'`o)=-   Tentou evitar conflitos a todo custo\n" + //
                        "      /.-.'     Fugiu de todos os combates!\n");
        this.frames.add("\n\n\n      .'´o)=-   Seria fácil te chamar de covarde\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´-)=-   Mas...\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´o)=-   Talvez você tenha razão\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n   ¨_ .'´o)=-   Por que lutamos até aqui?\n" + //
                        "    \\\\/.-.'     O que ganhamos com isso??\n");
        this.frames.add("\n\n\n      .'´o)=-   A verdadeira arte milenar não é sobre violência...\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´o)=-   É sobre *propósito*\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´-)=-   Talvez eu tenha te impedido de encontrar o seu...\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´o)=-   Mas ainda existe uma chance\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´o)=-   Quer tentar fazer a coisa certa?\n" + //
                        "      /.-.' \n"); // fim da rota 0
        this.frames.add("\n\n\n      .'´o)=-   Você finalmente conseguiu!\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´^)=-   A ilha finalmente está segura, graças aos seus esforços\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´o)=-   Em nome de toda a ilha, agradeço por nunca ter desistido\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n      .'´o)=-   Boa sorte em sua jornada a partir daqui\n" + //
                        "      /.-.'\n");
        this.frames.add("\n\n\n   ¨_ .'´^)=-   Até mais!\n" + //
                        "    \\\\/.-.'\n"); // fim da rota 1
    }

    public void addEscolha(int i) {
        // O valor de i depende se o jogador escolheu lutar/continuar (1) ou não (0)
        this.escolhas.add(i);
    }

    public void gameOver(Heroi player) {
        int rota = 0; // A rota é definida pelo vetor de escolhas do jogador

        for (int i = 0; i < this.escolhas.size(); i++) {
            IO.println(this.escolhas.get(i));
            if (this.escolhas.get(i) == 1) { // O jogador não relutou em alguma escolha, portanto seguirá a rota padrão
                rota = 1; 
                break;
            }
            }
        // Caso todas as escolhas tenham sido relutantes, o jogador seguirá a rota relutante (0)

        if (rota == 0) { // Rota relutante
            for (int i = 0; i < this.frames.size(); i++) { // Cutscene
                App.limparTela();
                IO.println(frames.get(i));
                try {
                    if (i < 9)
                        TimeUnit.MILLISECONDS.sleep(500);
                    else if (i < 21)
                        TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            IO.println("Deseja reencontrar seus inimigos?\n");
            IO.println("1 - Sim (chance de fazer a coisa certa)");
            IO.println("2 - Não...\n");
            int ans = scanner.nextInt();
            scanner.nextLine();

            if (ans == 1) {
                lutaFinal(player);
            } else {
                IO.println(""); // Frustração do pinguim
            }
        } else {
            for (int i = 0; i < this.frames.size(); i++) { // Cutscene
                App.limparTela();
                IO.println(frames.get(i));
                try {
                    if (i < 9)
                        TimeUnit.MILLISECONDS.sleep(500);
                    else if (i == 9 || i >= 21)
                        TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public void lutaFinal(Heroi player) {

        IO.println("Nível Final\n");
        IO.println(player.getNome() + " acaba de encontrar *TODOS* os inimigos!");
        IO.println("                                 _T_\n" + //
                   "  !  .'´o)=-       -=(O¬'.    -=(V¬'.\n" + //
                   "     /.-.'            '._.\\      '.-.\\\n" + //
                   "    //  |\\    VS     /| V \\\\    /|*V*\\\\\n" + //
                   "    ||  |'           '|   ||    '|*_*_||  (V) O O (V)\n" + //
                   "  _,:(_/_             _\\ _):,_   _\\ _):,_   `(, ,)´\n\n"); // Capa da luta final
        IO.println("Deseja confrontá-los?\n");
        IO.println("1 - Sim!");
        IO.println("2 - Não...\n");
        int ans = scanner.nextInt();
        scanner.nextLine();

        IO.println("¨_ .'´^)=-" + //
                " \\\\/.-.'     Agradecemos por jogar!" + //
                "  //  |\\     O nível final ainda está em desenvolvimento, aguarde..." + //
                "  ||  |' " + //
                "_,:(_/_ ");
    }
}




