package juego0.armas.disparos;

public class DisparoEnemigo extends Disparo {

    public DisparoEnemigo(double positionX, double positionY,double deltax, double deltay) {
        super("images/1984/DisparoEnemigo.png", positionX, positionY);
        this.delta.setLocation(deltax, deltay);
        this.origen.setLocation(positionX, positionY);
        this.alcance = 750;
    }

}
