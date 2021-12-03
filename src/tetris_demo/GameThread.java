/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris_demo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class GameThread extends Thread {

    private GameArea ga;
    private GameForm gf;
    private Tetris_demo td;
    private int score;
    private int level = 1;
    private int scorePerLevel = 3;
    
    private int pause = 1000;
    private int speedupPerLevel = 200;
    public GameThread(GameArea ga, GameForm gf) {
        this.ga = ga;
        this.gf = gf;
        gf.updateScore(score);
        gf.updateLevel(level);
    }

    @Override
    public void run() {
        td.playBackgroundMusic();
        while (true) {
            
            ga.spawnBlock();
            while (ga.moveBlockDown() == true) {
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    return;
                }
            }
            if(ga.isBlockOutOfBounds()){
                Tetris_demo.gameOver(score);
                break;
            }
            ga.moveBlockToBackgroud();
            score += ga.clearLines();
            gf.updateScore(score);
            int lvl = score/scorePerLevel + 1;
            if(lvl > level){
                level = lvl;
                gf.updateLevel(level);
                if(pause > 200){
                    pause -= speedupPerLevel; 
                }
            }
        }
    }

}
