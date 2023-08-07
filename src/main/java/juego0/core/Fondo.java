package juego0.core;

public class Fondo extends ObjetoGrafico {
	private boolean mover = true;
	public Fondo(String filename) {
		super(filename, 0, 0);
	}

	@Override
	public void update() {
		positionY+=0.3;
	}
	public boolean movible(){
		return mover;
	}
	public void setFijo() {
		mover=false;
	}

}