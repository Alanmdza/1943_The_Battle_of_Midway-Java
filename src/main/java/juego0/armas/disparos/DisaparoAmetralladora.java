package juego0.armas.disparos;

public class DisaparoAmetralladora extends Disparo{
    public DisaparoAmetralladora(int danio,double positionX, double positionY, double deltax) {
        super("images/1984/DisparoAmetralladora.png", positionX, positionY);
        this.delta.setLocation(deltax,-25);
        this.origen.setLocation(positionX, positionY);
        this.alcance=750;
        this.danio=danio;
    }
}
