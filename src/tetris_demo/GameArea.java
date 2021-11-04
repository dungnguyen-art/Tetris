/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris_demo;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
/**
 *
 * @author Admin
 */
public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] backgroud;
    private TetrisBlock block;

    public GameArea(JPanel placeholder, int columns) {
        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
//        spawnBlock();
        backgroud = new Color[gridRows][gridColumns];
        
    }
    
    public void spawnBlock(){
        block = new TetrisBlock(new int[][]{{1,0},{1,0},{1,1}}, Color.blue);
        block.spawn(gridColumns);
    }
//    
//    public boolean moveBlockDown(){
//        if(checkBotton() == false){
//            moveBlockToBackgroud();
//            return false;
//        }
//        block.moveDown();
//        repaint();
//        return true;
//    }
//    public void moveBlockRight(){
//        block.moveRight();
//        repaint();
//    }
//    public void moveBlockLeft(){
//        block.moveLeft();
//        repaint();
//    }
//    public void dropBlock(){
//        while(checkBotton()){
//            block.moveDown();
//        }
//        repaint();
//    }
//    public void rotateBlock(){
//        block.rotate();
//        repaint();
//    }
//    private boolean checkBotton(){
//        if(block.getBottonEdge() == gridRows){
//            return false;
//        }
//        return true;
//    }
    
    private void moveBlockToBackgroud(){
        int[][] shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();
        
        int xPos = block.getX();
        int yPos = block.getY();
        Color color = block.getColor();
        
        for(int r = 0;r<h;r++){
            for(int c = 0;c<w;c++){
                if(shape[r][c] == 1){
                    backgroud[r+yPos][c+xPos] = color;
                }
            }
        }
    }
    
    
    public void drawBlock(Graphics g){
        int h = block.getHeight();
        int w = block.getWidth();
        Color c = block.getColor();
        int[][] shape = block.getShape();
        
        for(int row = 0;row < h;row ++){
            for(int col = 0;col < w; col ++){
                if(shape[row][col] == 1){
                    int x = (block.getX() + col)*gridCellSize;
                    int y = (block.getY() + row)*gridCellSize;
                    
                    drawGridSquare(g, c, x, y);
                }
            }
        }
    }
    private void drawBackground(Graphics g){
        Color color;
        for(int r=0;r<gridRows;r++){
            for(int c = 0; c < gridColumns; c++){
                color = backgroud[r][c];
                if(color != null){
                    int x = c*gridCellSize;
                    int y = r*gridCellSize;
                    
                    drawGridSquare(g, color, x, y);
                }
            }
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y){
        g.setColor(block.getColor());
        g.fillRect(x, y, gridCellSize, gridCellSize);
        g.setColor(color.black);
        g.drawRect(x, y, gridCellSize, gridCellSize);
    }
    
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < gridRows; y++) {
            for (int x = 0; x < gridColumns; x++) {
                g.drawRect(x * gridCellSize, y*gridCellSize, gridCellSize, gridCellSize);
            }
        }
        drawBackground(g);
        drawBlock(g);
    }
}
////sfsfsfsf
