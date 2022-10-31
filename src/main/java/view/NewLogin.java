
package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author josev_ferreira
 */

public class NewLogin extends JFrame {
 
   public NewLogin (String Titulo){
    
    // botao.addActionListener(this);
     this.setSize(1080, 640);
     this.setVisible(true);
     this.setLocationRelativeTo(null);
     this.setLayout(null);
     this.add(new Botao("Entrar")).setBounds(590, 400, 360, 40);// 480 fica no meio
     this.add(new Label("E-mail")).setBounds(590, 200, 110, 40);
     this.add(new Label ("Senha")).setBounds(590, 280, 110, 40);
     this.add(new TextField ("")).setBounds(590, 230, 360, 40);
     this.add(new TextField ("")).setBounds(590, 310, 360, 40);
     
      }

   
 public static void main(String[] args){
   new NewLogin("LOGIN");
    } 
}