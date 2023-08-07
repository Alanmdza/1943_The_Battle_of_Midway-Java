package juego0.enemigos;

public class Enemigo3 extends Enemigo {
    int paso = 0;

    public Enemigo3(double x, double y) {
        super("images/1984/Enemigos/Enemigo3.png", x, y);
        energia = 20;
    }

    @Override
    public void update() {
        super.update();
        this.setPosition(positionX + 5, 600 - ((-((positionX - 300) * (positionX - 300)) + 50000) / 500));
        if (positionX == 200)
            paraDisparar = true;
    }
}
