package juego0.Jefes;

import java.util.Vector;

import juego0.core.ObjetoGrafico;
import juego0.enemigos.Torretas.*;
import juego0.niveles.Nivel;

public class Yamato extends Jefe {
    private int estadoMovimiento = 0, deltax, deltay;
    private long count = 0;

    public Yamato(Vector<ObjetoGrafico> pendientesGraficos,Nivel nivel) {
        super("images/1984/Jefes/Yamato/Yamato.png", 300, -1700, 33);
        torretas.add(new Torreta2(this.positionX + 98, this.positionY + 523));
        torretas.add(new Torreta2(this.positionX + 98, this.positionY + 385));
        torretas.add(new Torreta2(this.positionX + 98, this.positionY + 1130));
        torretas.add(new Torreta1(this.positionX + 115, this.positionY + 650));
        torretas.add(new Torreta1(this.positionX + 115, this.positionY + 1020));
        torretas.add(new Torreta3(this.positionX + 37, this.positionY + 1025, 240 + 60));
        torretas.add(new Torreta3(this.positionX + 217, this.positionY + 1025, 240 + 60));
        torretas.add(new Torreta3(this.positionX + 175, this.positionY + 970, 240 + 60));
        torretas.add(new Torreta3(this.positionX + 81, this.positionY + 970, 240 + 60));
        torretas.add(new Torreta3(this.positionX + 15, this.positionY + 937, 240 + 60));
        torretas.add(new Torreta3(this.positionX + 218, this.positionY + 937, 240 + 60));
        torretas.add(new Torreta3(this.positionX + 205, this.positionY + 917, 240 + 60));
        torretas.add(new Torreta3(this.positionX + 245, this.positionY + 913, 240 + 90));
        torretas.add(new Torreta3(this.positionX + 0, this.positionY + 913, 240 + 90));
        torretas.add(new Torreta3(this.positionX + 35, this.positionY + 917, 240 + 90));
        torretas.add(new Torreta3(this.positionX + 40, this.positionY + 850, 240 + 90));
        torretas.add(new Torreta3(this.positionX + 0, this.positionY + 870, 240 + 90));
        torretas.add(new Torreta3(this.positionX + 0, this.positionY + 820, 240 + 90));
        torretas.add(new Torreta3(this.positionX + 42, this.positionY + 798, 240 + 240));
        torretas.add(new Torreta3(this.positionX + 0, this.positionY + 780, 240 + 120));
        torretas.add(new Torreta3(this.positionX + 27, this.positionY + 740, 240 + 120));
        torretas.add(new Torreta3(this.positionX + 205, this.positionY + 850, 240 + 120));
        torretas.add(new Torreta3(this.positionX + 245, this.positionY + 870, 240 + 120));
        torretas.add(new Torreta3(this.positionX + 245, this.positionY + 820, 240 + 120));
        torretas.add(new Torreta3(this.positionX + 203, this.positionY + 798, 240 + 120));
        torretas.add(new Torreta3(this.positionX + 245, this.positionY + 780, 240 + 120));
        torretas.add(new Torreta3(this.positionX + 218, this.positionY + 740, 240 + 240));
        torretas.add(new Torreta3(this.positionX + 30, this.positionY + 1350, 240 + 240));
        torretas.add(new Torreta3(this.positionX + 5, this.positionY + 1330, 240 + 240));
        torretas.add(new Torreta3(this.positionX + 215, this.positionY + 1350, 240 + 240));
        torretas.add(new Torreta3(this.positionX + 240, this.positionY + 1330, 240 + 240));
        torretas.add(new Torreta0(this.positionX + 98, this.positionY + 720));
        energiaMaxima = 0;
        for (Torreta torreta : torretas) {
            energiaMaxima += torreta.getEnergia();
        }
        this.nivel=nivel;
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
                if (count >= 600) {
                    estadoMovimiento = 1;
                    count = 0;
                }
                break;
            case 1:
                deltax = -3;
                deltay = 0;
                count++;
                if (count >= 100) {
                    estadoMovimiento = 2;
                    count = 0;
                }
                break;
            case 2:
                deltay = -3;
                deltax = 0;
                count++;
                if (count >= 400) {
                    estadoMovimiento = 3;
                    count = 0;
                }
                break;
            case 3:
                deltax = 3;
                deltay = 0;
                count++;
                if (count >= 100) {
                    estadoMovimiento = 4;
                    count = 0;
                }
                break;
            case 4:
                deltay = 3;
                deltax = 0;
                count++;
                if (count >= 400) {
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
            torreta.update(p38x, p38y);        }
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
