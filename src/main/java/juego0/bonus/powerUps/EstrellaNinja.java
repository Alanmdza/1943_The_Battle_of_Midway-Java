package juego0.bonus.powerUps;

import juego0.core.P38;

public class EstrellaNinja extends PowerUp {

    public EstrellaNinja() {
        super("images/1984/Estrella.png");
    }
    
    @Override
    public void aplicar(P38 p38) {
        this.borrar = true;
        p38.recargarEnergia();
    }

}
