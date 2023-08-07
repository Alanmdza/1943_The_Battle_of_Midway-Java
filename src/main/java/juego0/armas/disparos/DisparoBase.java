package juego0.armas.disparos;

public class DisparoBase extends Disparo {

    public DisparoBase(Boolean aliado,double positionX, double positionY,int danio) {
        super( "images/1984/bala_simple.png", positionX, positionY);
        if (aliado) setImagen("images/1984/DisparoAliado.png");
        this.delta.setLocation(0, -25);
        this.origen.setLocation(positionX, positionY);
        this.alcance=750;
        this.danio=danio;
    }
    
}
