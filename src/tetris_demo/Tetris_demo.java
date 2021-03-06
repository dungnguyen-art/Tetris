/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
package tetris_demo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Tetris_demo {

    /**
     * @param args the command line arguments
     */
    private static GameForm gf;
    private static StartupForm sf;
    private static LeaderboardForm lf;
    private static AudioPlayer audio = new AudioPlayer();
    
    public static void start(){
        gf.setVisible(true);
        gf.startGame();
    }
    
    public static void showLeaderboard(){
        lf.setVisible(true);
    }
    
    public static void showStartup(){
        sf.setVisible(true);
    } 
    
    public static void gameOver(int score){
        playGameover();
        String playerName = JOptionPane.showInputDialog("Game Over!\nPlease enter your name.");
        gf.setVisible(false);
        try {
            lf.addPlayer(playerName, score);
        } catch (IOException ex) {
            Logger.getLogger(Tetris_demo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void playClear(){
        audio.playclearLine();
    }
    public static void playGameover(){
        audio.playGameover();
    }
    public static void playBackgroundMusic(){
        audio.playBackgroundMusic();
    }
    
    public static void main(String[] args) {
         java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                gf = new GameForm();
                sf = new StartupForm();
                try {
                    lf = new LeaderboardForm();
                } catch (IOException ex) {
                    Logger.getLogger(Tetris_demo.class.getName()).log(Level.SEVERE, null, ex);
                }
                sf.setVisible(true);
            }
        });
    }
    
}
