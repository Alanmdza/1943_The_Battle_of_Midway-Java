/*
 * http://entropyinteractive.com/tutorials/
 */
package entropyinteractive;

import java.awt.*;
import javax.swing.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.image.*;

//import java.util.*;
import java.io.*;

public abstract class Juego extends GameLoop {

    private JFrame frame;
    private JPanel canvas;
    private BufferStrategy buffer;
    private Keyboard keyboard;
    private Mouse mouse;
    private MouseWheel mouseWheel;
    private boolean fullScreenSupported;
    private final GraphicsDevice defaultScreen;
    private boolean bFullScreen;

   //private static int winModeX, winModeY; // top-left corner (x, y)
   // private static int winModeWidth, winModeHeight; // width and height


   // private DisplayMode origDisplayMode;
   // private DisplayMode newDisplayMode;

    /**
     * Creates a new game window.
     *
     * @param title  title of the window.
     * @param width  width of the window.
     * @param height height of the window.
     */
    public Juego(String title, int width, int height) {

    //    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

    //    winModeWidth = (int) dim.getWidth();
    //    winModeHeight = (int) dim.getHeight();
    //    winModeX = 0;
    //    winModeY = 0;
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();

        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader("src/main/resources/configuracion.json")) {
            Object config = parser.parse(fileReader);
            bFullScreen = ((String) (((JSONObject) config).get("Modo de pantalla"))).contains("Pantalla completa");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        this.defaultScreen = env.getDefaultScreenDevice();
        fullScreenSupported = defaultScreen.isFullScreenSupported();
        frame = new JFrame(title);
        ImageIcon im = new ImageIcon("src/main/resources/images/1984/P38.png");
        frame.setIconImage(im.getImage());
        if ((fullScreenSupported) && (bFullScreen)) {
        //    newDisplayMode = new DisplayMode(width, height, 32, DisplayMode.REFRESH_RATE_UNKNOWN);  

            frame.setUndecorated(true);
            defaultScreen.setFullScreenWindow(frame);
            /* Ocultar el puntero del mouse */
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Point hotSpot = new Point(0, 0);
            BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
            Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");
            frame.setCursor(invisibleCursor);
        } else {
            height -= 24;
        }

        frame.setResizable(false);
        canvas = new JPanel();
        canvas.setIgnoreRepaint(true);
        canvas.setSize(width, height);

        frame.setSize(width, height);
        frame.getContentPane().add(canvas, BorderLayout.CENTER);

        frame.getContentPane().setPreferredSize(new Dimension(width, height));

        frame.pack();
        frame.setVisible(true);

        frame.setLocationRelativeTo(null);

        frame.createBufferStrategy(2);
        buffer = frame.getBufferStrategy();

        // create our input classess and add them to the canvas
        keyboard = new Keyboard();
        mouse = new Mouse();
        mouseWheel = new MouseWheel();

        canvas.addKeyListener(keyboard);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        canvas.addMouseWheelListener(mouseWheel);
        canvas.requestFocus();
        if (defaultScreen.isDisplayChangeSupported() && bFullScreen ) {
           // defaultScreen.setDisplayMode(newDisplayMode);
        }
    }

    /**
     * Get the width of the window.
     *
     * @return the width of the window.
     */
    public int getWidth() {
        return frame.getWidth();
    }

    /**
     * Get the height of the window.
     *
     * @return the height of the window.
     */
    public int getHeight() {
        return frame.getHeight();
    }

    /**
     * Returns the title of the window.
     *
     * @return the title of the window.
     */
    public String getTitle() {
        return frame.getTitle();
    }

    /**
     * Returns the keyboard input manager.
     * 
     * @return the keyboard.
     */
    public Keyboard getKeyboard() {
        return keyboard;
    }

    /**
     * Returns the mouse input manager.
     * 
     * @return the mouse.
     */
    public Mouse getMouse() {
        return mouse;
    }

    /**
     * Returns the mouse wheel input manager.
     * 
     * @return the mouse wheel.
     */
    public MouseWheel getMouseWheel() {
        return mouseWheel;
    }

    /**
     * Calls gameStartup()
     */
    public void startup() {
        gameStartup();
    }

    /**
     * Updates the input classes then calls gameUpdate(double).
     * 
     * @param delta time difference between the last two updates.
     */

    public void update(double delta) {
        // call the input updates first
        keyboard.update();
        mouse.update();
        mouseWheel.update();
        // call the abstract update
        gameUpdate(delta);
    }

    /**
     * Calls gameDraw(Graphics2D) using the current Graphics2D.
     */
    public void draw() {
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight());

        g.setColor(Color.RED);
        g.drawRect(0, 0, frame.getWidth() - 1, frame.getHeight() - 1);

        gameDraw(g);

        // show our changes on the canvas
        buffer.show();
        // release the graphics resources
        g.dispose();
    }

    /**
     * Calls gameShutdown()
     */
    public void shutdown() {
        gameShutdown();
        frame.dispose();
    }

    

    public abstract void gameStartup();

    public abstract void gameUpdate(double delta);

    public abstract void gameDraw(Graphics2D g);

    public abstract void gameShutdown();
}