/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris_demo;

import java.awt.Color;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private String img;
    private int x, y;
    private int[][][] shapes;
    private int currentRotation;
    
    private Color[] availableColors = {Color.green, Color.red, Color.blue};
     private String listImg[] = {"src\\image\\blue_block.png", "src\\image\\cyan_block.png", "src\\image\\green_block.png", "src\\image\\orange_block.png", "src\\image\\red_block.png"};
    
    public TetrisBlock(int[][] shape) {
        this.shape = shape;
        initShapes();
    }
    private void initShapes(){
        shapes = new int[4][][];
        for(int i=0;i<4;i++){
            int r = shape[0].length;
            int c = shape.length;
            shapes[i] = new int[r][c];
            for(int y = 0;y<r;y++){
                for(int x = 0;x<c;x++){
                    shapes[i][y][x] = shape[c-x-1][y];
                }
            }
            shape = shapes[i];
        }
    }
    public void spawn(int gridWidth){ // tạo khối mới.
        Random r = new Random();
        
        currentRotation = r.nextInt(shapes.length);
        shape = shapes[currentRotation];
        y = -getHeight();
        x = r.nextInt(gridWidth - getWidth());
        color = availableColors[r.nextInt(availableColors.length)];
        img = listImg[r.nextInt(listImg.length)];
    }

    public int[][] getShape() { // nhận một khối.
//        currentRotation = 0;
//        shape = shapes[currentRotation];
        return shape;
    }

    public Color getColor() {
        return color;
    }
    
    public int getX(){  // nhận hoàng độ
        return x;
    }
    
    public void setX(int newX){ // tạo hoành độ mới
        x = newX;
    }
    public int getY(){          // nhận tung độ.
        return y;
    }
    public void setY(int newY){ // tạo tung độ mới.
        y = newY;
    }
    
    public int getHeight(){     // lấy chiều cao của khối.
        return shape.length;
    }
    
    public int getWidth(){      // lấy chiều rộng của khối.
        return shape[0].length;
    }
    
    public void moveDown(){     
        y++;
    }
    public void moveRight(){
        x++;
    }
    public void moveLeft(){
        x--;
    }
    public int getBottomEdge(){     //cập nhật chiều cao của đáy.
        return y + getHeight();
    }
    
    public void rotate(){
        currentRotation++; 
        if(currentRotation > 3){
            currentRotation = 0;
        }
        
        shape = shapes[currentRotation];
    }
    
    public int getLeftEdge(){      // lấy cạnh trái.
        return x;
    }
    public int getRightEdge(){      // lấy cạnh phải.
        return x + getWidth();      // tọa độ x + chiều rộng của khối.
    }
    public String getImage() {
        return img;
    }
}