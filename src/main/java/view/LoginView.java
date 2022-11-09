
package view;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author josev_ferreira
 */

public class LoginView extends JFrame {
    private Color rochoPersonalizado = new Color(51, 37, 58);
 
    public LoginView (String titulo){
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(1,2));
        this.setTitle(titulo);
        this.setSize(990, 520);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // painel para agrupar componentes de formulario de login
        JPanel formLogin = new JPanel();
        formLogin.setLayout(null);
        // colocar label de bem vindo!
        formLogin.add(new Label("E-mail", Color.WHITE)).setBounds(70, 120, 110, 40);
        formLogin.add(new TextField ("")).setBounds(70, 160, 360, 40);
        formLogin.add(new Label ("Senha", Color.WHITE)).setBounds(70, 220, 110, 40);
        formLogin.add(new TextField ("")).setBounds(70, 260, 360, 40);
        formLogin.add(new Botao("Entrar")).setBounds(70, 350, 360, 40);
        formLogin.setBackground(this.rochoPersonalizado);
        // imagem
        Icon imgCautioAction = new ImageIcon( "C:\\Users\\danie\\OneDrive\\Documentos\\NetBeansProjects\\gerenciamento-de-tarefas-java-swing\\src\\main\\java\\assets\\Task-management.png");
	JLabel labelImg = new JLabel("", imgCautioAction,JLabel.CENTER);
        // adicionar imagem e form de login
        background.add(labelImg);
        background.add(formLogin);
        background.setBackground(rochoPersonalizado);
        this.add(background);
        this.setVisible(true);
    }
   
    public static void main(String[] args){
        new LoginView("LOGIN");
    } 
}