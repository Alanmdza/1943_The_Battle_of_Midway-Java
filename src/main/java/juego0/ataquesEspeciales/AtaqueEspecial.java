package juego0.ataquesEspeciales;

import juego0.core.ObjetoGrafico;

public abstract class AtaqueEspecial extends ObjetoGrafico {
    protected int paso=0;
    protected boolean aplicado = false;
    public AtaqueEspecial(String filename, double positionX, double positionY) {
        super(filename, positionX, positionY);
    }
    
}
