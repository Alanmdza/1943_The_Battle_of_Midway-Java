package juego0.enemigos.Torretas;


public class Torreta0 extends Torreta {

    public Torreta0(double positionX, double positionY) {
        super("images/1984/Jefes/Yamato/T0.png", positionX, positionY);
        tiempoRecarga=60;
        energia=80;
    }

    public void update(double p38x, double p38y) {
        super.update();
        if (paso % 3 == 0) {
            grados = (int) Math.toDegrees(Math.atan2((positionY - p38y), (positionX - p38x)));
            grados = grados + 270;
        }
    }

}
