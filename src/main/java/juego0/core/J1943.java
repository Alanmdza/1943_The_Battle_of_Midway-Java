package juego0.core;

import entropyinteractive.*;

import juego0.armas.Explosion;
import juego0.armas.disparos.*;
import juego0.ataquesEspeciales.*;
import juego0.bonus.*;
import juego0.bonus.powerUps.*;
import juego0.enemigos.*;
import juego0.enemigos.Torretas.Torreta;
import juego0.niveles.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class J1943 extends Juego {

    private Date dInit = new Date(), dAhora = new Date(), dPausado;
    private long[] diffSeconds = { 0 };
    private long[] diffMinutes = { 0 };
    private long dateDiff, tiempoPausado = 0, acumPause = 0, ultimoPuntaje = 0, puntajeMasAlto = 0;
    private Vector<ObjetoGrafico> objetosGraficos = new Vector<>(), pendientesGraficos = new Vector<>(),
            limpiezGraficos = new Vector<>();
    private Vector<Explosion> explosiones = new Vector<>();
    private ObjetoAuxiliar barra, E, E1, E2, E3, player, highscore, Puntos[], HPuntos[];
    private Keyboard keyboard = this.getKeyboard();
    private P38 p38;
    private Nivel nivelactual;
    private GeneradorBonus generadorBonus;
    private int indexNivel = 0;
    private boolean pause = false, flag = false, flag2 = false, flag3 = false, hayTsunami = false, skip = false;
    private final boolean SONIDOG[] = new boolean[1], SONIDOE[] = new boolean[1], SONIDOM[] = new boolean[1];
    private Map<String, Integer> tablaHash = new HashMap<>();
    private int MUSICA, PAUSAR, EFECTOSDESONIDO;
    private Jugador jugador;
    private FXPlayer track;

    public static void main(String[] args) {
        J1943 juego = new J1943("prueba");
        juego.run(1.0 / 60.0);
        System.exit(0);
    }

    public J1943(String nombre) {
        super("1943: The Battle of Midway", 600, 800);
        player = new ObjetoAuxiliar("images/1984/1P.png", 30, 50);
        highscore = new ObjetoAuxiliar("images/1984/HS.png", 220, 50);
        jugador = new Jugador(nombre);
        tablaHash.put("Q", KeyEvent.VK_Q);
        tablaHash.put("W", KeyEvent.VK_W);
        tablaHash.put("Barra espaciadora", KeyEvent.VK_SPACE);
        tablaHash.put("Enter", KeyEvent.VK_ENTER);
        JSONObject jsonObject = new JSONObject();
        JSONParser parser = new JSONParser();
        try (FileReader fileReader = new FileReader("src/main/resources/configuracion.json")) {
            Object config = parser.parse(fileReader);
            jsonObject = (JSONObject) config;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        MUSICA = tablaHash.get(jsonObject.get("Activar y Desactivar musica de fondo"));
        EFECTOSDESONIDO = tablaHash.get(jsonObject.get("Activar y Desactivar efectos de sonido"));
        SONIDOG[0] = ((String) (jsonObject.get("Sonido General"))).contains("Activado");
        SONIDOE[0] = ((String) (jsonObject.get("Efectos de sonido"))).contains("Activado");
        SONIDOM[0] = ((String) (jsonObject.get("Musica de fondo"))).contains("Activado");
        PAUSAR = tablaHash.get(jsonObject.get("Pausar y Reanudar juego"));
        FXPlayer.init(SONIDOG, SONIDOE, SONIDOM);
    }

    public void gameStartup() {
        p38 = new P38(keyboard, pendientesGraficos);
        nivelactual = new Nivel0(keyboard, pendientesGraficos);
        nivelactual.start();
        track = FXPlayer.TRACK0;
        if (SONIDOM[0])
            track.playMusic();
        generadorBonus = new GeneradorBonus(pendientesGraficos, diffSeconds);
        generadorBonus.start();
        barra = new ObjetoAuxiliar("images/1984/vida/100.png", 40, 760);
        E = new ObjetoAuxiliar("images/1984/Numeros/E.png", 470, 758);
        E1 = new ObjetoAuxiliar("images/1984/Numeros/1.png", 500, 760);
        E2 = new ObjetoAuxiliar("images/1984/Numeros/0.png", 520, 760);
        E3 = new ObjetoAuxiliar("images/1984/Numeros/0.png", 540, 760);
        Puntos = new ObjetoAuxiliar[6];
        for (int i = 0; i < 6; i++) {
            Puntos[i] = new ObjetoAuxiliar("images/1984/Numeros/0.png", 30 + (i * 25), 80);
        }
        puntajeMasAlto = leerPuntuacionTop();
        int punt[] = separarEnDigitos(puntajeMasAlto);
        HPuntos = new ObjetoAuxiliar[6];
        for (int i = 0; i < 6; i++) {
            HPuntos[i] = new ObjetoAuxiliar("images/1984/Numeros/" + punt[i] + ".png", 250 + (i * 25), 80);
        }
    }

    public void gameShutdown() {
        Log.info(getClass().getSimpleName(), "Shutting down game");
    }

    public void gameUpdate(double delta) {
        if (skip)
            skipear();
        if (keyboard.isKeyPressed(MUSICA) && !flag2) {
            SONIDOM[0] = !SONIDOM[0];
            flag2 = true;
            if (!track.pausado())track.pause(); else track.resumeMusic();
        } else if (!keyboard.isKeyPressed(MUSICA)) {
            flag2 = false;
        }
        if (keyboard.isKeyPressed(EFECTOSDESONIDO) && !flag3) {
            SONIDOE[0] = !SONIDOE[0];
            flag3 = true;
        } else if (!keyboard.isKeyPressed(EFECTOSDESONIDO)) {
            flag3 = false;
        }

        updatenivel();
        borrarycargar();
        if ((nivelactual instanceof Nivel0) || (nivelactual instanceof GameOver)
                || (nivelactual instanceof Leaderboard))
            return;
        actualizarHora(pause);
        if (keyboard.isKeyPressed(PAUSAR) && !flag) {
            pause = !pause;
            flag = true;
            if (track.pausado())
               track.resumeMusic();
            else
               track.pause();

        } else if (!keyboard.isKeyPressed(PAUSAR))
            flag = false;
        if (!pause) {
            verificarObjetos();
            updateGeneral();
            if (nivelactual.getJefe() != null) {
                nivelactual.getJefe().update();
                nivelactual.getJefe().disparar(p38, pendientesGraficos);
            }
        }

    }

    public void gameDraw(Graphics2D g) {
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (nivelactual.getFondo() != null)
            nivelactual.getFondo().display(g);
        if (nivelactual.getJefe() != null) {
            nivelactual.getJefe().display(g);
        }
        if (!(nivelactual instanceof Nivel0) && !(nivelactual instanceof GameOver)
                && !(nivelactual instanceof Leaderboard))
            dibujarHUD(g);
        if (nivelactual instanceof Leaderboard)
            ((Leaderboard) nivelactual).mostrarPuntajes(g);
        dibujarObjetosGraficos(g);
    }

    private void dibujarHUD(Graphics2D g) {
        dibujarVida(g);
        player.display(g);
        highscore.display(g);
        if (jugador.getPuntuacion() != ultimoPuntaje) {
            int[] digitos = separarEnDigitos(jugador.getPuntuacion());
            // Imprimir los digitos separados
            for (int i = 0; i < 6; i++) {
                Puntos[i].setImagen("images/1984/Numeros/" + digitos[i] + ".png");
            }
            ultimoPuntaje = jugador.getPuntuacion();
        }
        for (int i = 0; i < 6; i++) {
            Puntos[i].display(g);
            HPuntos[i].display(g);

        }
    }

    public static int[] separarEnDigitos(long numero) {
        int[] digitos = new int[6];
        long divisor = 100000; // 10 elevado a la quinta potencia (6 digitos)

        for (int i = 0; i < 6; i++) {
            digitos[i] = (int) (numero / divisor);
            numero %= divisor;
            divisor /= 10;
        }

        return digitos;
    }

    private void dibujarVida(Graphics2D g) {
        int numero = p38.getEnergia();
        int redondeado = (numero / 10) * 10;
        switch (redondeado) {
            case 20:
                barra.setImagen("images/1984/vida/20.png");
                break;
            case 30:
                barra.setImagen("images/1984/vida/30.png");
                break;
            case 40:
                barra.setImagen("images/1984/vida/40.png");
                break;
            case 50:
                barra.setImagen("images/1984/vida/50.png");
                break;
            case 60:
                barra.setImagen("images/1984/vida/60.png");
                break;
            case 70:
                barra.setImagen("images/1984/vida/70.png");
                break;
            case 80:
                barra.setImagen("images/1984/vida/80.png");
                break;
            case 90:
                barra.setImagen("images/1984/vida/90.png");
                break;
            case 100:
                barra.setImagen("images/1984/vida/100.png");
                break;
        }
        if (redondeado >= 20)
            barra.display(g);
        E.display(g);
        int digito1 = numero / 100; // El primer digito
        int digito2 = (numero % 100) / 10; // El segundo digito
        int digito3 = numero % 10; // El tercer digito
        E1.setImagen("images/1984/Numeros/" + digito1 + ".png");
        E1.display(g);
        E2.setImagen("images/1984/Numeros/" + digito2 + ".png");
        E2.display(g);
        E3.setImagen("images/1984/Numeros/" + digito3 + ".png");
        E3.display(g);
    }

    private void actualizarHora(boolean pause) {
        if (pause) {
            if (dPausado == null)
                dPausado = new Date();
            dAhora = new Date();
            tiempoPausado = dAhora.getTime() - dPausado.getTime();
        } else {
            dPausado = null;
            dAhora = new Date();
            acumPause += tiempoPausado;
            tiempoPausado = 0;
            dateDiff = dAhora.getTime() - dInit.getTime() - acumPause;
            diffSeconds[0] = dateDiff / 1000 % 60;
            diffMinutes[0] = dateDiff / (60 * 1000) % 60;
            p38.setDate();
        }

    }

    private void dibujarObjetosGraficos(Graphics2D g) {
        for (ObjetoGrafico objeto : objetosGraficos) {
            objeto.display(g);
        }
        if ((nivelactual instanceof Nivel0) || (nivelactual instanceof GameOver)
                || (nivelactual instanceof Leaderboard))
            return;
        p38.display(g);
        for (Explosion explosion : explosiones) {
            explosion.display(g);
        }
        for (Refuerzo refuerzo : p38.getRefuerzos())
            refuerzo.display(g);
    }

    private void updateGeneral() {
        hayTsunami = false;
        p38.update();
        if (p38.getEnergia() <= 0)
            nivelactual.setEstado(0);// Gamer Over
        for (ObjetoGrafico objeto : objetosGraficos) {
            objeto.update();
            revisarInstersecciones(objeto);
            if (objeto instanceof Enemigo)
                efectuarDisparosEnemigos((Enemigo) objeto);
        }
        if (!hayTsunami && nivelactual.getFondo().movible())
            nivelactual.getFondo().update();
        for (Explosion explosion : explosiones) {
            explosion.update();
        }
    }

    private void efectuarDisparosEnemigos(Enemigo enemigo) {
        if (enemigo.disparoPendiente())
            enemigo.disparar(p38, pendientesGraficos);
    }

    private void revisarInstersecciones(ObjetoGrafico objeto) {
        if ((objeto instanceof AtaqueEspecial)) {
            aplicarAtaque((AtaqueEspecial) objeto);
        } else {
            if (objeto instanceof Enemigo) {
                for (Refuerzo refuerzo : p38.getRefuerzos()) {
                    if ((interseccion(refuerzo, objeto)))
                        colisionar(refuerzo, (Enemigo) objeto);
                }
                if ((interseccion(p38, objeto)))
                    colisionar((P38) p38, (Enemigo) objeto);
            } else {
                if (objeto instanceof DisparoEnemigo) {
                    for (Refuerzo refuerzo : p38.getRefuerzos()) {
                        if ((interseccion(refuerzo, objeto)))
                            colisionar(refuerzo, (DisparoEnemigo) objeto);
                    }
                }
                if ((((objeto instanceof DisparoEnemigo)) || (objeto instanceof Hiteable)) && interseccion(objeto, p38))
                    colisionar(p38, objeto);
                else {
                    if ((objeto instanceof Disparo) && (!(objeto instanceof DisparoEnemigo))) {
                        for (ObjetoGrafico objeto2 : objetosGraficos) {
                            if ((interseccion(objeto, objeto2)) && (objeto2 instanceof Hiteable)) {
                                colisionar((Disparo) objeto, (Hiteable) objeto2);

                            }

                            if ((interseccion(objeto, objeto2)) && (objeto2 instanceof DisparoEnemigo)
                                    && (objeto instanceof DisparoEscopeta)) {
                                objeto.setBorrar();
                                objeto2.setBorrar();
                            }
                        }
                    }
                }
            }
        }
    }

    private void aplicarAtaque(AtaqueEspecial ataqueEspecial) {
        if (ataqueEspecial instanceof Relampago) {
            Relampago relampago = (Relampago) ataqueEspecial;
            relampago.aplicar(objetosGraficos);
        } else if (ataqueEspecial instanceof Tsunami) {
            hayTsunami = true;
            Tsunami tsunami = (Tsunami) ataqueEspecial;
            tsunami.aplicar(objetosGraficos);
        }
    }

    private void verificarObjetos() {
        p38.update();
        for (ObjetoGrafico objeto : objetosGraficos) {
            if (objeto.getBorrar()) {
                limpiezGraficos.add(objeto);
            }
        }
        for (Explosion explosion : explosiones) {
            if (explosion.getBorrar())
                limpiezGraficos.add(explosion);
        }
        for (Refuerzo refuerzo : p38.getRefuerzos()) {
            if (refuerzo.getBorrar())
                limpiezGraficos.add(refuerzo);
        }
    }



    private void borrarycargar() {
        for (ObjetoGrafico objeto : limpiezGraficos) {
            if (objeto instanceof Explosion) {
                explosiones.remove(objeto);
            } else {
                if (objeto instanceof EnemigoGde1) {
                    explosiones.add(new Explosion(objeto.getX(), objeto.getY()));
                    explosiones.add(new Explosion(objeto.getX() + 70, objeto.getY() + 90));
                    explosiones.add(new Explosion(objeto.getX(), objeto.getY() + 67));
                    explosiones.add(new Explosion(objeto.getX() + 50, objeto.getY()));
                    explosiones.add(new Explosion(objeto.getX() + 35, objeto.getY() + 49));
                    if (!((Enemigo) objeto).fueraDeRango())
                        jugador.sumarPuntos(100);
                } else if (objeto instanceof Enemigo) {
                    explosiones.add(new Explosion(objeto.getX(), objeto.getY()));
                    if (!((Enemigo) objeto).fueraDeRango()) {
                        jugador.sumarPuntos(10);
                        FXPlayer.EXPLOSION.playEfect();
                    }
                }
                if (objeto instanceof EnemigoRojo) {
                    EnemigoRojo.reducir();
                    if (EnemigoRojo.getCantidad() == 0) {
                        pendientesGraficos.add(bonusRandom(objeto.getX(), objeto.getY()));
                        EnemigoRojo.reducir();
                    }
                }
                objetosGraficos.remove(objeto);
            }
            if (objeto instanceof Refuerzo) {
                explosiones.add(new Explosion(objeto.getX(), objeto.getY()));
                p38.getRefuerzos().remove(objeto);
            }
        }
        limpiezGraficos.clear();
        for (ObjetoGrafico objeto : pendientesGraficos) {
            objetosGraficos.add(objeto);
        }
        pendientesGraficos.clear();
    }

    private static boolean interseccion(ObjetoGrafico a, ObjetoGrafico b) {
        double ax = a.getX();
        double ay = a.getY();
        double aw = a.getWidth();
        double ah = a.getHeight();
        double bx = b.getX();
        double by = b.getY();
        double bw = b.getWidth();
        double bh = b.getHeight();
        aw += ax;
        ah += ay;
        bw += bx;
        bh += by;
        return ((aw < ax || aw > bx) &&
                (ah < ay || ah > by) &&
                (bw < bx || bw > ax) &&
                (bh < by || bh > ay));
    }

    private static boolean interseccion(Tsunami tsunami, ObjetoGrafico b) {
        return ((b.getX() > tsunami.getX() + tsunami.desplazamiento() + 600)
                && (b.getY() > 1000 + tsunami.desplazamiento()));
    }

    private void colisionar(Disparo disparo, Hiteable hiteable) {
        limpiezGraficos.add(disparo);
        hiteable.recibirDisparo(disparo);
        if (hiteable instanceof Bonus) {
            Bonus bonus = (Bonus) hiteable;
            if ((bonus.getCount() >= 3))
                pendientesGraficos.add(bonusRandom(bonus.getX(), bonus.getY() - 40));
        }
    }

    private void colisionar(P38 p38, ObjetoGrafico objeto2) {
        if ((objeto2 instanceof Enemigo) && (!p38.invulnerable()) && (!p38.Girando())) {
            Enemigo enemigo = (Enemigo) objeto2;
            enemigo.recibirDanio(10);
            p38.recibirDanio(10);
            FXPlayer.HIT.playEfect();
        }
        if ((objeto2 instanceof DisparoEnemigo) && (!p38.invulnerable()) && (!p38.Girando())) {
            objeto2.setBorrar();
            p38.recibirDanio(5);
            FXPlayer.HIT.playEfect();
        }
        if ((objeto2 instanceof Bonus)) {
            if (objeto2 instanceof ItemSecreto) {
                skip = true;
            } else {
                Bonus bonus = (Bonus) objeto2;
                bonus.aplicar(p38);
                FXPlayer.BONUS.playEfect();
            }
        }
    }

    private void skipear() {
        clear();
        indexNivel++;
        inicializarNivel(indexNivel);
        nivelactual.start();
        skip = false;
    }

    private void colisionar(Refuerzo refuerzo, Enemigo enemigo) {
        refuerzo.setBorrar();
        enemigo.recibirDanio(10);
    }

    private void colisionar(Refuerzo refuerzo, DisparoEnemigo disparo) {
        refuerzo.setBorrar();
        disparo.setBorrar();
    }

    private Bonus bonusRandom(double x, double y) {
        Random random = new Random();
        int numeroRandom1;
        numeroRandom1 = random.nextInt(7) + 1;
        Bonus bonus = new Auto();
        switch (numeroRandom1) {
            case 1:
                bonus = new Auto();
            case 2:
                bonus = new EstrellaNinja();
                break;
            case 3:
                bonus = new POW();
                break;
            case 4:
                bonus = new SuperShell();
                break;
            case 5:
                bonus = new CambioArma();
                break;
            case 6:
                bonus = new ObtenerRefuerzos();
                break;
        }
        bonus.setPosition(x, y);
        return bonus;
    }

   

    private void clear() {
        dInit = new Date();
        objetosGraficos.clear();
        pendientesGraficos.clear();
        p38 = new P38(keyboard, pendientesGraficos);
    }

    private void updatenivel() {
        if (keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
            track.pause();
            stop();
        }
        int estado = nivelactual.getestado();
        switch (estado) {
            case 1:
                if (nivelactual.getJefe() != null) {
                    for (Torreta torreta : nivelactual.getJefe().getTorretas()) {
                        for (ObjetoGrafico objetoGrafico : objetosGraficos) {
                            if ((objetoGrafico instanceof Disparo) && (!(objetoGrafico instanceof DisparoEnemigo))
                                    && (interseccion(torreta, objetoGrafico))) {
                                colisionar((Disparo) objetoGrafico, torreta);
                                explosiones.add(new Explosion(torreta.getX(), torreta.getY()));
                            } else if (objetoGrafico instanceof Tsunami) {
                                if (interseccion((Tsunami) objetoGrafico, torreta)
                                        && !(((Tsunami) objetoGrafico).getAfectados().contains(torreta))) {
                                    torreta.recibirDanio(10);
                                    ((Tsunami) objetoGrafico).getAfectados().add(torreta);
                                }
                            }
                        }
                        if (torreta.getBorrar()) {
                            jugador.sumarPuntos(10);
                        }
                    }
                }
                break;// caso normal, en ejecucion
            case -1:// cerrar el juego
                track.pause();
                stop();
                break;
            case 0:// Game over
                clear();
                track.pause();
                track = FXPlayer.GAMEOVER;
                if (SONIDOM[0])
                    track.playMusic();
                guardarEnJSON(jugador.getNombre(), jugador.getPuntuacion());
                jugador.reiniciar();
                nivelactual = new GameOver(keyboard, pendientesGraficos);
                nivelactual.start();
                break;
            case 2:// Avanzo de nivel
                clear();
                indexNivel++;
                inicializarNivel(indexNivel);
                nivelactual.start();
                break;
            case 3:// Retomo el nivel
                clear();
                inicializarNivel(indexNivel);
                nivelactual.start();
                break;
            case 4:// Paso a la segunda fase
                clear();
                track.pause();
                if (nivelactual instanceof Nivel1)
                    track = FXPlayer.TRACK2;
                else
                    track = FXPlayer.TRACK4;
                if (SONIDOM[0])
                    track.playMusic();
                p38.aproximar();
                nivelactual.getFondo().setImagen("images/1984/fondo2.png");
                nivelactual.getFondo().setY(-nivelactual.getFondo().getHeight() + 810);
                nivelactual.setEstado(1);
                break;
            case 5:// Se acabo el tiempo de la mision
                if (nivelactual.getJefe().getDanioAcumulado() > 0.7) {
                    nivelactual.setEstado(2);
                } else
                    nivelactual.setEstado(0);
                break;
        }
    }

    private void inicializarNivel(int indexNivel) {
        track.pause();
        switch (indexNivel) {
            case 1:
                nivelactual = new Nivel1(pendientesGraficos, diffSeconds, diffMinutes);
                track = FXPlayer.TRACK1;
                break;
            case 2:
                nivelactual = new Nivel2(pendientesGraficos, diffSeconds, diffMinutes);
                track = FXPlayer.TRACK3;
                break;
            case 3:
                guardarEnJSON(jugador.getNombre(), jugador.getPuntuacion());
                nivelactual = new Leaderboard(keyboard, pendientesGraficos);
                track = FXPlayer.ENDING;
                break;
        }
            track.playMusic();
    }

    private void guardarEnJSON(String nombre, long puntuacion) {
        File archivoJSON = new File("src/main/resources/puntuaciones.json");

        // Leer el contenido existente del archivo JSON y convertirlo en una estructura
        // de datos Java
        List<Map.Entry<String, Long>> puntuaciones;

        try {
            if (archivoJSON.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                puntuaciones = objectMapper.readValue(archivoJSON, new TypeReference<List<Map.Entry<String, Long>>>() {
                });
            } else {
                puntuaciones = new ArrayList<>();
            }

            // Agregar el nuevo nombre y puntuacion
            Map.Entry<String, Long> nuevaPuntuacion = new AbstractMap.SimpleEntry<>(nombre, puntuacion);
            puntuaciones.add(nuevaPuntuacion);

            // Ordenar las puntuaciones por la puntuacion en orden descendente
            puntuaciones.sort((a, b) -> Long.compare(b.getValue(), a.getValue()));

            // Escribir la estructura de datos ordenada en el archivo JSON
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(archivoJSON, puntuaciones);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long leerPuntuacionTop() {
        File archivoJSON = new File("src/main/resources/puntuaciones.json");

        try {
            if (archivoJSON.exists()) {
                ObjectMapper objectMapper = new ObjectMapper();
                final List<Map.Entry<String, Long>> puntuaciones = new ArrayList<>();

                puntuaciones.clear();
                puntuaciones
                        .addAll(objectMapper.readValue(archivoJSON, new TypeReference<List<Map.Entry<String, Long>>>() {
                        }));

                if (!puntuaciones.isEmpty()) {
                    Map.Entry<String, Long> primerPuntajeEntry = puntuaciones.get(0);
                    return primerPuntajeEntry.getValue();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Si no hay puntajes o el archivo no existe, retornar 0 como valor
        // predeterminado
        return 0L;
    }
}
