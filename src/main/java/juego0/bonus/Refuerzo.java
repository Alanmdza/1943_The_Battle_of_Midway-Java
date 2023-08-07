package juego0.bonus;

import juego0.armas.disparos.DisparoBase;
import juego0.core.ObjetoGrafico;

public class Refuerzo extends ObjetoGrafico {
    private int danio;
    private Boolean iz;
    public Refuerzo(boolean iz,int danio, double positionX, double positionY) {
        super("images/1984/NaveAliada.png", positionX, positionY);
        this.iz=iz;
        this.danio=danio;
    }

    @Override
    public void update() {
    }
    public Boolean getIz() {
        return iz;
    }
    public DisparoBase disparar() {
        return new DisparoBase(true,this.getX()+(this.getWidth()/2)-5, this.getY(),danio);
    }
    

}
