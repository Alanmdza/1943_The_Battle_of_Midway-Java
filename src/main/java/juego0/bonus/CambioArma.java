package juego0.bonus;

import java.util.Random;
import juego0.armas.*;
import juego0.core.P38;


public class CambioArma extends Bonus {

    private int arma;

    public CambioArma() {
        super("images/1984/DisparoEscopeta.png");
        Random random = new Random();
        arma = random.nextInt(3) + 1;
        if (arma == 2)
            this.setImagen("images/1984/DisparoAmetralladora.png");
        else if (arma == 3)
            this.setImagen("images/1984/LaserMini.png");
    }

    //Le otorga al p38 el arma nueva, y si corresponde se la da mejorada
    @Override
    public void aplicar(P38 p38) {
        Arma armaActual = p38.getArma();
        super.aplicar(p38);
        switch (arma) {
            case 1:
                p38.setArma(new Escopeta());
                if (armaActual instanceof Escopeta)
                    p38.getArma().mejorar();
                break;
            case 2:
                p38.setArma(new Ametralladora());
                if (armaActual instanceof Ametralladora)
                    p38.getArma().mejorar();
                break;
            case 3:
                p38.setArma(new Laser());
                if (armaActual instanceof Laser)
                    p38.getArma().mejorar();
                break;
        }
        p38.getArma().setPendienteGraficos(p38.getPendientesGraficos());
    }

}
