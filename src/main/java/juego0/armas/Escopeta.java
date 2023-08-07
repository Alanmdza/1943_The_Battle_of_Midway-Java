package juego0.armas;

import juego0.armas.disparos.DisparoEscopeta;
import juego0.core.FXPlayer;

public class Escopeta extends Arma {
    //La Escopeta dispara 6 proyectiles en cono, al mejorarla sube el danio de cada uno.
    //En el bucle se revisan las interacciones para destruir los proyectiles enemigos
    @Override
    public void disparar(double x, double y) {
        FXPlayer.DISPAROES.playEfect();
        pendientesGraficos.add(new DisparoEscopeta(danio,x-10, y+60, 0));
        pendientesGraficos.add(new DisparoEscopeta(danio,x-10, y+60, -6));
        pendientesGraficos.add(new DisparoEscopeta(danio,x-10, y+60, -20));
        pendientesGraficos.add(new DisparoEscopeta(danio,x+10, y+60, 0));
        pendientesGraficos.add(new DisparoEscopeta(danio,x+10, y+60, +6));
        pendientesGraficos.add(new DisparoEscopeta(danio,x+10, y+60, +20));
    }
    @Override
    public void mejorar() {
        this.danio=20;
    }


}
