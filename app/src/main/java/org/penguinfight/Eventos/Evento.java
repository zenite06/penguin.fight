package org.penguinfight.Eventos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import org.penguinfight.App;
import org.penguinfight.Cartas.Carta;
import org.penguinfight.Efeitos.Efeito;
import org.penguinfight.Entidades.Heroi;
import org.penguinfight.Entidades.Inimigo;

public abstract class Evento {
    protected String local;

    public Evento(String local) {
        this.local = local;
    }

    public String getLocal() {
        return this.local;
    }

    public abstract boolean iniciar();
}
