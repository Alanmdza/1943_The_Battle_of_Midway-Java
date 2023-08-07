/*
 * http://entropyinteractive.com/tutorials/
 */
package entropyinteractive;

import java.awt.*;

import java.awt.image.*;

/**
 * Game that creates a window and handles input.
 * @author Eric
 */
public abstract class Game extends GameLoop {
    private Frame frame;
    private Canvas canvas;
    private BufferStrategy buffer;

    private static Keyboard keyboard;
    private static Mouse mouse;
    private static MouseWheel mouseWheel;

    /**
     * Creates a new game window.
     *
     * @param title title of the window.
     * @param width width of the window.
     * @param height height of the window.
     */
    public Game(String title, int width, int height) {
       // Log.debug("Game", "Creating game " + title + " (" + width + ", " + height + ")");

        // create frame and canvas
        frame = new Frame(title);
        frame.setResizable(false);
        canvas = new Canvas();
        canvas.setIgnoreRepaint(true);
        frame.add(canvas);
        // resize canvas and make the window visible
        canvas.setSize(width, height);


        frame.pack();
        frame.setLocationRelativeTo(null);  // *** this will center your app ***
        frame.setVisible(true);

        // create buffer strategy
        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();

        // create our input classess and add them to the canvas
        keyboard = new Keyboard();
        mouse = new Mouse();
        mouseWheel = new MouseWheel();

        canvas.addKeyListener(keyboard);
        canvas.addMouseListener(mouse);
        canvas.addMouseMotionListener(mouse);
        canvas.addMouseWheelListener(mouseWheel);
        canvas.requestFocus();
    }

    /**
     * Get the width of the window.
     *
     * @return the width of the window.
     */
    public int getWidth() {
        return canvas.getWidth();
    }

    /**
     * Get the height of the window.
     *
     * @return the height of the window.
     */
    public int getHeight() {
        return canvas.getHeight();
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
     * @return the keyboard.
     */
    public static  Keyboard getKeyboard() {
        return keyboard;
    }

    /**
     * Returns the mouse input manager.
     * @return the mouse.
     */
    public static Mouse getMouse() {
        return mouse;
    }

    /**
     * Returns the mouse wheel input manager.
     * @return the mouse wheel.
     */
    public static  MouseWheel getMouseWheel() {
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
        // get the current graphics object
        Graphics2D g = (Graphics2D) buffer.getDrawGraphics();

    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        // clear the window
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        // send the graphics object to gameDraw() for our main drawing

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