package juego0.core;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import javax.imageio.ImageIO;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import entropyinteractive.Keyboard;
import juego0.armas.*;
import juego0.ataquesEspeciales.*;
import juego0.bonus.Refuerzo;

public class P38 extends ObjetoGrafico {
    private boolean aproximado = false;
    private int energia = 100, asceleracion = 0,shell = 0, XZ = 0;
    private Keyboard keyboard;
    private boolean interrumpirdisparo = false, interrumpirdisparo2 = false, interrumpirataque = false;
    private Arma arma = new ArmaBase();
    private Date dAhora = new Date(), dDanio, dUltimoBonus, dUltimoDisparo = new Date();
    private BufferedImage p38Invulnerable = null, P382I = null, P381I = null, P382D = null, P381D = null, P382II = null,
            P381II = null, P382DI = null, P381DI = null, P382IG = null,
            P381IG = null, P382DG = null, P381DG = null, P38G = null;
    private Vector<ObjetoGrafico> pendientesGraficos;
    private Vector<Refuerzo> refuerzos = new Vector<>();
    private Map<String, Integer> tablaHash = new HashMap<>();
    private final int ARRIBA, IZQUIERDA, DERECHA, ABAJO, DISPARO, ESPECIAL;

    public P38(Keyboard keyboard, Vector<ObjetoGrafico> pendientesGraficos) {
        super("images/1984/P38.png", 275, 700);
        this.keyboard = keyboard;
        this.pendientesGraficos = pendientesGraficos;
        arma.setPendienteGraficos(pendientesGraficos);
        try {
            p38Invulnerable = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P38I.png"));
            P382I = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P382I.png"));
            P381I = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P381I.png"));
            P381D = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P381D.png"));
            P382D = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P382D.png"));
            P382II = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P382II.png"));
            P381II = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P381II.png"));
            P381DI = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P381DI.png"));
            P382DI = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P382DI.png"));
            P382IG = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P382IG.png"));
            P381IG = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P381IG.png"));
            P381DG = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P381DG.png"));
            P382DG = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P382DG.png"));
            P38G = ImageIO
                    .read(getClass().getClassLoader().getResourceAsStream("images/1984/P38G.png"));

        } catch (IOException e) {
            System.out.println(e);
        }
        JSONObject jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader("src/main/resources/configuracion.json")) {
            Object config = parser.parse(fileReader);
            jsonObject = (JSONObject) config;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        tablaHash.put("↑", KeyEvent.VK_UP);
        tablaHash.put("↓", KeyEvent.VK_DOWN);
        tablaHash.put("→", KeyEvent.VK_RIGHT);
        tablaHash.put("←", KeyEvent.VK_LEFT);
        tablaHash.put("X", KeyEvent.VK_X);
        tablaHash.put("Z", KeyEvent.VK_Z);
        ARRIBA = tablaHash.get(jsonObject.get("Arriba"));
        ABAJO = tablaHash.get(jsonObject.get("Abajo"));
        IZQUIERDA = tablaHash.get(jsonObject.get("Izquierda"));
        DERECHA = tablaHash.get(jsonObject.get("Derecha"));
        DISPARO = tablaHash.get(jsonObject.get("Disparo"));
        ESPECIAL = tablaHash.get(jsonObject.get("Ataque Especial"));
    }

    @Override
    public void update() {
        if (XZ > 0)
            girar();
        //energia = 100;
        if (keyboard.isKeyPressed(ARRIBA) && positionY > 30 && XZ == 0)
            positionY -= 4;
        if (keyboard.isKeyPressed(ABAJO) && positionY < 756 && XZ == 0)
            positionY += 4;
        if (keyboard.isKeyPressed(IZQUIERDA) && (positionX > 6) && XZ == 0) {
            positionX -= 4;
            if (asceleracion>-40) asceleracion -= 2;
        }
        if (keyboard.isKeyPressed(DERECHA) && (positionX < 536) && XZ == 0) {
            positionX += 4;
            if (asceleracion< 40)asceleracion += 2;
        }
        if (!keyboard.isKeyPressed(DERECHA) && !keyboard.isKeyPressed(IZQUIERDA)) {
            if (asceleracion < 0)
                asceleracion += 2;
            else if (asceleracion > 0)
                asceleracion -= 2;
        }
        if (XZ == 0) {
            if (asceleracion < 0)
                asceleracion++;
            else if (asceleracion > 0)
                asceleracion--;
        }

        if (keyboard.isKeyPressed(ESPECIAL) && keyboard.isKeyPressed(DISPARO) && XZ == 0) {
            XZ = 1;
        }

        if (keyboard.isKeyPressed(ESPECIAL) && energia > 35 && XZ == 0) {
            if (!interrumpirataque) {
                if (!aproximado) {
                    pendientesGraficos.add(new Relampago());
                    FXPlayer.RELAMPAGO.playEfect();
                } else {
                    pendientesGraficos.add(new Tsunami());
                    FXPlayer.TSUNAMI.playEfect();
                }

                interrumpirataque = true;
                energia -= 35;
            }
        } else
            interrumpirataque = false;
        if (keyboard.isKeyPressed(DISPARO)) {
            switch (shell) {
                case 0:
                    if (!interrumpirdisparo) {
                        arma.disparar(positionX + 23, positionY - (this.getHeight()));
                        interrumpirdisparo = true;
                    }
                    break;
                case 1:
                    if (dAhora.getTime() - dUltimoDisparo.getTime() > 200) {
                        arma.disparar(positionX + 23, positionY - (this.getHeight()));
                        dUltimoDisparo = new Date();
                    }
                    break;
                case 2:
                    if (dAhora.getTime() - dUltimoDisparo.getTime() > 100) {
                        arma.disparar(positionX + 23, positionY - (this.getHeight()));
                        dUltimoDisparo = new Date();
                    }
                    break;
            }
            if (!interrumpirdisparo2) {
                for (Refuerzo refuerzo : refuerzos) {
                    pendientesGraficos.add(refuerzo.disparar());
                }
                interrumpirdisparo2 = true;
            }
        } else {
            interrumpirdisparo = false;
            interrumpirdisparo2 = false;
        }
        if (dUltimoBonus != null) {
            if ((dAhora.getTime() - dUltimoBonus.getTime()) / 1000 % 60 > 6) {
                eliminarBonus();
            }
        }
        for (Refuerzo refuerzo : refuerzos) {
            if (refuerzo.getIz())
                refuerzo.setPosition(positionX - 50, positionY + 10);
            else
                refuerzo.setPosition(positionX + 82, positionY + 10);
        }
    }

    private void girar() {
        XZ++;
        if (asceleracion >= 300) {
            asceleracion = -60;
        }
        if (asceleracion < 0 && XZ > 100) {
            asceleracion += 1;
        } else
            asceleracion += 3;
        if (asceleracion >= 0 && XZ > 230)
            XZ = 0;
    }

    public int getEnergia() {
        return energia;
    }

    public void recibirDanio(int danio) {
        dDanio = new Date();
        energia -= danio;
        if (energia < 0)
            energia = 0;
    }

    public void recargarEnergia() {
        energia = 100;
    }

    public void recargarEnergia(int a) {
        energia += a;
    }

    public boolean invulnerable() {
        if (dDanio != null) {
            return (dAhora.getTime() - dDanio.getTime()) / 1000 % 60 < 1;
        } else
            return false;
    }

    public void setDate() {
        dAhora = new Date();
    }

    public void setdUltimoBonus(Date dUltimoBonus) {
        this.dUltimoBonus = dUltimoBonus;
    }

    public void eliminarBonus() {
        arma = new ArmaBase();
        arma.setPendienteGraficos(pendientesGraficos);
        dUltimoBonus = null;
        shell = 0;
    }

    public Arma getArma() {
        return arma;
    }

    public void setArma(Arma arma) {
        this.arma = arma;
    }

    public void setShell(int shell) {
        this.shell = shell;
    }

    public int getShell() {
        return shell;
    }

    public Vector<ObjetoGrafico> getPendientesGraficos() {
        return pendientesGraficos;
    }

    @Override
    public void display(Graphics2D g2) {
        if (asceleracion < 50) {
            if (invulnerable()) {
                if (asceleracion == 0)
                    g2.drawImage(p38Invulnerable, (int) this.positionX, (int) this.positionY, null);
                else if (asceleracion < -30)
                    g2.drawImage(P382II, (int) this.positionX, (int) this.positionY, null);
                else if (asceleracion < 0)
                    g2.drawImage(P381II, (int) this.positionX, (int) this.positionY, null);
                else if (asceleracion > 30)
                    g2.drawImage(P382DI, (int) this.positionX, (int) this.positionY, null);
                else if (asceleracion > 0)
                    g2.drawImage(P381DI, (int) this.positionX, (int) this.positionY, null);
            } else {
                if (asceleracion == 0)
                    g2.drawImage(imagen, (int) this.positionX, (int) this.positionY, null);
                else if (asceleracion < -30)
                    g2.drawImage(P382I, (int) this.positionX, (int) this.positionY, null);
                else if (asceleracion < 0)
                    g2.drawImage(P381I, (int) this.positionX, (int) this.positionY, null);
                else if (asceleracion > 30)
                    g2.drawImage(P382D, (int) this.positionX, (int) this.positionY, null);
                else if (asceleracion > 0)
                    g2.drawImage(P381D, (int) this.positionX, (int) this.positionY, null);

            }
        } else {
            if (asceleracion > 250)
                g2.drawImage(P382IG, (int) this.positionX, (int) this.positionY, null);
            else if (asceleracion > 200)
                g2.drawImage(P381IG, (int) this.positionX, (int) this.positionY, null);
            else if (asceleracion > 150)
                g2.drawImage(P38G, (int) this.positionX, (int) this.positionY, null);
            else if (asceleracion > 100)
                g2.drawImage(P381DG, (int) this.positionX, (int) this.positionY, null);
            else if (asceleracion > 50)
                g2.drawImage(P382DG, (int) this.positionX, (int) this.positionY, null);

        }
    }

    public void setRefuerzos(int danio) {
        refuerzos.clear();
        refuerzos.add(new Refuerzo(true, 1, this.getX() - 50, this.getY() + 10));
        refuerzos.add(new Refuerzo(false, 1, this.getX() + 82, this.getY() + 10));
    }

    public Vector<Refuerzo> getRefuerzos() {
        return refuerzos;

    }

    public boolean Girando() {
        return XZ != 0;
    }

    public void aproximar() {
        aproximado = true;
    }

}
