/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetris_demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Admin
 */
public class BackImage extends JFrame{
    private JLabel L1;
    public BackImage(){
        setSize(700,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new BorderLayout());
        
        setContentPane(new JLabel(new ImageIcon("C:\\Users\\Admin\\Pictures\\M10\\F_back.jpg")));
        setLayout(new FlowLayout());
        L1 = new JLabel();
        add(L1);
        setSize(500,500);
    }
    
    public static void main(String[] args) {
        new BackImage();
    }

}
