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
    private int score;
    private int level = 1;
    private int scorePerLevel = 3;
    
    private int pause = 1000;
    private int speedupPerLevel = 200;
    public GameThread(GameArea ga, GameForm gf) {
        this.ga = ga;
        this.gf = gf;
    }

    @Override
    public void run() {
        while (true) {
            ga.spawnBlock();
            while (ga.moveBlockDown() == true) {
                try {
                    Thread.sleep(pause);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if(ga.isBlockOutOfBounds()){
                System.out.println("Game Over");
                break;
            }
            ga.moveBlockToBackgroud();
            score += ga.clearLines();
            gf.updateScore(score);
            int lvl = score/scorePerLevel + 1;
            if(lvl > level){
                level = lvl;
                gf.updateLevel(level);
                pause -= speedupPerLevel; 
            }
        }
    }

}
