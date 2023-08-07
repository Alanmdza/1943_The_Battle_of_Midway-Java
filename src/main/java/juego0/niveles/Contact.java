package juego0.niveles;

import java.awt.Graphics2D;

import juego0.core.ObjetoAuxiliar;

public class Contact extends ObjetoAuxiliar{
    private int paso = 0;
    public Contact() {
        super("images/1984/Contact.png", 50, 120);
    }

    @Override
    public void update() {
        paso++;
        if (paso>200) paso=0;
    }
    @Override
    public void display(Graphics2D g2){
        if (paso<100) super.display(g2);
    }
    
}
