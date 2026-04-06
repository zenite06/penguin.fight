package org.penguinfight.Entidades;

import org.penguinfight.RoundManager;

public interface Observer {
    void serNotificado(String evento, RoundManager manager);
}
