
package view;

import view.componente.Label;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import util.ArquivosProjeto;

/**
 *
 * @author danie
 */
public class BarraDeMenu extends JToolBar{

    public BarraDeMenu(Component c){
        this.setSize(c.getWidth(), 40);
        this.setLayout(new GridLayout(1, 3, 80, 0));
        // Determinar se a barra de ferramentas pode se mover pelo container:
        this.setFloatable(false);
        this.setBackground(new Color(51, 51, 51));
        // configurações de menu
        this.construirMenu();
        // label de alterar nome do projeto
        Icon pencilIcon = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("pencil-icon.png"));
        var labelPencilIcon = new JLabel("undefined", pencilIcon,JLabel.CENTER); 
        labelPencilIcon.setForeground(Color.WHITE);
        // icon de login de usuario
        var nomeUsuario = new Label("Conectado");
        nomeUsuario.setForeground(Color.WHITE);
//        Icon circulo = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("Ellipse.png"));
//        var labelCirculo = new JLabel("", circulo,JLabel.CENTER); 
        
        var painelLoginUsu = new JPanel();
        painelLoginUsu.setLayout(null);
        painelLoginUsu.setBackground(new Color(51, 51, 51));
        // posicionando os elementos
//        nomeUsuario.setBounds(277, -22, 80, 80);
//        labelCirculo.setBounds(240, -22, 80, 80);
        nomeUsuario.setBounds(240, -22, 80, 80);
        painelLoginUsu.add(nomeUsuario);
//        painelLoginUsu.add(labelCirculo);
//        painelLoginUsu.add(labelCirculo);
        // adicionar elementos
        this.add(labelPencilIcon);
        this.add(painelLoginUsu);
        this.setVisible(true);
    }
    
    private void construirMenu(){
        var painelMenu = new JPanel();
        painelMenu.setLayout(new GridLayout());
        var checkIcon = new JLabel();
        checkIcon.setIcon(new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("check-icon.png")));
        painelMenu.add(checkIcon);
        painelMenu.setBackground(new Color(51, 51, 51));
        
        JMenuBar bar = new JMenuBar();
	JMenu m1 = new JMenu("Arquivos");
	JMenu m2 = new JMenu("Editar");
	JMenu m3 = new JMenu("Ajuda");
        m1.setForeground(Color.WHITE);
        m2.setForeground(Color.WHITE);
        m3.setForeground(Color.WHITE);
        
        JMenuItem item1M1 = new JMenuItem("Projetos recentes");
        JMenuItem item2M1 = new JMenuItem("Criar Novo Projeto");
        JMenuItem item1M2 = new JMenuItem("Config editor");
        JMenuItem item1M3 = new JMenuItem("Principais Comandos");
	
	bar.add(m1);
	bar.add(m2);
	bar.add(m3);
	m1.add(item1M1);
	m1.add(item2M1);
	m2.add(item1M2);
	m3.add(item1M3);
	bar.setBackground(new Color(51, 51, 51));
	painelMenu.add(bar);	
        this.add(painelMenu);
    }
    
}
