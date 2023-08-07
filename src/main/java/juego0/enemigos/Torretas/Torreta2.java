package juego0.enemigos.Torretas;

public class Torreta2 extends Torreta {
    private boolean I = false, D = false;

    public Torreta2(double positionX, double positionY) {
        super("images/1984/Jefes/Yamato/T2.png", positionX, positionY);
        tiempoRecarga = 90;
    }

    public void update(double p38x, double p38y) {
        super.update();
        if (paso % 3 == 0) {
            grados = (int) Math.toDegrees(Math.atan2((positionY - p38y), (positionX - p38x)));
            grados = grados + 270;
            if (positionX > p38x + 30) {
                I = true;
                D = false;
            } else if (positionX < p38x - 60) {
                I = false;
                D = true;
            } else {
                I = D = false;
            }
            if (I)
                this.setImagen("images/1984/Jefes/Yamato/T2I.png");
            else if (D)
                this.setImagen("images/1984/Jefes/Yamato/T2D.png");
            else
                this.setImagen("images/1984/Jefes/Yamato/T2.png");
        }

    }
}
