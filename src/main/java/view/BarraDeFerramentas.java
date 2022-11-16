
package view;

import view.componente.Label;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import util.ArquivosProjeto;

/**
 *
 * @author danie
 */
public class BarraDeFerramentas extends JToolBar{

    public BarraDeFerramentas(Component c){
        this.setSize(c.getWidth(), 40);
        this.setLayout(new GridLayout(1, 3, 80, 0));
        // Determinar se a barra de ferramentas pode se mover pelo container:
        this.setFloatable(false);
        this.setBackground(new Color(51, 51, 51));
        // configurações de menu
        var painelMenu = new JPanel();
        painelMenu.setLayout(new GridLayout(1, 4, 10, 0));
        Icon checkIcon = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("check-icon.png"));
        var labelCheckIcon = new JLabel("", checkIcon,JLabel.CENTER);
        painelMenu.add(labelCheckIcon);
        painelMenu.add(new Label("Arquivos", Color.WHITE));
        painelMenu.add(new Label("Editar", Color.WHITE));
        painelMenu.add(new Label("Ajuda", Color.WHITE));
        painelMenu.setBackground(new Color(51, 51, 51));
        // label de alterar nome do projeto
        Icon pencilIcon = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("pencil-icon.png"));
        var labelPencilIcon = new JLabel("undefined", pencilIcon,JLabel.CENTER); 
        labelPencilIcon.setForeground(Color.WHITE);
        // icon de login de usuario
        var nomeUsuario = new Label("A");
        Icon circulo = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("Ellipse.png"));
        var labelCirculo = new JLabel("", circulo,JLabel.CENTER); 
        
        var painelLoginUsu = new JPanel();
        painelLoginUsu.setLayout(null);
        painelLoginUsu.setBackground(new Color(51, 51, 51));
        // posicionando os elementos
        nomeUsuario.setBounds(277, -22, 80, 80);
        labelCirculo.setBounds(240, -22, 80, 80);
        painelLoginUsu.add(nomeUsuario);
        painelLoginUsu.add(labelCirculo);
        // adicionar elementos
        this.add(painelMenu);
        this.add(labelPencilIcon);
        this.add(painelLoginUsu);
        this.setVisible(true);
    }
    
}
