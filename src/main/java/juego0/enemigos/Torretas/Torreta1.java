package juego0.enemigos.Torretas;

import java.util.HashMap;
import java.util.Map;


public class Torreta1 extends Torreta {
    Map<Integer, Integer> tablaHashX = new HashMap<>();
    Map<Integer, Integer> tablaHashY = new HashMap<>();

    public Torreta1(double positionX, double positionY) {
        super("images/1984/Jefes/Yamato/T1180.png", positionX, positionY);
        tablaHashX.put(0, Integer.valueOf(0));
        tablaHashX.put(30, Integer.valueOf(-2));
        tablaHashX.put(45, Integer.valueOf(-2));
        tablaHashX.put(60, Integer.valueOf(0));
        tablaHashX.put(90, Integer.valueOf(0));
        tablaHashX.put(120, Integer.valueOf(0));
        tablaHashX.put(135, Integer.valueOf(2));
        tablaHashX.put(150, Integer.valueOf(4));
        tablaHashX.put(180, Integer.valueOf(0));
        tablaHashX.put(210, Integer.valueOf(-4));
        tablaHashX.put(225, Integer.valueOf(-15));
        tablaHashX.put(240, Integer.valueOf(-20));
        tablaHashX.put(270, Integer.valueOf(-22));
        tablaHashX.put(300, Integer.valueOf(-20));
        tablaHashX.put(315, Integer.valueOf(-15));
        tablaHashX.put(330, Integer.valueOf(-4));
        tablaHashX.put(360, Integer.valueOf(0));
        tablaHashY.put(0, Integer.valueOf(0));
        tablaHashY.put(30, Integer.valueOf(-8));
        tablaHashY.put(45, Integer.valueOf(-5));
        tablaHashY.put(60, Integer.valueOf(-2));
        tablaHashY.put(90, Integer.valueOf(5));
        tablaHashY.put(120, Integer.valueOf(0));
        tablaHashY.put(135, Integer.valueOf(0));
        tablaHashY.put(150, Integer.valueOf(0));
        tablaHashY.put(180, Integer.valueOf(0));
        tablaHashY.put(210, Integer.valueOf(0));
        tablaHashY.put(225, Integer.valueOf(0));
        tablaHashY.put(240, Integer.valueOf(1));
        tablaHashY.put(270, Integer.valueOf(5));
        tablaHashY.put(300, Integer.valueOf(1));
        tablaHashY.put(315, Integer.valueOf(-6));
        tablaHashY.put(330, Integer.valueOf(-11));
        tablaHashY.put(360, Integer.valueOf(-12));
        tiempoRecarga=120;
    }

    public void update(double p38x, double p38y) {
        super.update();
        if (paso % 3 == 0) {
            grados = (int) Math.toDegrees(Math.atan2((positionY - p38y), (positionX - p38x)));
            grados = grados + 270;
            if (grados < 0) {
                grados += 360;
            }
            if (grados > 360) {
                grados -= 360;
            }
            int a = obtenerAnguloNotableCercano(grados);
            if (ultimoGrado != a) {
                ultimoGrado = a;
                this.setImagen("images/1984/Jefes/Yamato/T1" + Integer.toString(ultimoGrado) + ".png");
                offsideX = tablaHashX.get(ultimoGrado);
                offsideY = tablaHashY.get(ultimoGrado);
            }
        }
    }

    private int obtenerAnguloNotableCercano(int numero) {
        int[] angulosNotables = { 0, 30, 45, 60, 90, 120, 135, 150, 180, 210, 225, 240, 270, 300, 315, 330, 360 };
        int anguloCercano = angulosNotables[0];
        int menorDiferencia = Math.abs(angulosNotables[0] - numero);

        for (int i = 1; i < angulosNotables.length; i++) {
            int diferencia = Math.abs(angulosNotables[i] - numero);
            if (diferencia < menorDiferencia) {
                menorDiferencia = diferencia;
                anguloCercano = angulosNotables[i];
            }
        }
        if (anguloCercano == 0)
            anguloCercano = 360;
        return anguloCercano;
    }
}
