package juego0.niveles;

import java.util.Random;
import java.util.Vector;

import juego0.bonus.*;
import juego0.bonus.powerUps.*;
import juego0.core.ObjetoGrafico;

public class GeneradorBonus extends Thread {
    private final int T = 16;
    private Random random = new Random();
    private Vector<ObjetoGrafico> pendientesGraficos;
    private long[] diffSeconds = { 0 };
    private long diffSeconds2;
    private int numero = random.nextInt(T) + 1;

    public GeneradorBonus(Vector<ObjetoGrafico> pendientesGraficos, long[] diffSeconds) {
        this.pendientesGraficos = pendientesGraficos;
        this.diffSeconds = diffSeconds;
    }

    public void run() {
        while (true) {
            diffSeconds2 = diffSeconds[0];
            while (diffSeconds2 > T){diffSeconds2 -= T;}
            if (diffSeconds2 == numero) {
                pendientesGraficos.add(bonusRandom());
                numero = random.nextInt(T) + 1;
            }
        }
    }

    private Bonus bonusRandom() {
        int numeroRandom1;
        numeroRandom1 = random.nextInt(7) + 1;
        Bonus bonus = new Auto();
        switch (numeroRandom1) {
            case 1:
                bonus = new Auto();
            case 2:
                bonus = new EstrellaNinja();
                break;
            case 3:
                bonus = new POW();
                break;
            case 4:
                bonus = new SuperShell();
                break;
            case 5:
                bonus = new CambioArma();
                break;
            case 6:
                bonus = new ObtenerRefuerzos();
                break;
        }
        return bonus;
    }

}
