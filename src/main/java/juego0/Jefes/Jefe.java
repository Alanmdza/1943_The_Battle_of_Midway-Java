package juego0.Jefes;

import java.awt.Graphics2D;
import java.util.Vector;
import juego0.core.*;
import juego0.enemigos.Enemigo;
import juego0.enemigos.Torretas.Torreta;
import juego0.niveles.Nivel;

public abstract class Jefe extends Enemigo {

    protected Vector<Torreta> torretas = new Vector<>();
    protected int TOTALTORRETAS;
    protected int energiaMaxima;
    protected double danioAcumulado;
    protected double p38x = 0, p38y = 0;
    protected Nivel nivel;

    public Jefe(String filename, double positionX, double positionY, int TOTALTORRETAS) {
        super(filename, positionX, positionY);
        this.TOTALTORRETAS = TOTALTORRETAS;
        alcance = 10000;

    }

    public Vector<Torreta> getTorretas() {
        return torretas;
    }

    @Override
    public void display(Graphics2D g2) {
        g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, null);
        for (Torreta torreta : torretas) {
            torreta.display(g2);
        }
    }

    // suma el danio acumulado de cada torreta y lo divide con la cantidad para
    // tener un porcentaje
    public double getDanioAcumulado() {
        for (Torreta torreta : torretas) {
            danioAcumulado += torreta.getEnergiaInicial() - torreta.getEnergia();
        }
        return danioAcumulado / TOTALTORRETAS;
    }

    @Override
    public void disparar(P38 p38, Vector<ObjetoGrafico> pendientesGraficos) {
        p38x = p38.getX();
        p38y = p38.getY();
        for (Torreta torreta : torretas) {
            if (torreta.disparoPendiente())
                torreta.disparar(p38, pendientesGraficos);
        }
    }
}
