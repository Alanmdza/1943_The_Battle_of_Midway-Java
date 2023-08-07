package juego0.Jefes;

import java.util.Vector;

import juego0.core.ObjetoGrafico;
import juego0.enemigos.Torretas.*;
import juego0.niveles.Nivel;


public class Tone extends Jefe {
    private int estadoMovimiento = 0, deltax, deltay;
    private long count = 0;

    public Tone(Vector<ObjetoGrafico> pendientesGraficos, Nivel nivel) {
        super("images/1984/Jefes/Tone/Tone.png", 300, -1700, 10);
        torretas.add(new Torreta5(positionX + 45, positionY + 355));
        torretas.add(new Torreta6(positionX + 63, positionY + 727, 100));
        torretas.add(new Torreta6(positionX + 63, positionY + 647, 130));
        torretas.add(new Torreta6(positionX, positionY + 482, 130));
        torretas.add(new Torreta6(positionX + 122, positionY + 482, 90));
        torretas.add(new Torreta6(positionX, positionY + 382, 90));
        torretas.add(new Torreta6(positionX + 122, positionY + 382, 160));
        torretas.add(new Torreta6(positionX + 63, positionY + 245, 112));
        torretas.add(new Torreta6(positionX + 63, positionY + 200, 125));
        torretas.add(new Torreta6(positionX + 63, positionY + 155, 125));
        this.nivel=nivel;
        energiaMaxima = 0;
        for (Torreta torreta : torretas) {
            energiaMaxima += torreta.getEnergia();
        }
    }

    @Override
    public void update() {
        if (torretas.isEmpty()) nivel.setEstado(2);
        Limpieza();
        switch (estadoMovimiento) {
            case 0:
                deltay = 3;
                deltax = 0;
                count++;
                if (count >= 530) {
                    estadoMovimiento = 1;
                    count = 0;
                }
                break;
            case 1:
                deltax = -2;
                deltay = 0;
                count++;
                if (count >= 100) {
                    estadoMovimiento = 2;
                    count = 0;
                }
                break;
            case 2:
                deltax = 2;
                count++;
                if (count >= 100) {
                    estadoMovimiento = 1;
                    count = 0;
                }
                break;
        }
         moverX(deltax);
         moverY(deltay);
        for (Torreta torreta : torretas) {
             torreta.moverX(deltax);
             torreta.moverY(deltay);
            torreta.update(p38x, p38y);
        }
    }

    private void Limpieza() {
        Vector<Torreta> vector = new Vector<>();
        for (Torreta torreta : torretas) {
            if (torreta.getBorrar()) {
                vector.add(torreta);
            }
        }
        for (Torreta torreta : vector) {
            danioAcumulado += torreta.getEnergiaInicial();
            torretas.remove(torreta);
        }
    }
}
