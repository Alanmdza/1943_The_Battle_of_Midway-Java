package juego0.enemigos;

public class Enemigo4 extends Enemigo {
    private int paso = 0;

    public Enemigo4(double x, double y) {
        super("images/1984/Enemigos/Enemigo4p1.png", x, y);
        energia = 20;
    }

    @Override
    public void update() {
        super.update();
        paso++;
        if (paso == 60) {
            this.setImagen("images/1984/Enemigos/Enemigo4p2.png");
            this.paraDisparar = true;
        }
        moverX(-5);
        positionY = (-(positionX * positionX) + (1761.22 * positionX) - 41633.78) / 2500 + 50;
        
    }
}
