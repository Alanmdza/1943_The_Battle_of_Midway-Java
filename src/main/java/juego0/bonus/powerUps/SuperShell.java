package juego0.bonus.powerUps;

import juego0.core.P38;

public class SuperShell extends PowerUp {

    public SuperShell() {
        super("images/1984/SuperShell.png");
    }

    @Override
    public void aplicar(P38 p38) {
        if (p38.getShell() == 0) {
            super.aplicar(p38);
            p38.setShell(1);
        } else {
            super.aplicar(p38);
            p38.setShell(2);
        }

    }

}
