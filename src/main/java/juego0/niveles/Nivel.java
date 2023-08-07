package juego0.niveles;

import java.util.Vector;
import juego0.Jefes.Jefe;
import juego0.core.*;

public class Nivel extends Thread {
    protected int estado = 1;
    protected Jefe jefe;
    protected Vector<ObjetoGrafico> pendientesGraficos;
    protected Fondo fondo;
    protected int tiempoEspera = 0;
    public long diffSeconds[];
    public long diffMinutes[];

    public void nivel() {
    }

    public Fondo getFondo() {
        return fondo;
    }

    public int getestado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Jefe getJefe() {
        return jefe;
    }
}