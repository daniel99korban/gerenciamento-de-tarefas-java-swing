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
import javax.swing.JPasswordField;
public class password extends JPasswordField {
    public password (String Titulo, Color cor){
        this.setSize(360, 40);
        this.setText(Titulo);
        this.setForeground(cor);
    }
     
   
}
