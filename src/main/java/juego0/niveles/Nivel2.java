package juego0.niveles;

import java.util.Vector;

import juego0.Jefes.Yamato;
import juego0.core.Fondo;
import juego0.core.ObjetoGrafico;
import juego0.enemigos.*;


public class Nivel2 extends Nivel {
    public Nivel2(Vector<ObjetoGrafico> pendientesGraficos, long[] diffSeconds, long[] diffMinutes) {
        fondo = new Fondo("images/1984/fondo.png");
        fondo.setY(-fondo.getHeight() + 810);
        this.pendientesGraficos = pendientesGraficos;
        this.diffSeconds = diffSeconds;
        this.diffMinutes = diffMinutes;
    }

    @Override
    public void run() {
        pendientesGraficos.add(new Enemigo3(-30, 820));
        pendientesGraficos.add(new EnemigoRojo(-100, 200));
        pendientesGraficos.add(new EnemigoRojo(-200, 200));
        pendientesGraficos.add(new EnemigoRojo(-300, 200));
        pendientesGraficos.add(new EnemigoRojo(-400, 200));
        pendientesGraficos.add(new EnemigoRojo(-500, 200));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LU(300 + 200, 900));
        pendientesGraficos.add(new Enemigo1RU(100, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LU(300 + 100, 850));
        pendientesGraficos.add(new Enemigo1RU(100 + 100, 850));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LU(300, 800));
        pendientesGraficos.add(new Enemigo1RU(100 + 200, 800));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(80, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(160, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(240, -140, true));
        pendientesGraficos.add(new EnemigoGde2(400, 890));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(360, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(420, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(420, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(360, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(240, -140, true));
        pendientesGraficos.add(new EnemigoGde1(-150, 200));
        esperar(4);
        pendientesGraficos.add(new Enemigo1RD(100, -100));
        pendientesGraficos.add(new Enemigo1LD(400, -100));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RU(100, 900));
        pendientesGraficos.add(new Enemigo1LU(400, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RD(200, -100));
        pendientesGraficos.add(new Enemigo1LD(300, -100));
        pendientesGraficos.add(new Enemigo3(-30, 820));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RU(200, 900));
        pendientesGraficos.add(new Enemigo1LU(300, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RU(150, 900));
        pendientesGraficos.add(new Enemigo1RU(300, 900));
        pendientesGraficos.add(new Enemigo1RU(450, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LD(450, -100));
        pendientesGraficos.add(new Enemigo1LD(300, -100));
        pendientesGraficos.add(new Enemigo1LD(150, -100));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LU(150, 900));
        pendientesGraficos.add(new Enemigo1LU(300, 900));
        pendientesGraficos.add(new Enemigo1LU(450, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RD(450, -100));
        pendientesGraficos.add(new Enemigo1RD(300, -100));
        pendientesGraficos.add(new Enemigo1RD(150, -100));
        esperar(3);
        pendientesGraficos.add(new Enemigo3(-30, 820));
        pendientesGraficos.add(new EnemigoRojo(-100, 200));
        pendientesGraficos.add(new EnemigoRojo(-200, 200));
        pendientesGraficos.add(new EnemigoRojo(-300, 200));
        pendientesGraficos.add(new EnemigoRojo(-400, 200));
        pendientesGraficos.add(new EnemigoRojo(-500, 200));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LU(300 + 200, 900));
        pendientesGraficos.add(new Enemigo1RU(100, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LU(300 + 100, 850));
        pendientesGraficos.add(new Enemigo1RU(100 + 100, 850));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LU(300, 800));
        pendientesGraficos.add(new Enemigo1RU(100 + 200, 800));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(80, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(160, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(240, -140, true));
        pendientesGraficos.add(new EnemigoGde2(400, 890));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(360, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(420, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(420, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(360, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -140, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(240, -140, true));
        pendientesGraficos.add(new EnemigoGde1(-150, 200));
        esperar(4);
        pendientesGraficos.add(new Enemigo1RD(100, -100));
        pendientesGraficos.add(new Enemigo1LD(400, -100));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RU(100, 900));
        pendientesGraficos.add(new Enemigo1LU(400, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RD(200, -100));
        pendientesGraficos.add(new Enemigo1LD(300, -100));
        pendientesGraficos.add(new Enemigo3(-30, 820));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RU(200, 900));
        pendientesGraficos.add(new Enemigo1LU(300, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RU(150, 900));
        pendientesGraficos.add(new Enemigo1RU(300, 900));
        pendientesGraficos.add(new Enemigo1RU(450, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LD(450, -100));
        pendientesGraficos.add(new Enemigo1LD(300, -100));
        pendientesGraficos.add(new Enemigo1LD(150, -100));
        esperar(1);
        pendientesGraficos.add(new Enemigo1LU(150, 900));
        pendientesGraficos.add(new Enemigo1LU(300, 900));
        pendientesGraficos.add(new Enemigo1LU(450, 900));
        esperar(1);
        pendientesGraficos.add(new Enemigo1RD(450, -100));
        pendientesGraficos.add(new Enemigo1RD(300, -100));
        pendientesGraficos.add(new Enemigo1RD(150, -100));
        esperar(2);
        pendientesGraficos.add(new Contact());
        esperar(8);
        this.estado = 4;
        jefe = new Yamato(pendientesGraficos,this);
        esperar(1);
        pendientesGraficos.add(new Enemigo2(180, -260, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(500, -200, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(200, -320, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -260, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo4(810, 300));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(160, -260, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(500, -200, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(200, -320, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -260, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo4(810, 300));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(160, -260, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(500, -200, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(200, -320, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -260, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo4(810, 300));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(160, -260, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(500, -200, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(200, -320, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -260, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo4(810, 300));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(160, -260, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(500, -200, true));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(200, -320, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(300, -260, false));
        esperar(1);
        pendientesGraficos.add(new Enemigo4(810, 300));
        esperar(1);
        pendientesGraficos.add(new Enemigo2(160, -260, true));
        esperar(2);
        estado = 5;
    }

    private void esperar(int seg) {
        while (this.diffSeconds[0] + (60 * this.diffMinutes[0]) < tiempoEspera + seg) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tiempoEspera += seg;
    }
}
