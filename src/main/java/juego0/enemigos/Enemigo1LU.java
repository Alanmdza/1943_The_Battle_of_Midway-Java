package juego0.enemigos;

public class Enemigo1LU extends Enemigo {

    public Enemigo1LU(double x, double y) {
        super("images/1984/Enemigos/Enemigo1Arriba.png", x, y);
    }

    @Override
    public void update() {
        super.update();
        moverY(-10);
        moverX(-2);
        paso++;
        if (paso % 60 == 0)
            paraDisparar = true;
    }
}
