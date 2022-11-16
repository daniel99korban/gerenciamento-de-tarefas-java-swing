/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.componente;

import java.awt.Color;
import javax.swing.JTextField;

/**
 *
 * @author josev_ferreira
 */
public class TextField extends JTextField{
    public TextField(String titulo, Color cor){
        this.setSize(360, 40);
        this.setText(titulo);
        this.setForeground(cor);
    }

    
   
}
