
package view;

import view.componente.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import util.ArquivosProjeto;
import view.tratadoreventos.TratadorDeEvento;

/**
 *
 * @author josev_ferreira
 */

public class LoginView extends JFrame {
    
    private Color margentaPersonalizado = new Color(51, 37, 58);
    Font fonteTitulo = new Font("Arial", Font.BOLD, 37);
    Font fonte = new Font("Arial", Font.PLAIN, 16);
    Font fontem = new Font("Arial", Font.PLAIN, 12);
    // email e senha
    TextField email;
    password senha;
    Botao botaoEntrar;
    
    public LoginView (String titulo){
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(1,2));
        this.setTitle(titulo);
        this.setSize(1040, 520);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // painel para agrupar componentes de formulario de login
        JPanel formLogin = new JPanel();
        formLogin.setLayout(null);
        formLogin.add(new Label("Bem-vindo!", Color.WHITE, fonteTitulo)).setBounds(70, 40, 300, 50);
        formLogin.add(new Label("Você ainda não tem conta?", Color.WHITE, fontem)).setBounds(70, 90, 300,30);
        
        var labelEntrar = new Label("Cadastre-se", Color.WHITE, fontem);
        labelEntrar.setBounds(225, 90, 100,30);
        labelEntrar.addMouseListener(new TratadorDeEvento(labelEntrar, this));
        
        formLogin.add(labelEntrar);
       // label.setFont(new Font("Times new Roman", Font.BOLD, 20));
        formLogin.add(new Label("E-mail", Color.WHITE, fonte)).setBounds(70, 120, 110, 40);
        email = new TextField ("", Color.GRAY);
        formLogin.add(email).setBounds(70, 160, 360, 40);
        formLogin.add(new Label ("Senha", Color.WHITE, fonte)).setBounds(70, 220, 110, 40);
        senha = new password ("", Color.GRAY);
        formLogin.add(senha).setBounds(70, 260, 360, 40);
        
        botaoEntrar = new Botao("Entrar");
        botaoEntrar.addActionListener(new TratadorDeEvento(email, senha, this));
        formLogin.add(botaoEntrar).setBounds(70, 350, 360, 40);
        formLogin.add(new CheckBox("Continue logado", margentaPersonalizado)).setBounds(70, 300, 150, 40);
        formLogin.setBackground(this.margentaPersonalizado);
        formLogin.add(new Label("esqueceu a senha?", Color.WHITE, fontem)).setBounds(225, 300, 160,40);
        // imagem
        Icon imgCautioAction = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("Task-management.png"));
	JLabel labelImg = new JLabel("", imgCautioAction,JLabel.CENTER);
        // adicionar imagem e form de login
        background.add(labelImg);
        background.add(formLogin);
        
        background.setBackground(margentaPersonalizado);
        this.add(background);
        this.setVisible(true);
    }
   
    public static void main(String[] args){
        new LoginView("LOGIN");
    } 
}