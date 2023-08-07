package juego0.bonus.powerUps;

import java.awt.Graphics2D;
import java.util.Vector;

import juego0.armas.disparos.Disparo;
import juego0.core.Hiteable;
import juego0.core.ObjetoGrafico;

public class Contenedor extends ObjetoGrafico implements Hiteable {
    private Vector<ObjetoGrafico> pendientesGraficos;

    public Contenedor(double x, double y, Vector<ObjetoGrafico> pendientesGraficos) {
        super("images/1984/POW.png", x, y);
        this.pendientesGraficos = pendientesGraficos;
    }
    @Override
    public void display(Graphics2D g) {
        
    }
    @Override
    public void update() {
        this.setPosition(positionX, positionY + 2);
        if (positionY>1000) borrar = true;
    }

    @Override
    public void recibirDisparo(Disparo disparo) {
        this.borrar = true;
        pendientesGraficos.add(new ItemSecreto(positionX, positionY));
    }

}
