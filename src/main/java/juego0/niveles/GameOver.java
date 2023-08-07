package juego0.niveles;

import java.awt.event.KeyEvent;
import java.util.Vector;

import entropyinteractive.Keyboard;

import juego0.core.*;

public class GameOver extends Nivel {
    private boolean exit = false;
    private boolean flag;
    private Keyboard keyboard;
    private Vector<ObjetoGrafico> pendientesGraficos;
    private Selector selector = new Selector();

    public GameOver(Keyboard keyboard, Vector<ObjetoGrafico> pendientesGraficos) {
        this.keyboard = keyboard;
        this.pendientesGraficos = pendientesGraficos;

    }

    @Override
    public void run() {
        fondo = new Fondo("images/1984/GameOver.png");
        fondo.setFijo();
        flag = true;
        pendientesGraficos.add(selector);
        while (true) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_S) || (keyboard.isKeyPressed(KeyEvent.VK_W))) {
                if (flag) {
                    if (exit) {
                        selector.moverY(-42);
                    } else
                        selector.moverY(42);
                    exit = !exit;
                    flag = false;
                }
            } else {
                flag = true;
            }
            if (keyboard.isKeyPressed(KeyEvent.VK_ENTER)) {
                if (exit)
                    estado = -1;
                else
                    estado = 3;
            }
        }
    }
}
