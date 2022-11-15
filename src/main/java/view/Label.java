/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;

/**
 *
 * @author josev_ferreira
 */
public class Label extends JLabel {
    /**
     * @param titulo será exatamente o texto que aparecerá na tela.
     * @param cor o programador tem a opção de alterar cor, caso use o outro construtor, por default a cor será black.
     */
    public Label(String titulo, Color cor, Font fonte){
        this.setText(titulo);
        this.setForeground(cor);
        this.setFont(fonte);
    }
    
    public Label(String titulo){
        this.setText(titulo);
    }

    
    
}
