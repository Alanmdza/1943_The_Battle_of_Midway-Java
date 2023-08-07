package juego0.core;

public class Jugador {
    private String nombre;
    private long puntuacion=0;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public long getPuntuacion() {
        return puntuacion;
    }

    public void sumarPuntos(long puntos) {
        puntuacion += puntos;
    }

    public void reiniciar() {
        puntuacion = 0;
    }
}
