/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.componente;

/**
 *
 * @author josev_ferreira
 */
import java.awt.Color;
import javax.swing.JCheckBox;

public class CheckBox extends JCheckBox {
    public CheckBox(String titulo, Color cor){
        this.setText(titulo);
        this.setForeground(Color.WHITE);
        this.setBackground(cor);
    }

    
}
