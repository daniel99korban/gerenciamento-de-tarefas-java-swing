
package view;

import javax.swing.JFrame;
import javax.swing.JButton;


/**
 *
 * @author Daniel Lima, Jose Victor & Paulo Roberto
 */
public class LoginView extends JFrame{
 
    // Apenas um exemplo
    public LoginView(String titulo){
        this.setTitle(titulo);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        JButton botao = new JButton("Entrar");
        
        this.add(botao);
    }
    
    
    
    public static void main(String[] args) {
        new LoginView("Tela de Login");
        
    }
    
}


