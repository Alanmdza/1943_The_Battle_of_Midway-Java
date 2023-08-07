package juego0.armas;

import juego0.armas.disparos.DisparoBase;
import juego0.core.FXPlayer;

public class ArmaBase extends Arma {

    @Override
    public void disparar(double x, double y) {
        FXPlayer.DISPARO.playEfect();
        switch (rafaga) {
            case 1:
                pendientesGraficos.add(new DisparoBase(false, x, y + 60, danio));
                break;
            case 2:
                pendientesGraficos.add(new DisparoBase(false, x, y + 60, danio));
                pendientesGraficos.add(new DisparoBase(false, x, y, danio));
                break;
            case 3:
                pendientesGraficos.add(new DisparoBase(false, x, y + 60, danio));
                pendientesGraficos.add(new DisparoBase(false, x, y, danio));
                pendientesGraficos.add(new DisparoBase(false, x, y - 60, danio));
                break;
        }
    }

    @Override
    public void mejorar() {
        throw new UnsupportedOperationException("Unimplemented method 'mejorar'");
    }
}
