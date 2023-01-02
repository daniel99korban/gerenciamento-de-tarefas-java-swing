
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import model.Projeto;
import view.tratadoreventos.TratadorDeEvento;

// PROXIMA MISSÃO É PROGRAMAR PARA QUE OS PROJETOS SO APARECAM QUANDO EU CLICAR NELES
/**
 * @author danie
 */
public class DashBoardView extends JFrame{
    // variavel global que será util nas operações com a view(desativar/ativar view em certos momentos)
    public static DashBoardView instanciaDashBoard;
    // arvores com lista de projetos do usuario
    private JTree arvoreProjetos = null;
    private DefaultMutableTreeNode raiz = null;
    public JSplitPane split;
    public JPanel background;
    public JScrollPane scrollBackground;
    // guias de projetos
    public GuiasDeProjetos guiasProjeto = new GuiasDeProjetos();
    
    public DashBoardView(String titulo){
        super(titulo);
        instanciaDashBoard = this;
         // split pane
        split = new JSplitPane();
        raiz = new DefaultMutableTreeNode("Meus Projetos");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        this.setSize(1340, 640);// width : 1140
        this.setSize(// obter as dimensões da tela do notebook
            Toolkit.getDefaultToolkit().getScreenSize().width,
            Toolkit.getDefaultToolkit().getScreenSize().height
        );
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout());
        // painel para agrupar abas de projetos
        background = new JPanel();
        background.setLayout(null);
        background.setSize(1140, 640);
        background.setBackground(new Color(36, 37, 36));
////        // Arquvos para compor a guia de projetos(apenas um teste) os dados serão recuperados de um BD
////        String[] nomesArquivos = {"Projeto de LPIII", "Estudos de Java", "Prototipação de interfaces"};
////        for(int i=0; i<=2; i++){
////            guiasProjeto.addProjeto(nomesArquivos[i]);
////        }
        this.carregarProjetosNaGuiaProjetos();
//
//        // Barra de tarefas
        BarraDeMenu barraTarefa = new BarraDeMenu(this);
        // guias de projetos
        guiasProjeto.setBounds(0, 36, 1140, 580);
        guiasProjeto.init();
        background.add(guiasProjeto);
        // add componentes no painel
        this.add(barraTarefa, BorderLayout.NORTH);
        // lado esquerdo do split
        this.montarArvore();
        this.criarNosDaArvore();
        var scrollProjetos = new JScrollPane();
        arvoreProjetos.setBackground(new Color(36, 37, 36));
        arvoreProjetos.setForeground(new Color(36, 37, 36));
        scrollProjetos.setViewportView(arvoreProjetos);
        scrollProjetos.setMinimumSize(new Dimension(150, 0));
        split.setLeftComponent(scrollProjetos);
        // lado direito do spllit
        scrollBackground = new JScrollPane();
        scrollBackground.setViewportView(background);
        split.setRightComponent(scrollBackground);
        this.add(split, BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    public void carregarProjetosNaGuiaProjetos(){// esta função teria q ser chamada a cada inserção de um novo projeto?
        if(TratadorDeEvento.usuarioLogado.getProjetos().size() == 0){
            System.out.println("\n\nNenhum projeto criado!\n\n\n");
//            this.guiasProjeto.addProjeto("nenhum projeto");// if senhum projeto
        }else{
            System.out.println("\n\nCaiu no else\n\n\n");
            for(Projeto projeto : TratadorDeEvento.usuarioLogado.getProjetos()){
                if(projeto==null){
                    System.out.println("\n\n\nMODEL PROJETO NULLO(carregardados)\n\n\n");
                    break;
                }
                String nomeProjeto = projeto.getNomeProjeto();
                this.guiasProjeto.addProjeto(nomeProjeto);
            }
        }
    }
    
    private JTree montarArvore() {
	if (arvoreProjetos == null) {
            arvoreProjetos = new JTree(raiz);
            arvoreProjetos.setBounds(new Rectangle(12, 46, 155, 210));      
            // Add a listener
            arvoreProjetos.addTreeSelectionListener(new TratadorDeEvento());	
	}
	arvoreProjetos.getSelectionModel().setSelectionMode(
           TreeSelectionModel.SINGLE_TREE_SELECTION
        );
        return null;
    }
    /**
     * A função ler arquivos de projetos ja criados pelo usuario
     */
    private void criarNosDaArvore() {
        // cada item do projeto
        DefaultMutableTreeNode projeto = null;
        
        for(ProjetoView pv : guiasProjeto.getProjetos()){
            projeto = new DefaultMutableTreeNode(pv.getNomeProjeto());
            raiz.add(projeto);
        }
    }

    public static void main(String[] args) {// so para testes
        new DashBoardView("Software de Gerenciamento de Projetos");
    }
    
}
