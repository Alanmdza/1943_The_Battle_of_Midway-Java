package juego0.enemigos;

import java.awt.geom.Point2D;
import java.util.Vector;
import juego0.armas.disparos.*;
import juego0.core.*;


public abstract class Enemigo extends ObjetoGrafico implements Hiteable {
    protected Point2D.Double origen = new Point2D.Double();
    protected int alcance = 1500;;
    protected int energia = 10;
    protected boolean chico = true; // Nos sirve para revisar las interacciones con los ataques especiales
    protected boolean paraDisparar = false;

    public Enemigo(String filename, double positionX, double positionY) {
        super(filename, positionX, positionY);
        origen.setLocation(positionX, positionY);
    }

    public void recibirDisparo(Disparo disparo) {
       recibirDanio(disparo.getDanio());
    }

    public void recibirDanio(int danio) {
        energia -= danio;
        if (energia <= 0) {
            borrar = true;
            FXPlayer.EXPLOSION.playEfect();
        }
    }

    public boolean getChico() {
        return chico;
    }

    public boolean disparoPendiente() {
        return this.paraDisparar;
    }

    public void disparar(P38 p38, Vector<ObjetoGrafico> pendientesGraficos) {
        double angulo = Math.atan2((p38.getY() - positionY), (p38.getX() - positionX));
        pendientesGraficos.add(new DisparoEnemigo(positionX, positionY, 3 * Math.cos(angulo), 3 * Math.sin(angulo)));
        this.paraDisparar = false;
    }

    @Override
    public void update() {
         if (this.origen.distance(positionX, positionY) > alcance) {
            borrar = true;
        }
    }

    public boolean fueraDeRango(){
        return this.origen.distance(positionX, positionY) > alcance;
    }
}
