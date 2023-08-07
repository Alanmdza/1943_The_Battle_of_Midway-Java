package juego0.armas;

import java.util.Vector;

import juego0.core.ObjetoGrafico;

public abstract class Arma {
    protected int danio = 10;
    protected int rafaga = 1;
    protected Vector<ObjetoGrafico> pendientesGraficos;

    public void setRafaga(int rafaga) {
        this.rafaga = rafaga;
    }

    public int getRafaga() {
        return rafaga;
    }

    public void setPendienteGraficos(Vector<ObjetoGrafico> pendienteGraficos) {
        this.pendientesGraficos = pendienteGraficos;
    }

    public abstract void mejorar();

    public abstract void disparar(double x, double y);

}
