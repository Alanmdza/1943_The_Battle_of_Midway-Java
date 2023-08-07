package launcher;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Panelwest extends JPanel implements ActionListener {

    static public JButton boton_juego[];

    public Panelwest() {
        this.setPreferredSize(new Dimension(170, 800));
        this.setBackground(new Color(34, 34, 34));
        Border borde = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK);
        Border bordeCompuesto = BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), borde);
        this.setBorder(bordeCompuesto);
        this.setLayout(new GridLayout(10, 1));
        boton_juego = new JButton[4];
        for (int i = 0; i < 4; i++) {
            boton_juego[i] = new JButton(new ImageIcon("src/main/resources/images/launcher/juego" + (i) + ".png"));
            boton_juego[i].setName("juego" + (i));
            boton_juego[i].setBorderPainted(false);
            boton_juego[i].setContentAreaFilled(false);
            boton_juego[i].setFocusPainted(false);
            this.add(boton_juego[i]);
            boton_juego[i].addActionListener(this);
            boton_juego[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

        }
    }

    public void actionPerformed(ActionEvent evt) {
        for (int i = 0; i < 4; i++) {
            if (evt.getSource().equals(boton_juego[i])) {
                Panelcenter.cardLayout.show(Panelcenter.panelcenter, new String("carta" + i));
            }

        }

    }
}
