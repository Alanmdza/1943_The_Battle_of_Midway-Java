package launcher;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.*;

public class Menu1943 extends JPanel implements ActionListener {
    static private ArrayList<JComboBox<String>> comboBox;
    JButton button1, button2;

    public Menu1943() {

        this.setBackground(new Color(34, 34, 34));
        button1 = new JButton("Guardar");
        button1.addActionListener(this);
        button1.setBackground(new Color((34), (34), (34)));
        button1.setForeground(new Color((200), (200), (200)));
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button2 = new JButton("Reset");
        button2.setBackground(new Color((34), (34), (34)));
        button2.setForeground(new Color((200), (200), (200)));
        button2.addActionListener(this);
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR));

        comboBox = new ArrayList<JComboBox<String>>();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        GridBagConstraints d = new GridBagConstraints();
        GridBagConstraints e = new GridBagConstraints();
        c.anchor = GridBagConstraints.LINE_START;
        c.insets.set(5, 5, 5, 5);
        d.anchor = GridBagConstraints.LINE_START;
        d.insets.set(5, 5, 5, 5);
        e.gridy = 4;
        e.gridheight = 10;
        e.anchor = GridBagConstraints.LINE_START;
        e.gridx = 0;

        for (int i = 0; i < 16; i++) {
            c.gridy = i;
            d.gridy = i;
            if (i == 5) {
                JLabel aux = new JLabel("Definicion de teclas");
                aux.setFont(new Font("Arial", Font.PLAIN, 12));
                aux.setForeground(new Color(200, 200, 200));
                this.add(aux, e);
            }
            if (i < 4) {
                JLabel aux = new JLabel(label[i]);
                aux.setFont(new Font("Arial", Font.PLAIN, 12));
                aux.setForeground(new Color(200, 200, 200));
                c.gridx = 0;
                d.gridx = 1;
                this.add(aux, c);
                comboBox.add(new JComboBox<String>(opcion[i]));
                comboBox.get(i).setFont(new Font("Arial", Font.PLAIN, 12));
                this.add(comboBox.get(i), d);
            } else if (i >= 4 && i < 14) {
                c.gridx = 1;
                d.gridx = 2;
                JLabel aux = new JLabel(label[i]);
                aux.setFont(new Font("Arial", Font.PLAIN, 12));
                aux.setForeground(new Color(200, 200, 200));
                this.add(aux, c);
                comboBox.add(new JComboBox<String>(opcion[4]));
                comboBox.get(i).setFont(new Font("Arial", Font.PLAIN, 12));
                this.add(comboBox.get(i), d);
            } else {
                c.gridx = 0;
                d.gridx = 1;
                JLabel aux = new JLabel(label[i]);
                aux.setFont(new Font("Arial", Font.PLAIN, 12));
                aux.setForeground(new Color(200, 200, 200));
                this.add(aux, c);
                comboBox.add(new JComboBox<String>(opcion[5]));
                comboBox.get(i).setFont(new Font("Arial", Font.PLAIN, 12));
                this.add(comboBox.get(i), d);
            }
        }

        c.gridx = 3;
        d.gridx = 4;
        c.gridy = d.gridy = 18;
        this.add(button1, c);
        this.add(button2, d);
        JSONObject config = loadConfig();
        for (int i = 0; i < 16; i++) {
            comboBox.get(i).setSelectedItem(config.get(label[i]));
        }

    }

    void setDefault() {
        for (int i = 0; i < 16; i++) {
            if (i >= 4 && i < 14) {
                comboBox.get(i).setSelectedIndex(i - 4);
            } else {
                comboBox.get(i).setSelectedIndex(0);
            }
        }
    }
    @SuppressWarnings("unchecked")
    private static void saveConfig() {
        JSONObject configJson = new JSONObject();
        for (int i = 0; i < 16; i++) {
            configJson.put(label[i], comboBox.get(i).getSelectedItem());
        }
        try (FileWriter fileWriter = new FileWriter("src/main/resources/configuracion.json")) {
            fileWriter.write(configJson.toJSONString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject loadConfig() {
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader("src/main/resources/configuracion.json")) {
            Object obj = parser.parse(fileReader);
            return (JSONObject) obj;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void actionPerformed(ActionEvent evt) {
        if (evt.getActionCommand() == button1.getActionCommand()) {
            saveConfig();
        }
        if (evt.getActionCommand() == button2.getActionCommand()) {
            this.setDefault();
        }
    }

    public static String[][] opcion = { { "Ventana", "Pantalla completa" },
            { "Activado", "Desactivado" },
            { "Activado", "Desactivado" },
            { "Activado", "Desactivado" },
            { "Q",
                    "W",
                    "Barra espaciadora",
                    "←",
                    "→",
                    "↑",
                    "↓",
                    "X",
                    "Z",
                    "Enter" },
            { "Original" }
    };

    public static String[] label = {
            "Modo de pantalla",
            "Sonido General",
            "Efectos de sonido",
            "Musica de fondo",
            "Activar y Desactivar efectos de sonido", "Activar y Desactivar musica de fondo", "Pausar y Reanudar juego",
            "Izquierda", "Derecha", "Arriba", "Abajo", "Disparo", "Ataque Especial", "Inicia el juego",
            "Seleccion de pista musical",
            "Seleccion de personaje",
            "Modo de pantalla",
    };
}