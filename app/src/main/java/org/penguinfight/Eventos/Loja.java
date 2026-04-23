package org.penguinfight.Eventos;

import java.util.List;
import java.util.Stack;

import org.penguinfight.Cartas.Carta;

public class Loja extends Evento {

    public Loja (String local) {
        super(local);
    }

    public boolean iniciar(List <Carta> pilhaDescarte, Stack <Carta> pilhaCompra) {
        return true;
    }
}
