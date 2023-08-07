package juego0.ataquesEspeciales;

import java.util.Vector;

import juego0.core.ObjetoGrafico;
import juego0.enemigos.Enemigo;

public class Relampago extends AtaqueEspecial {

    public Relampago() {
        super("images/1984/Relampago.png", -40, 0);
    }

    @Override
    public void update() {
        paso++;
        if (paso > 10)
            this.borrar = true;
    }

    public void aplicar(Vector<ObjetoGrafico> objetosGraficos) {
        for (ObjetoGrafico objeto : objetosGraficos) {
            if (objeto instanceof Enemigo) {
                Enemigo enemigo = (Enemigo) objeto;
                if (enemigo.getChico())
                    enemigo.setBorrar();
                // borra todos los enemigos chicos.
            }
        }
    }

}
