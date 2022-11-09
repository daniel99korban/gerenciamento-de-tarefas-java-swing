/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.JTextField;

/**
 *
 * @author josev_ferreira
 */
public class TextField extends JTextField{
    public TextField(String titulo){
        this.setSize(360, 40);
        this.setText(titulo);
    }
}
