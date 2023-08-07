package juego0.enemigos.Torretas;

import java.util.Vector;

import juego0.armas.disparos.Disparo;
import juego0.armas.disparos.DisparoEnemigo;
import juego0.armas.disparos.DisparoLaser;
import juego0.core.FXPlayer;
import juego0.core.ObjetoGrafico;
import juego0.enemigos.Enemigo;

public abstract class Torreta extends Enemigo {
    protected int ultimoGrado = 360;
    protected int blindaje = 20;
    protected long paso;
    protected int tiempoRecarga;
    int grados;

    public Torreta(String filename, double positionX, double positionY) {
        super(filename, positionX, positionY);
        chico = false;
        energia = 30;
    }

    @Override
    public void recibirDisparo(Disparo disparo) {
        disparo.setBorrar();
        if (disparo instanceof DisparoLaser) {
            energia -= disparo.getDanio();
            if (energia <= 0) {
                borrar = true;
                FXPlayer.EXPLOSION.playEfect();
            }
            blindaje -= disparo.getDanio();
            if (blindaje < 0) {
                blindaje = 0;
            }
        } else {
            if (blindaje > 0)
                blindaje -= disparo.getDanio();
            else {
                energia -= disparo.getDanio();
                if (energia <= 0) {
                    borrar = true;
                    FXPlayer.EXPLOSION.playEfect();
                }
            }

        }
    }

    public int getEnergia() {
        return energia;
    }

    public int getEnergiaInicial() {
        return 1;
    }

    public abstract void update(double p38x, double p38y);

    @Override
    public void update() {
        paso++;
        if (tiempoRecarga != 0) {
            if (paso % tiempoRecarga == 0) {
                this.paraDisparar = true;
            }
        }
        if (paso == Integer.MAX_VALUE - 1)
            paso = 0;

    }

    public void disparar(Vector<ObjetoGrafico> pendientesGraficos) {
        if (tiempoRecarga != 0) {
            if (paso % tiempoRecarga == 0) {
                pendientesGraficos.add(new DisparoEnemigo(positionX, positionY,
                        3 * Math.cos(Math.toRadians(grados + 270)), 3 * Math.sin(Math.toRadians(grados + 270))));
            }
        }
    }
}
