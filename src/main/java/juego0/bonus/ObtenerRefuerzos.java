package juego0.bonus;

import juego0.core.P38;

public class ObtenerRefuerzos extends Bonus {

    public ObtenerRefuerzos() {
        super("images/1984/Refuerzos.png");
    }

    @Override
    public void aplicar(P38 p38) {
        this.setBorrar();
        if (p38.getRefuerzos().isEmpty()) {
            p38.setRefuerzos(1);
        } else {
            p38.setRefuerzos(2);
        }

    }

}
