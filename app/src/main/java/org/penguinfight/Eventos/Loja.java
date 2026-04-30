package org.penguinfight.Eventos;
import java.util.List;
import java.util.Stack;
import org.penguinfight.App;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Cartas.CartaDano;
import org.penguinfight.Cartas.CartaEfeito;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Entidades.Heroi;

public class Loja extends Evento {
    private List<Carta> cartas;

    public Loja (String local, List<Carta> cartas) {
        super(local);
        this.cartas = cartas;
    }

    public boolean iniciar() {
        Heroi player = Heroi.getInstance();
        App.limparTela();
        IO.println();
        IO.println(player.getNome() + " chegou a " + this.local + "\n");
        
        while(true) {
            IO.println(App.lerTXT("src/main/resources/Assets/loja1.txt"));
            IO.println("Você possui " + player.getMoedas() + " moedas em suas nadadeiras. O que deseja fazer?\n");
            int i = 0;
            for ( ; i < cartas.size(); i++) {
                if (cartas.get(i) instanceof CartaEfeito)
                    IO.println(i + " - Comprar " + App.ANSI_PURPLE + cartas.get(i).getNome() + App.ANSI_RESET + ": " + cartas.get(i).getDescricao() + " (Custo = " + cartas.get(i).getCusto() + ") " + App.ANSI_GREEN + "(" + cartas.get(i).getMoedas() + " moedas)" + App.ANSI_RESET);
                else if (cartas.get(i) instanceof CartaDano)
                    IO.println(i + " - Comprar " + cartas.get(i).getDescricao() + ": " + App.ANSI_PURPLE + cartas.get(i).getNome() + App.ANSI_RESET + " (Dano = " + cartas.get(i).getValor() + " / Custo = " + cartas.get(i).getCusto() + ") " + App.ANSI_GREEN + "(" + cartas.get(i).getMoedas() + " moedas)" + App.ANSI_RESET);
                else
                    IO.println(i + " - Comprar " + cartas.get(i).getDescricao() + ": " + App.ANSI_PURPLE + cartas.get(i).getNome() + App.ANSI_RESET + " (Defesa = " + cartas.get(i).getValor() + " / Custo = " + cartas.get(i).getCusto() + ") " + App.ANSI_GREEN + "(" + cartas.get(i).getMoedas() + " moedas)" + App.ANSI_RESET);
            }
            IO.println(i + " - Sair\n");

            int ans = App.scanner.nextInt();
            App.scanner.nextLine();

            if (ans > i || ans < 0) { // O jogador escolheu uma opção inválida
                App.limparTela();
                IO.println(App.ANSI_YELLOW + "Escolha uma das opções disponíveis!\n" + App.ANSI_RESET);
                continue;
            }
            if (ans == i) { // O jogador escolheu sair
                App.limparTela();
                IO.println("Deseja mesmo sair de " + this.local + "?\n");
                    IO.println("1 - Sim");
                    IO.println("2 - Não\n");
                    ans = App.scanner.nextInt();
                    App.limparTela();
                    if (ans == 1)
                        return true;
                    else 
                        continue;
            }
            else {
                App.limparTela();
                comprar(cartas.get(ans));
                continue;
            }
        }
    }

    public void comprar(Carta carta) {
        Heroi player = Heroi.getInstance();
        Stack <Carta> pilhaCompra = player.getPilhaCompra();
        if (player.getMoedas() >= carta.getMoedas()) {
            IO.println("Você possui " + player.getMoedas() + " moedas em suas nadadeiras. Deseja mesmo usar " + carta.getMoedas() + " delas para comprar " + carta.getDescricao() + ": " + App.ANSI_PURPLE + carta.getNome() + App.ANSI_RESET + " (Dano = " + carta.getValor() + " / Custo = " + carta.getCusto() + ")?\n");
                    IO.println("1 - Sim");
                    IO.println("2 - Não\n");
            int ans = App.scanner.nextInt();
            if (ans == 1) {
                App.limparTela();
                player.usarMoedas(carta.getMoedas());
                this.cartas.remove(carta);
                pilhaCompra.push(carta);
                IO.println("Você adicionou " + carta.getDescricao() + ": " + App.ANSI_PURPLE + carta.getNome() + App.ANSI_RESET + " (Dano = " + carta.getValor() + " / Custo = " + carta.getCusto() + ") ao seu baralho!" + App.ANSI_RED + " (- " + carta.getMoedas() + " moedas)" + App.ANSI_RESET + "\n");
                return;
            }
            else {
                App.limparTela();
                return;
            }
        }
        else {
            IO.println(App.ANSI_RED + "Você não possui moedas suficientes!" + App.ANSI_RESET);
            IO.println();
            return;
        }
    }
}
