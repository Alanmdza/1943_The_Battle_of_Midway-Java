package juego0.armas.disparos;

public class DisparoEscopeta extends Disparo{
    public DisparoEscopeta(int danio,double positionX, double positionY, double deltax) {
        super("images/1984/DisparoEscopeta.png", positionX, positionY);
        this.delta.setLocation(deltax,-25);
        this.origen.setLocation(positionX, positionY);
        this.alcance=300;
        this.danio=danio;
    }
}
