
package view;

import java.awt.Color;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author danie
 */
public class DashBoardView extends JFrame{
    
    private List<GuiasDeProjetos> guiaProjeto;
    
    public DashBoardView(String titulo){
        super(titulo);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1140, 640);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        // painel para agrupar abas de projetos
        var background = new JPanel();
        background.setLayout(null);
        background.setSize(1140, 640);
        background.setBackground(new Color(36, 37, 36));
        // Arquvos para compor a guia de projetos(apenas um teste)
        String[] nomesArquivos = {"Projeto de LPIII", "Estudos de JavaScript", "Prototipação de interfaces"};
        GuiasDeProjetos gp = new GuiasDeProjetos();
        for(int i=0; i<=2; i++){
            gp.addProjeto(nomesArquivos[i]);
        }
        // Barra de tarefas
        BarraDeFerramentas bf = new BarraDeFerramentas(this);
        // guias de projetos
        gp.setBounds(0, 36, 1140, 580);
        gp.init();
        background.add(gp);
        // add componentes no painel
        this.add(bf);
        this.add(background);
        this.setVisible(true);
    }
    
    public static void main(String[] args) {
        new DashBoardView("Software de Gerenciamento de Projetos");
    }
    
}
