/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;
/**
 *
 * @author josev_ferreira
 */
public class Panel extends JPanel {
    public void paintComponent(Graphics g) {
		g.fillRect(0,0,this.getWidth(), this.getHeight());
		int red = (int) (Math.random() * 255);
		int green = (int) (Math.random() * 255);
		int blue = (int) (Math.random() * 255);
		Color randomColor = new Color(red, green, blue);
		g.setColor(randomColor);
		g.fillOval(70,70,100,100);
	}
}
