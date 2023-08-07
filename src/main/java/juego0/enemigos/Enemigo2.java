package juego0.enemigos;

public class Enemigo2 extends Enemigo {
    private boolean dispara;
    private int delta;

    public Enemigo2(double x, double y, boolean dispara) {
        super("images/1984/Enemigos/Enemigo2p1.png", x, y);
        delta = 6;
        this.dispara = dispara;
    }

    @Override
    public void update() {
        super.update();
        this.setPosition(positionX, positionY + delta);
        if ((positionY >= 600) && (paso == 0))
            paso = 1;
        switch ((int) paso) {
            case 1:
                setImagen("images/1984/Enemigos/Enemigo2p2.png");
                delta = 2;
                paso++;
                break;
            case 5:
                setImagen("images/1984/Enemigos/Enemigo2p3.png");
                delta = 0;
                paso++;
                if (dispara)
                    paraDisparar = true;
                break;
            case 15:
                setImagen("images/1984/Enemigos/Enemigo2p4.png");
                delta = -6;
                paso++;
                break;
            default:
                if ((paso > 1) && (paso < 30)) {
                    paso++;
                    break;
                }
        }
    }
}
