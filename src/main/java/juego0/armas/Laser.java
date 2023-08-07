package juego0.armas;


import juego0.armas.disparos.DisparoLaser;
import juego0.core.FXPlayer;

public class Laser extends Arma {
    public Laser() {
        danio = 30;
    }

    @Override
    public void disparar(double x, double y) {
        FXPlayer.DISPAROLA.playEfect();
        pendientesGraficos.add(new DisparoLaser(danio,x, y));
    }

    @Override
    public void mejorar() {
        this.danio=30;;
    }


}
