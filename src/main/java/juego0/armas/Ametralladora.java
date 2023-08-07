package juego0.armas;

import juego0.armas.disparos.DisaparoAmetralladora;
import juego0.core.FXPlayer;

public class Ametralladora extends Arma {
    // La ametralladora dispara 3 proyectiles en cono, al mejorarla sube el danio de
    // cada uno
    @Override
    public void disparar(double x, double y) {
        FXPlayer.DISPAROAM.playEfect();
        pendientesGraficos.add(new DisaparoAmetralladora(danio, x + 12, y + 60, 0));
        pendientesGraficos.add(new DisaparoAmetralladora(danio, x - 8, y + 60, -7));
        pendientesGraficos.add(new DisaparoAmetralladora(danio, x + 32, y + 60, 7));
    }

    @Override
    public void mejorar() {
        this.danio = 20;
    }

}
