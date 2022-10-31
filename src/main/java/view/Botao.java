/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import javax.swing.JButton;

public class Botao extends JButton {
    public Botao(String titulo){
        this.setText(titulo);
        this.setSize(80, 20);
        this.setForeground(Color.WHITE);
        this.setBackground(Color.BLUE);
        this.setVisible(true);
        
        
    }
}
