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
public class IShape extends TetrisBlock{

    public IShape() {
        super(new int[][]{{1,1,1,1}});
    }
    @Override
    public void rotate(){
        super.rotate();
        if(this.getWidth() == 1){
            this.setX(this.getX() + 1);
            this.setY(this.getY() + 1);
        }
        else{
            this.setX(this.getX() - 1);
            this.setY(this.getY() + 1);
        }
    }
}
