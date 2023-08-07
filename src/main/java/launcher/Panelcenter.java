package launcher;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import juego0.core.J1943;

public class Panelcenter extends JPanel implements ActionListener {
    J1943 juego0;
    Thread t;
    static Panelcenter panelcenter;
    static CardLayout cardLayout;
    static JPanel panel[];
    JButton boton1[], boton2[];

    public Panelcenter() {

        panel = new JPanel[4];
        boton1 = new JButton[4];
        boton2 = new JButton[4];
        panelcenter = this;
        cardLayout = new CardLayout();
        this.setLayout(cardLayout);
        this.setPreferredSize(new Dimension(630, 800));
        for (int i = 0; i < 4; i++) {
            panel[i] = new JPanel();
            panel[i].setLayout(new BorderLayout());
            JPanel centro = new JPanel(new GridLayout(1, 1));
            JPanel abajo = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
            centro.setBackground(new Color((34), (34), (34)));
            abajo.setBackground(new Color((34), (34), (34)));
            centro.setPreferredSize(new Dimension(630, 700));
            abajo.setPreferredSize(new Dimension(630, 100));
            panel[i].add(BorderLayout.CENTER, centro);
            panel[i].add(BorderLayout.SOUTH, abajo);
            ImageIcon imageIcon = new ImageIcon("src/main/resources/images/launcher/juego_grande" + (i) + ".png");
            Image image = imageIcon.getImage().getScaledInstance(-1, 470, Image.SCALE_SMOOTH);
            ImageIcon scaledImageIcon = new ImageIcon(image);
            centro.add(new JLabel(scaledImageIcon));
            boton1[i] = new JButton("Jugar");
            boton2[i] = new JButton("Configuración");
            boton1[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            boton1[i].setAlignmentY(Component.CENTER_ALIGNMENT);
            boton1[i].setBackground(new Color((34), (34), (34)));
            boton1[i].setForeground(new Color((200), (200), (200)));
            boton1[i].setPreferredSize(new Dimension(180, 60));
            boton2[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            boton2[i].setAlignmentY(Component.CENTER_ALIGNMENT);
            boton2[i].setBackground(new Color((34), (34), (34)));
            boton2[i].setForeground(new Color((200), (200), (200)));
            boton2[i].setPreferredSize(new Dimension(180, 60));
            boton1[i].addActionListener(this);
            boton2[i].addActionListener(this);
            abajo.add(boton1[i]);
            abajo.add(boton2[i]);
            this.add(panel[i], new String("carta" + i));
        }

    }

    public void actionPerformed(ActionEvent evt) {
            if (evt.getSource().equals(boton1[0])) {
                String nombre = JOptionPane.showInputDialog(Panelcenter.this, "Ingresa tu nombre:");
                juego0 = new J1943(nombre);
						t = new Thread() {
						    public void run() {
						        juego0.run(1.0 / 60.0);
						    }
						};

						t.start();
            }
            if (evt.getSource().equals(boton2[0])) {
                Menu1943 men = new Menu1943();
                JDialog d = new JDialog(Ventana.ventana, "Configuración", true);
                ImageIcon im = new ImageIcon("src/main/resources/images/launcher/icono.png");
                d.setIconImage(im.getImage());
                d.add(men);
                d.setPreferredSize(new Dimension(800, 700));
                d.pack();
                d.setLocationRelativeTo(null);
                d.setModal(true);
                d.setVisible(true);
                
            }


    }
}
