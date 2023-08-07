package juego0.enemigos.Torretas;


public class Torreta5 extends Torreta {

    public Torreta5(double positionX, double positionY) {
        super("images/1984/Jefes/Tone/T5.png", positionX, positionY);
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
