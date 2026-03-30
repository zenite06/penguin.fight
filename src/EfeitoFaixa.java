public class EfeitoFaixa extends Efeito {
    // Cada acúmulo de efeito de faixa aumenta em 2 pontos o escudo e reduz em 2 pontos o dano causado na entidade
    public static final String ANSI_RESET = "\u001B[0m"; 
    public static final String ANSI_WHITE = "\u001B[1;37m";
    public static final String ANSI_YELLOW = "\033[1;33m";
    public static final String ANSI_ORANGE = "\u001B[1;38;2;255;165;0m"; 
    public static final String ANSI_GREEN = "\u001B[1;32m";
    public static final String ANSI_BLUE = "\u001B[1;34m";
    public static final String ANSI_RED = "\u001B[1;31m";
    public static final String ANSI_PURPLE = "\033[1;35m";
    public static final String ANSI_BROWN = "\u001B[1;33m";
    public static final String ANSI_BLACK = "\u001B[1;32m";


    public EfeitoFaixa(int acumulos) {
        super("FAIXA", acumulos); // Default
    }

    public void aplicarFaixa(Inimigo inimigo) {
        String cor = ANSI_RESET;
        switch (this.getAcumulos()) {
            case 1:
                cor = ANSI_WHITE;
                break;
            case 2:
                cor = ANSI_YELLOW;
                break;
            case 3:
                cor = ANSI_ORANGE;
                break;
            case 4:
                cor = ANSI_GREEN;
                break;
            case 5:
                cor = ANSI_BLUE;
                break;
            case 6:
                cor = ANSI_RED;
                break;
            case 7:
                cor = ANSI_PURPLE;
                break;
            case 8:
                cor = ANSI_BROWN;
                break;
            case 9:
                cor = ANSI_BLACK;
                break;
            default:
                break;
        }

        if (RoundManager.getLevel() == 1) {
            inimigo.setCapa("     .'´o)=-       -=(O¬'.\n" + //
                                "     /.-.'            '._.\\\n" + //
                                "    //   |\\    VS    /| V \\\\\n" + //
                                "    ||" + cor + "===" + ANSI_RESET + "|'          '|   ||\n" + //
                                "  _,:(_ /_            _\\ _):,_");
        } else if (RoundManager.getLevel() == 2) {
            inimigo.setCapa("                      _T_\n" + //
                                "     .'´o)=-       -=(V¬'.\n" + //
                                "     /.-.'            '.-.\\\n" + //
                                "    //   |\\    VS    /|*V*\\\\\n" + //
                                "    ||" + cor + "===" + ANSI_RESET + "|'          '|*_*_||\n" + //
                                "  _,:(_ /_            _\\ _):,_");
        } else if (RoundManager.getLevel() == 3) {
            inimigo.setCapa("     .'´o)=- \n" + //
                                "     /.-.' \n" + //
                                "    //   |\\    VS \n" + //
                                "    ||" + cor + "===" + ANSI_RESET + "|'         (V) O O (V)\n" + //
                                "  _,:(_ /_             `(, ,)´\n");
        }
    }

    public void resetCapa(Inimigo inimigo) {
        if (RoundManager.getLevel() == 1) {
            inimigo.setCapa("     .'´o)=-      -=(O¬'.\\n\" + //\r\n" + //
"                        \"     /.-.'           '._.\\\\\\n\" + //\r\n" + //
"                        \"    //  |\\\\    VS    /| V \\\\\\\\\\n\" + //\r\n" + //
"                        \"    ||  |'          '|   ||\\n\" + //\r\n" + //
"                        \"  _,:(_/_            _\\\\ _):,_\\n");
        } else if (RoundManager.getLevel() == 2) {
            inimigo.setCapa("                     _T_\\n\" + //\r\n" + //
"                        \"     .'´o)=-      -=(V¬'.\\n\" + //\r\n" + //
"                        \"     /.-.'           '.-.\\\\\\n\" + //\r\n" + //
"                        \"    //  |\\\\    VS    /|*V*\\\\\\\\\\n\" + //\r\n" + //
"                        \"    ||  |'          '|*_*_||\\n\" + //\r\n" + //
"                        \"  _,:(_/_            _\\\\ _):,_");
        } else if (RoundManager.getLevel() == 3) {
            inimigo.setCapa("     .'´o)=- \\n\" + //\r\n" + //
"                        \"     /.-.' \\n\" + //\r\n" + //
"                        \"    //  |\\\\    VS \\n\" + //\r\n" + //
"                        \"    ||  |'         (V) O O (V)\\n\" + //\r\n" + //
"                        \"  _,:(_/_            `(, ,)´");
        }
    }

    public void usar(Heroi player, Inimigo inimigo, RoundManager manager) {
        IO.println(player.getNome() + " treinou técnicas mais avançadas e aumentou sua faixa!\n");
        player.setEscudo(player.getEscudo() + (2 * this.getAcumulos()));
        inimigo.setDano(inimigo.getDano() - (2 * this.getAcumulos()));
        aplicarFaixa(inimigo);
    }

    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("FIM DO ROUND"))
            usar(manager.getPlayer(), manager.getInimigo(), manager);
    }
}

/*
DICIONÁRIO DE FAIXAS PARA ACÚMULOS
1 - Branca
2 - Amarela
3 - Laranja
4 - Verde
5 - Azul
6 - Vermelha
7 - Roxa
8 - Marrom
9 - Preta
*/
