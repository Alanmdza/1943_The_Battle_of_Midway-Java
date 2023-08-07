package juego0.bonus;

import juego0.armas.disparos.Disparo;
import juego0.core.*;
import java.util.*;


public abstract class Bonus extends ObjetoGrafico implements Hiteable {
    private int count;
    public Bonus(String filename) {
        super(filename, 0, 0);
        Random random = new Random();
        this.moverX(random.nextInt(400) + 100);
        //Generamos el bonus en un punto x random
    }

    public void update() {
        this.setPosition(positionX, positionY + 2);
        if (count>=3) this.borrar = true;
    }

    public void aplicar(P38 p38) {
        p38.eliminarBonus();
        this.borrar = true;
        p38.setdUltimoBonus(new Date());
        //Sacamos el bonus antes de aplicar uno nuevo y guardamos el tiempo para saber cuando quitarlo
    }
    public void recibirDisparo(Disparo disparo){
        count++;
        this.moverY(-20);
        if (count>=3){
            disparo.setBorrar();
        }
        //Depues de 3 disparos se cambia de bonus. Se realiza en el bucle
    }
    public int getCount(){
        return count;
    }

}

