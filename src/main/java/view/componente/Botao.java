/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.componente;

import java.awt.Color;
import javax.swing.JButton;

public class Botao extends JButton {
    private Color azulEscuro = new Color(36, 9, 147);
    public Botao(String titulo){
        this.setText(titulo);
        this.setSize(80, 20);
        this.setForeground(Color.WHITE);
        this.setBackground(azulEscuro);
        this.setVisible(true);  
    }
}
