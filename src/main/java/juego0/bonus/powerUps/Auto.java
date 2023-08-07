package juego0.bonus.powerUps;

import juego0.core.P38;

public class Auto extends PowerUp {

    public Auto() {
        super("images/1984/Auto.png");
    }
    //Modifica la cantidad de disparos que suelta el p38 en un solo ataque
    @Override
    public void aplicar(P38 p38) {
        if (p38.getArma().getRafaga()==1) {
            super.aplicar(p38);
            p38.getArma().setRafaga(2);
        } else {
            super.aplicar(p38);
            p38.getArma().setRafaga(3);
        }

    }

}
