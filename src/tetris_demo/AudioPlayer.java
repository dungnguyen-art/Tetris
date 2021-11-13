/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris_demo;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Admin
 */
public class AudioPlayer {
    private String soundsFolders = "tetrissounds" + File.separator;
    private String clearLinePath = soundsFolders + "line.wav";
    private String gameoverPath = soundsFolders + "Oiline.wav";
    
    private Clip clearLineSound, gameoverSound;
    public AudioPlayer(){
        try {
            clearLineSound = AudioSystem.getClip();
            gameoverSound = AudioSystem.getClip();
            
            clearLineSound.open(AudioSystem.getAudioInputStream(new File(clearLinePath).getAbsoluteFile()));
            gameoverSound.open(AudioSystem.getAudioInputStream(new File(gameoverPath).getAbsoluteFile()));
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void playclearLine(){
        clearLineSound.setFramePosition(0);
        clearLineSound.start();
    }
    public void playGameover(){
        gameoverSound.setFramePosition(0);
        gameoverSound.start();
    }
}

