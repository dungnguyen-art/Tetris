/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris_demo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import tetrisBlocks.*;
/**
 *
 * @author Admin
 */
public class GameArea extends JPanel {

    private int gridRows;
    private int gridColumns;
    private int gridCellSize;
    private Color[][] backgroud;
    private String [][] background2;
    private TetrisBlock block;

    private TetrisBlock  blocks []; // mang doi tuong TetrisBlock.
    
//    private int [] a = new int[100];
    public GameArea(JPanel placeholder, int columns) {
//        placeholder.setVisible(false);
        this.setBounds(placeholder.getBounds());
        this.setBackground(placeholder.getBackground());
        this.setBorder(placeholder.getBorder());

        gridColumns = columns;
        gridCellSize = this.getBounds().width / gridColumns;
        gridRows = this.getBounds().height / gridCellSize;
        
        blocks = new TetrisBlock[]{new IShape(),
                                   new JShape(),
                                   new LShape(),
                                   new OShape(),
                                   new SShape(),
                                   new TShape(),
                                   new ZShape(),
        };
    }
    
    public void initBackgroundArray(){          // khởi tạo giao diện lưới.
        backgroud = new Color[gridRows][gridColumns];
        background2 = new String[gridRows][gridColumns];
    }
    public void spawnBlock(){  
        Random r = new Random();
        
        block =  blocks[r.nextInt(blocks.length)];
        block.spawn(gridColumns);
    }
    public boolean isBlockOutOfBounds(){        // giới hạn vị trí xuất hiện của khối mới.
        if(block.getY() < 0){
            block = null;
            return true;
        }
        return false;
    }
    
    public boolean moveBlockDown(){
        if(checkBottom() == false){
//            moveBlockToBackgroud();
//            clearLines();
            return false;
        }
        block.moveDown();
        repaint();
        return true;
    }
    public void moveBlockRight(){
        if(block == null) return;
        if(checkRight() == false){
            return;
        }
        block.moveRight();
        repaint();
    }
    public void moveBlockLeft(){
        if(block == null) return;
        if(checkLeft() == false){
            return;
        }
        block.moveLeft();
        repaint();
    }
    public void dropBlock(){
        if(block == null) return;
        while(checkBottom()){
            block.moveDown();
        }
        repaint();
    }
    
    public void rotateBlock(){
        if(block == null) return;
        block.rotate();
        if(block.getLeftEdge() < 0)             // đặt giới hạn cạnh trái
            block.setX(0);
        if(block.getRightEdge() >= gridColumns) // đặt giới hạn cạnh phải.
            block.setX(gridColumns - block.getWidth());
        if(block.getBottomEdge() >= gridRows)   // đặt giới hạn của đáy
            block.setY(gridRows - block.getHeight());
        repaint();
    }
    
    
    private boolean checkBottom(){
        if(block.getBottomEdge() == gridRows){
            return false;
        }
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for(int col = 0; col < w; col++){
            for(int row = h-1;row >=0;row--){
                if(shape[row][col] != 0){
                    int x = col + block.getX();
                    int y = row + block.getY() + 1;
                    if(y<0) break;
                    if(backgroud[y][x] != null){
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
    private boolean checkLeft(){
        if(block.getLeftEdge() == 0){
            return false;
        }
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for(int row = 0; row < h; row++){
            for(int col = 0;col < w;col++){
                if(shape[row][col] != 0){
                    int x = col + block.getX() - 1;
                    int y = row + block.getY();
                    if(y<0) break;
                    if(backgroud[y][x] != null){
                        return false;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    private boolean checkRight(){
        if(block.getRightEdge() == gridColumns){
            return false;
        }
        
        int[][]shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();
        for(int row = 0; row < h; row++){
            for(int col = w-1;col >= 0;col--){
                if(shape[row][col] != 0){
                    int x = col + block.getX() + 1;
                    int y = row + block.getY();
                    if(y<0) break;
                    if(backgroud[y][x] != null){
                        return false;
                    }
                    break;
                }
            }
        }
        
        return true;
    }
    
    public int clearLines(){
        boolean lineFilled;
        int linesCleared = 0;
        for(int r = gridRows - 1; r >= 0; r--){
            lineFilled = true;
            for(int c = 0; c < gridColumns; c++){
                if(backgroud[r][c] == null){
                    lineFilled = false;
                    break;
                }
            }
            if(lineFilled){
                linesCleared ++;
                clearLine(r);
                shiftDown(r);
                clearLine(0);
                r++;
                repaint();
            }
        }
        if(linesCleared>0){
            Tetris_demo.playClear();
        }
        return linesCleared;
    }
    private void clearLine(int r){
        for(int i = 0;i<gridColumns;i++){
            backgroud[r][i] = null;
            background2[r][i] = null;
        }
    }
    private void shiftDown(int r){
        for(int row = r; row > 0; row--){
            for(int col = 0; col < gridColumns; col++){
                backgroud[row][col] = backgroud[row-1][col];
                background2[row][col] = background2[row-1][col];
            }
        }
    }
    
    public void moveBlockToBackgroud(){         // vẽ khối khi thay đổi tọa độ
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
                    background2[r+yPos][c+xPos] = block.getImage();
                }
            }
        }
    }
    
    
    public void drawBlock(Graphics g){  // vẽ khối
        if (block != null) {
            int h = block.getHeight();
            int w = block.getWidth();
            Color c = block.getColor();
            int[][] shape = block.getShape();

            for(int row = 0;row < h;row ++){
                for(int col = 0;col < w; col ++){ 
                    if(shape[row][col] == 1){
                        int x = (block.getX() + col)*gridCellSize;
                        int y = (block.getY() + row)*gridCellSize;

                        drawGridSquare(g, c, x, y, block.getImage());
                    }
                }
            }
        }
    }
    
    private void drawBackground(Graphics g){    // vẽ nền
        Color color;
        for(int r=0;r<gridRows;r++){
            for(int c = 0; c < gridColumns; c++){
                color = backgroud[r][c];
                String img = background2[r][c];
                if(color != null){
                    int x = c*gridCellSize;
                    int y = r*gridCellSize;
                    
                    drawGridSquare(g, color, x, y, img);
                }
            }
        }
    }
    
    private void drawGridSquare(Graphics g, Color color, int x, int y, String img){ // vẽ từng ô vuông một
        if (img.equals("")) {
            g.setColor(color);
            g.fillRect(x, y, gridCellSize, gridCellSize);
            g.setColor(color.black);
            g.drawRect(x, y, gridCellSize, gridCellSize);
        }
        else {
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File(img));
            }
            catch (IOException ex) {
            }
            g.drawImage(image.getScaledInstance(gridCellSize, gridCellSize, Image.SCALE_SMOOTH), x, y, this);  
        }
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