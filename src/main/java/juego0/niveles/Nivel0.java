package juego0.niveles;

import java.awt.event.KeyEvent;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import entropyinteractive.Keyboard;
import juego0.core.Fondo;
import juego0.core.ObjetoGrafico;

public class Nivel0 extends Nivel {
    private boolean exit = false;
    private boolean flag;
    private Keyboard keyboard;
    private Vector<ObjetoGrafico> pendientesGraficos;
    private Selector selector = new Selector();
   private Map<String, Integer> tablaHash = new HashMap<>();
    protected int ARRIBA;
    protected int ABAJO;
    protected int ACEPTAR;
    public Nivel0(Keyboard keyboard, Vector<ObjetoGrafico> pendientesGraficos) {
        super();
        this.keyboard = keyboard;
        this.pendientesGraficos = pendientesGraficos;
        tablaHash.put("↑", KeyEvent.VK_UP);
        tablaHash.put("↓", KeyEvent.VK_DOWN);
        tablaHash.put("Enter", KeyEvent.VK_ENTER);
        JSONObject jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader("src/main/resources/configuracion.json")) {
            Object config = parser.parse(fileReader);
            jsonObject = (JSONObject)config;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        ARRIBA=tablaHash.get(jsonObject.get("Arriba"));
        ABAJO=tablaHash.get(jsonObject.get("Abajo"));
        ACEPTAR = tablaHash.get(jsonObject.get("Inicia el juego"));
    }

    @Override
    public void run() {
        fondo = new Fondo("images/1984/Nivel0.png");
        fondo.setFijo();
        pendientesGraficos.add(selector);
        flag = true;
        while (true) {
            try {
                sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (keyboard.isKeyPressed(ABAJO) || (keyboard.isKeyPressed(ARRIBA))) {
                if (flag) {
                    if (exit) {
                        selector.moverY(-42);
                    } else
                        selector.moverY(42);
                    exit = !exit;
                    flag = false;
                }
            } else {
                flag = true;
            }
            if (keyboard.isKeyPressed(ACEPTAR)) {
                if (exit)
                    estado = -1;
                else
                    estado = 2;
            }
        }
    }
}
