package juego0.enemigos;

public class EnemigoGde1 extends Enemigo {
    private int paso = 1;
    private int deltaX = 3;
    private int deltaY = 3;
    private boolean moviendo = true;

    public EnemigoGde1(double x, double y) {
        super("images/1984/Enemigos/EnemigoGde1.png", x, y);
        energia = 50;
        chico = false;
    }

    @Override
    public void update() {
        super.update();
        if (positionX > 30)
            paso++;
        if (paso == 50) {
            moviendo = false;
        } else if (paso >= 90) {
            moviendo = true;
            paso = 1;
            deltaY = -deltaY;
            this.paraDisparar=true;
        }
        if (moviendo) {
            moverX(deltaX);
             if (positionX > 25 && paso > 30)
            moverY(deltaY);
        }
        if (positionX > 420)
            deltaX = -3;
        else if (positionX < 50)
            deltaX = 3;
            

    }
}
