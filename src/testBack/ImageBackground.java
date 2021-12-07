/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testBack;
import javax.swing.*;
import java.awt.*;

public class ImageBackground 
{
  public static void main(String args[]) 
  {
    JFrame frame = new JFrame("Display an image in the background");
    final ImageIcon icon = new ImageIcon("b.jpg");
    JTextArea text = new JTextArea() 
    {
      Image img = icon.getImage();
      // instance initializer
      {setOpaque(false);}
      public void paintComponent(Graphics graphics) 
      {
        graphics.drawImage(img, 0, 0, this);
        super.paintComponent(graphics);
      }
    };
    JScrollPane pane = new JScrollPane(text);
    Container content = frame.getContentPane();
    content.add(pane, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(3);
    frame.setSize(400, 300);
    frame.setVisible(true);
  }
}