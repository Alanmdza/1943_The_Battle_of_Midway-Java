package juego0.ataquesEspeciales;

import java.util.Vector;

import juego0.core.ObjetoGrafico;
import juego0.enemigos.Enemigo;

public class Tsunami extends AtaqueEspecial {
    private Vector<Enemigo> afectados = new Vector<>();

    public Tsunami() {
        super("images/1984/Tsunami.png", 400, 0);
    }

    @Override
    public void update() {
        this.moverX(-10);
        paso++;
        if (paso>180) this.borrar=true;
    }

    public Vector<Enemigo> getAfectados() {
        return afectados;
    }
    public int desplazamiento() {
        return paso*(-10);
    }

    public void aplicar(Vector<ObjetoGrafico> objetosGraficos) {
        for (ObjetoGrafico objeto : objetosGraficos) {
            if (objeto instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) objeto;
                if (enemigo.getChico() && !afectados.contains(enemigo)
                        && intersección(this, enemigo)) {
                    afectados.add(enemigo);
                    enemigo.recibirDanio(10);
                }
            }
        }
    }
    public static boolean intersección(ObjetoGrafico a, ObjetoGrafico b) {
        double ax = a.getX();
        double ay = a.getY();
        double aw = a.getWidth();
        double ah = a.getHeight();
        double bx = b.getX();
        double by = b.getY();
        double bw = b.getWidth();
        double bh = b.getHeight();
        aw += ax;
        ah += ay;
        bw += bx;
        bh += by;
        return ((aw < ax || aw > bx) &&
                (ah < ay || ah > by) &&
                (bw < bx || bw > ax) &&
                (bh < by || bh > ay));
    }

}
