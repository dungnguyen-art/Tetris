/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisBlocks;

import tetris_demo.TetrisBlock;

/**
 *
 * @author Admin
 */
public class LShape extends TetrisBlock {
    public LShape(){
        super(new int[][]{  {1,0},
                            {1,0},
                            {1,1},
        });
    }
}
