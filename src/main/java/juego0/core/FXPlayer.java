package juego0.core;

/*
 Ejemplo original

 https://www3.ntu.edu.sg/home/ehchua/programming/java/J8c_PlayingSound.html

 */

import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public enum FXPlayer {
   DISPARO("D1.wav"),
   DISPAROAM("DA.wav"),
   DISPAROES("DE.wav"),
   DISPAROLA("DL.wav"),
   EXPLOSION("Explosion.wav"),
   BONUS("Bonus.wav"),
   HIT("Hit.wav"),
   RELAMPAGO("Relampago.wav"),
   TSUNAMI("Tsunami.wav"),
   GAMEOVER("GameOver.wav"),
   ENDING("Ending.wav"),
   TRACK0("Track0.wav"),
   TRACK1("Track1.wav"),
   TRACK2("Track2.wav"),
   TRACK3("Track3.wav"),
   TRACK4("Track4.wav");

   public static enum Volume {
      MUTE, LOW, MEDIUM, HIGH
   }

   private Volume volume = Volume.LOW;

   private Clip clip;
   private boolean pausado = false;
   static private boolean SonidoG[], SonidoE[], SonidoM[];

   FXPlayer(String wav) {
      try {

         URL url = this.getClass().getClassLoader().getResource("sonidos/" + wav);

         AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);

         clip = AudioSystem.getClip();

         clip.open(audioInputStream);
      } catch (UnsupportedAudioFileException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (LineUnavailableException e) {
         e.printStackTrace();
      }
   }

   public void playMusic() {
      if ((SonidoG[0] && SonidoM[0]) && (volume != Volume.MUTE)) {
         clip.setFramePosition(0);
         clip.start();
      }
   }

   public void playEfect() {
      if ((SonidoG[0] && SonidoE[0]) && (volume != Volume.MUTE)) {
         clip.setFramePosition(0);
         clip.start();
      }
   }

   public void pause() {
      clip.stop();
      pausado = true;
   }

   public void resumeMusic() {
      if ((SonidoG[0] && SonidoM[0])) {
         clip.start();
         pausado = false;
      }
   }

   public boolean pausado() {
      return pausado;
   }

   static void init(boolean SONIDOG[], boolean SONIDOE[], boolean SONIDOM[]) {
      values();
      SonidoE = SONIDOE;
      SonidoG = SONIDOG;
      SonidoM = SONIDOM;
   }
}