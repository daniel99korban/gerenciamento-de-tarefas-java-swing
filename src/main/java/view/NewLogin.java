
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

public class NewLogin implements ActionListener {
    JFrame frame;
    JLabel label;
    JButton botao;
    JPanel panel;
    
    

    public static void main(String[] args){
    
        NewLogin login = new NewLogin();
        login.inicio();
    
    }
   public void inicio(){
     frame = new JFrame("Tela login");  
     botao = new JButton("Entar");
     panel = new JPanel();
    // botao.addActionListener(this);
     frame.add(botao);
     frame.getContentPane().add(panel, BorderLayout.CENTER);
     frame.getContentPane().add(botao, BorderLayout.SOUTH);
     frame.setSize(1000, 600);
     frame.setVisible(true);
     frame.setLocationRelativeTo(null);
      botao.setSize(100, 30);
     
		
   }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
}