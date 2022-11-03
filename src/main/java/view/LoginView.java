
package view;

import javax.swing.JFrame;

/**
 *
 * @author josev_ferreira
 */

public class LoginView extends JFrame {
 
    public LoginView (String Titulo){
        this.setSize(1080, 640);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.add(new Botao("Entrar")).setBounds(590, 400, 360, 40);// 480 fica no meio
        this.add(new Label("E-mail")).setBounds(590, 200, 110, 40);
        this.add(new Label ("Senha")).setBounds(590, 280, 110, 40);
        this.add(new TextField ("")).setBounds(590, 230, 360, 40);
        this.add(new TextField ("")).setBounds(590, 310, 360, 40);
    }
   
    public static void main(String[] args){
        new LoginView("LOGIN");
    } 
}