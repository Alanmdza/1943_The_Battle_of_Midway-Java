package juego0.enemigos;

public class EnemigoGde2 extends Enemigo {
    private int paso = 1;
    private int deltaY = -3;
    private boolean moviendo = true;
    private double puntoR;

    public EnemigoGde2(double x, double y) {
        super("images/1984/Enemigos/EnemigoGde2.png", x, y);
        energia = 50;
        chico = false;
        puntoR=x;
    }

    @Override
    public void update() {
        super.update();
        if (positionY < 750)
            paso++;
        if (paso == 50) {
            moviendo = false;
        } else if (paso >= 90) {
            moviendo = true;
            paso = 1;
            this.paraDisparar=true;
        }
        if (moviendo) {
            moverY(deltaY);
            positionX= puntoR+40*Math.sin(positionY/30);
        }
            

    }
}
