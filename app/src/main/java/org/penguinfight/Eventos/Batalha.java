package org.penguinfight.Eventos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.penguinfight.App;
import org.penguinfight.RoundManager;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

/**
 * Gerencia o motor de combate por turnos do jogo Penguin Fight.
 * Esta classe controla a interação entre o herói e o inimigo, o gerenciamento
 * do baralho (compra e descarte) e a progressão dos rounds.
 */
public class Batalha extends Evento {
    private Inimigo inimigo;
    private int recompensa;

    public Batalha(Inimigo inimigo, String local, int recompensa) {
        super(local);
        this.inimigo = inimigo;
        this.recompensa = recompensa;
    }

    public Inimigo getInimigo() {
        return this.inimigo;
    }

    /**
     * Inicia a sequência de combate. Apresenta o inimigo, solicita confirmação do 
     * jogador e mantém o loop de rounds enquanto ambos estiverem vivos.
     * * @param pilhaDescarte Lista de cartas já utilizadas.
     * @param pilhaCompra Pilha de cartas disponíveis para compra.
     * @return {@code true} se o jogador vencer a batalha, {@code false} se perder.
     */
    public boolean iniciar() {
        Heroi player = Heroi.getInstance();
        Stack <Carta> pilhaCompra = player.getPilhaCompra();
        List <Carta> pilhaDescarte = player.getPilhaDescarte();
        App.limparTela();
        IO.println();

        IO.println(Heroi.getInstance().getNome() + " chegou a " + this.local + "\n");
        IO.println(Heroi.getInstance().getNome() + " acaba de encontrar " + inimigo.getNome() + "\n");
        IO.println(inimigo.getC() + "\n");
        IO.println("Deseja confrontá-lo?\n");
        IO.println("1 - Sim!");
        IO.println("2 - Não...\n");
        int ans = App.scanner.nextInt();
        App.scanner.nextLine();

        if (ans == 2) {
            App.limparTela();
            IO.println();
            IO.println(App.lerTXT("src/main/resources/Assets/desistir2.txt")); 
            IO.println("\nDigite qualquer coisa para continuar\n");
            String rand = App.scanner.nextLine();
            App.getRota().addEscolha(0);
        } else
            App.getRota().addEscolha(1);

        App.limparTela();

        while (inimigo.estaVivo() && Heroi.getInstance().estaVivo()) {
            startRound(pilhaDescarte, pilhaCompra);
            RoundManager.getInstance().notificar("FIM DO ROUND"); // Manager notifica os efeitos de que o round acabou
        }
        App.limparTela();

        // A batalha terminou...
        if (inimigo.estaVivo()) { // O Heroi.getInstance() perdeu
            IO.println("\n");
            IO.println(App.ANSI_YELLOW + "Você perdeu...\n" + App.ANSI_RESET);
            IO.println();
            IO.println(inimigo.getCD());
            IO.println();
            resetBattle();
            return false;
        }
        else { // O Heroi.getInstance() ganhou
            IO.println("\n");
            IO.println(App.ANSI_YELLOW + "Você ganhou!\n" + App.ANSI_RESET);
            IO.println();
            IO.println(inimigo.getCV());
            IO.println();
            recompensa();
            resetBattle();
            return true;
        }
    }

    /**
     * Gerencia um round individual. Inclui a compra de cartas para a mão (nadadeira), 
     * a decisão de ataque/defesa/efeito do inimigo (aleatoriamente) e o processamento 
     * das cartas jogadas pelo usuário.
     * <p>
     * Se a pilha de compra esvaziar, este método embaralha a pilha de descarte 
     * e a repõe no topo da compra.
     * </p>
     * * @param pilhaDescarte Lista para onde as cartas usadas/descartadas são enviadas.
     * @param pilhaCompra Pilha de onde o jogador retira novas cartas.
     */
    public void startRound(List <Carta> pilhaDescarte, Stack <Carta> pilhaCompra) {
        List<Carta> nadadeira = new ArrayList<>();
        
        for (int i = 0; i < 6; i++) {
            if (pilhaCompra.isEmpty()) { // Se a pilha de compra estiver vazia, as cartas da pilha de descarte são embaralhadas e passam pro topo da pilha de compra
                Collections.shuffle(pilhaDescarte);
                while (!pilhaDescarte.isEmpty())
                    pilhaCompra.push(pilhaDescarte.remove(0)); 
            } 
            nadadeira.add(pilhaCompra.pop()); // São compradas cinco cartas da pilha de compra pra mão do Heroi.getInstance() (nadadeira)
        }
        inimigo.decidirAcao();
        while (true) {
            IO.println();
            inimigo.declarar();
            IO.println(App.ANSI_YELLOW + "                  " + inimigo.getNome() + " (Vida = " + inimigo.getVida() + ")\n" + App.ANSI_RESET);
            IO.println(inimigo.getC() + "\n");
            IO.println(App.ANSI_YELLOW + "  " + Heroi.getInstance().getNome() + " (Vida = " + Heroi.getInstance().getVida() + " / Defesa = " + Heroi.getInstance().getEscudo() + ")\n" + App.ANSI_RESET);
            IO.println("Nas suas nadadeiras existem cartas\nDeseja usá-las?\n");
            IO.println("Energia: " + Heroi.getInstance().getEnergia());
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

            int ans = App.scanner.nextInt();
            App.scanner.nextLine();

            if (ans > i || ans < 0) { // O jogador escolheu uma opção inválida
                App.limparTela();
                IO.println(App.ANSI_YELLOW + "Ei, não tente fugir dessa! Escolha uma das opções disponíveis\n" + App.ANSI_RESET);
                continue;
            }
            if (ans == i) { // O jogador escolheu finalizar o turno
                while (!nadadeira.isEmpty())
                    pilhaDescarte.add(nadadeira.remove(0)); 
                App.limparTela();
                RoundManager.getInstance().notificar("INIMIGO VAI ATACAR"); // Manager notifica os efeitos de que o inimigo vai atacar
                inimigo.atacar(Heroi.getInstance());
                inimigo.usarEfeito();
                RoundManager.getInstance().notificar("INIMIGO ATACOU"); // Manager notifica os efeitos de que o inimigo atacou
                resetRound();
                break;
            }
            // O jogador escolheu uma carta
            App.limparTela();
            nadadeira.get(ans).usar(Heroi.getInstance(), inimigo); 
            pilhaDescarte.add(nadadeira.remove(ans)); 
            if (!inimigo.estaVivo()) {
                while (!nadadeira.isEmpty())
                    pilhaDescarte.add(nadadeira.remove(0)); 
                resetRound();
                break;
            }
        }
    }

    /**
     * Reseta os atributos temporários de fim de round.
     * Zera o escudo acumulado de ambos os combatentes e restaura a energia do jogador.
     */
    public void resetRound() {
        inimigo.setEscudo(0);
        Heroi.getInstance().setEscudo(0);
        Heroi.getInstance().setEnergia(100);
    }

    /**
     * Limpa o estado da batalha após o término (vitória ou derrota).
     * Remove inscrições de efeitos do Manager, limpa listas de efeitos ativos 
     * e restaura a vida do inimigo para um estado inicial.
     */
    public void resetBattle() {
        for (Efeito efeito : Heroi.getInstance().getEfeitos())
            RoundManager.getInstance().desinscrever(efeito);
        for (Efeito efeito : inimigo.getEfeitos())
            RoundManager.getInstance().desinscrever(efeito);
        Heroi.getInstance().getEfeitos().clear();
        inimigo.getEfeitos().clear();
        RoundManager.getInstance().resetCapa(inimigo);
        inimigo.setVida(inimigo.getMaxVida());
        Heroi.getInstance().setEscudo(0);
    }

    public void recompensa() {
        Heroi.getInstance().ganharMoedas(this.recompensa);
        IO.println(App.ANSI_GREEN + "+ " + this.recompensa + " MOEDAS!\n" + App.ANSI_RESET);
    }
}


