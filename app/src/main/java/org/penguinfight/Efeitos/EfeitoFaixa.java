package org.penguinfight.Efeitos;
import org.penguinfight.Entidades.Entidade;
import org.penguinfight.RoundManager;

/**
 * Efeito progressivo exclusivo do jogador que representa o treinamento.
 * Concede bônus de escudo e altera a cor da faixa na arte ASCII do personagem.
 */
public class EfeitoFaixa extends Efeito {
    public static final String ANSI_RESET = "\u001B[0m"; 
    public static final String ANSI_WHITE = "\u001B[1;37m";
    public static final String ANSI_YELLOW = "\033[1;33m";
    public static final String ANSI_ORANGE = "\u001B[1;38;2;255;165;0m"; 
    public static final String ANSI_GREEN = "\u001B[1;32m";
    public static final String ANSI_BLUE = "\u001B[1;34m";
    public static final String ANSI_RED = "\u001B[1;31m";
    public static final String ANSI_PURPLE = "\033[1;35m";
    public static final String ANSI_BROWN = "\u001B[38;2;139;69;19m";
    public static final String ANSI_BLACK = "\u001B[1;30m";


    public EfeitoFaixa(int acumulos) {
        super("FAIXA", acumulos);
    }

    /**
     * Atualiza a arte visual do combate colorindo a faixa do herói baseada 
     * nos acúmulos (nível da faixa).
     */
    public void aplicarFaixa(RoundManager manager) {
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
            default:
                cor = ANSI_BLACK;
                break;
        }

        if (RoundManager.getLevel() == 0) {
            manager.getInimigo().setCapa("     .'´o)=-       -=(O¬'.\n" + //
                                "     /.-.'            '._.\\\n" + //
                                "    //   |\\    VS    /| V \\\\\n" + //
                                "    ||" + cor + "===" + ANSI_RESET + "|'          '|   ||\n" + //
                                "  _,:(_ /_            _\\ _):,_");
        } else if (RoundManager.getLevel() == 1) {
            manager.getInimigo().setCapa("                      _T_\n" + //
                                "     .'´o)=-       -=(V¬'.\n" + //
                                "     /.-.'            '.-.\\\n" + //
                                "    //   |\\    VS    /|*V*\\\\\n" + //
                                "    ||" + cor + "===" + ANSI_RESET + "|'          '|*_*_||\n" + //
                                "  _,:(_ /_            _\\ _):,_");
        } else if (RoundManager.getLevel() == 2) {
            manager.getInimigo().setCapa("     .'´o)=- \n" + //
                                "     /.-.' \n" + //
                                "    //   |\\    VS \n" + //
                                "    ||" + cor + "===" + ANSI_RESET + "|'         (V) O O (V)\n" + //
                                "  _,:(_ /_             `(, ,)´\n");
        }
    }

    @Override
    public void ativar(Entidade entidade, RoundManager manager) { // Não precisa...
        this.getDono().setEscudo(this.getDono().getEscudo() + (2 * this.getAcumulos()));
    } 

    @Override
    public void ativarImediato(Entidade entidade, RoundManager manager) {
        IO.println(entidade.getNome() + " treinou técnicas mais avançadas e aumentou sua faixa!\n");
        entidade.setEscudo(entidade.getEscudo() + (2 * this.getAcumulos()));
    }

    @Override
    public void serNotificado(String evento, RoundManager manager) {
        if (evento.equals("FIM DO ROUND")) // Não precisa
            ativar(this.getDono(), manager); 
    } 

    @Override
    public Efeito clonar() {
        return new EfeitoFaixa(this.getAcumulos());
    }
}
