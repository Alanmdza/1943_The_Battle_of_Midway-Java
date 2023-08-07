package juego0.niveles;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import entropyinteractive.Keyboard;
import juego0.core.Fondo;
import juego0.core.ObjetoGrafico;

public class Leaderboard extends Nivel {
    private Keyboard keyboard;
    private Vector<ObjetoGrafico> pendientesGraficos;
    private Selector selector = new Selector();
    private Map<String, Integer> tablaHash = new HashMap<>();
    protected int ACEPTAR;
    private static File archivoJSON = new File("src/main/resources/puntuaciones.json");
    private static List<Map.Entry<String, Long>> puntuaciones = new ArrayList<>();
    private static int MAX_PUNTUACIONES = 10;
    private static String[] nombres = new String[MAX_PUNTUACIONES];
    private static String[] puntajes = new String[MAX_PUNTUACIONES];

    public Leaderboard(Keyboard keyboard, Vector<ObjetoGrafico> pendientesGraficos) {
        super();
        this.keyboard = keyboard;
        this.pendientesGraficos = pendientesGraficos;

        tablaHash.put("Enter", KeyEvent.VK_ENTER);
        JSONObject jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader("src/main/resources/configuracion.json")) {
            Object config = parser.parse(fileReader);
            jsonObject = (JSONObject) config;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        ACEPTAR = tablaHash.get(jsonObject.get("Inicia el juego"));
    }

    @Override
    public void run() {
        fondo = new Fondo("images/1984/Leaderboard.png");
        fondo.setFijo();
        selector.moverY(40);
        pendientesGraficos.add(selector);
        leerPuntuacionesTop();
        while (true) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (keyboard.isKeyPressed(ACEPTAR)) {
                estado = -1;
                
            }
        }
    }

    public static void leerPuntuacionesTop() {
        try {
                ObjectMapper objectMapper = new ObjectMapper();
                puntuaciones
                        .addAll(objectMapper.readValue(archivoJSON, new TypeReference<List<Map.Entry<String, Long>>>() {
                        }));
                for (int i = 0; i < 10; i++) {
                    Map.Entry<String, Long> entry = puntuaciones.get(i);
                    nombres[i] = entry.getKey();
                    puntajes[i] = entry.getValue().toString();

                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarPuntajes(Graphics2D g) {
        g.setColor(Color.white);
        g.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
        for (int i = 0; i < 5; i++)
            g.drawString(nombres[i] + " " + puntajes[i], 60, 380 + (65 * i));
        for (int i = 5; i < 10; i++)
            g.drawString(nombres[i] + " " + puntajes[i], 350, 380 + (65 * (i-5)));
    }
}
