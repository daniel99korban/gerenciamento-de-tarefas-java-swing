
package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.componente.*;
import view.tratadoreventos.TratadorDeEvento;

/**
 *
 * @author danie
 */
public class CadastroView extends JFrame{
        private Color margentaPersonalizado = new Color(51, 37, 58);
        Font fonteTitulo = new Font("Arial", Font.BOLD, 37);
        Font fonte = new Font("Arial", Font.PLAIN, 16);
        Font fontem = new Font("Arial", Font.PLAIN, 12);
        // email e senha
        TextField email;
        password senha;
        Botao botaoCadastrar;

    public CadastroView() {
        JPanel background = new JPanel();
        background.setLayout(new GridLayout(1,1));
        this.setTitle("Cadastre-se");
        this.setSize(510, 520);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // painel para agrupar componentes de formulario de login
        JPanel formCadastro = new JPanel();
        formCadastro.setLayout(null);
        formCadastro.add(new Label("Cadastre-se!", this.margentaPersonalizado, fonteTitulo)).setBounds(70, 40, 300, 50);
        formCadastro.add(new Label("Você ja possui uma conta?", this.margentaPersonalizado, fontem)).setBounds(70, 90, 300,30);
        var labelEntrar = new Label("Entrar", this.margentaPersonalizado, fontem);
        labelEntrar.setBounds(225, 90, 100,30);
        labelEntrar.addMouseListener(new TratadorDeEvento(labelEntrar, this));
        
        formCadastro.add(labelEntrar);
       // label.setFont(new Font("Times new Roman", Font.BOLD, 20));
        formCadastro.add(new Label("E-mail ou nome de Usuario", this.margentaPersonalizado, fonte)).setBounds(70, 120, 200, 40);
        email = new TextField ("", Color.GRAY);
        formCadastro.add(email).setBounds(70, 160, 360, 40);
        formCadastro.add(new Label ("Informe uma senha", this.margentaPersonalizado, fonte)).setBounds(70, 220, 200, 40);
        senha = new password ("", Color.GRAY);
        formCadastro.add(senha).setBounds(70, 260, 360, 40);
        
        botaoCadastrar = new Botao("Cadastrar");
        botaoCadastrar.setBackground(margentaPersonalizado);
        botaoCadastrar.addActionListener(new TratadorDeEvento(email, senha, this));
        formCadastro.add(botaoCadastrar).setBounds(70, 350, 360, 40);
        formCadastro.setBackground(Color.WHITE);
       
        background.add(formCadastro);
        background.setBackground(Color.WHITE);
        this.add(background);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new CadastroView();
    }
    
}
