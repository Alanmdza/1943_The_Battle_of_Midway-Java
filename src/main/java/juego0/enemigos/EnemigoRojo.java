package juego0.enemigos;

public class EnemigoRojo extends Enemigo {
    private static int cantidad = -1;

    public EnemigoRojo(double x, double y) {
        super("images/1984/Enemigos/EnemigoRojo.png", x, y);
        if (cantidad == -1)
            cantidad = 1;
        else
            cantidad = cantidad + 1;
    }

    @Override
    public void update() {
        super.update();
        if (this.origen.distance(positionX, positionY) > alcance) {
            borrar = true;
        }
        if (paso <= 0)
            moverX(4);
        if ((paso > 0 || positionX > 200) && paso!=-1) {
            paso++;
            positionX = positionX + 4 * (Math.cos(Math.toDegrees((paso) / 20)));
            positionY = positionY + 4 * (Math.sin(Math.toDegrees((paso) / 20)));
            if (Math.toDegrees((paso-30)/20) >= 360)
            paso = -1;
        }
        if (this.origen.distance(positionX, positionY) > alcance) {
            borrar = true;
        }
    }

    public static int getCantidad() {
        return cantidad;
    }

    public static void reducir() {
        cantidad = cantidad - 1;
    }
}
