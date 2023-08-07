package juego0.enemigos;

public class Enemigo1RD extends Enemigo {

    public Enemigo1RD(double x, double y) {
        super("images/1984/Enemigos/Enemigo1.png", x, y);
    }

    @Override
    public void update() {
        super.update();
        moverY(10);
        moverX(2);
        paso++;
        if (paso % 60 == 0)
            paraDisparar = true;
    }
}
