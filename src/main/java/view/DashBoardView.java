
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.List;
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
    private List<GuiasDeProjetos> guiaProjeto;// vai ter minhas listas de projetos
    // arvores com lista de projetos do usuario
    private JTree arvoreProjetos = null;
    private DefaultMutableTreeNode raiz = null;
    private JSplitPane split;
    // teste
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
        var background = new JPanel();
        background.setLayout(null);
        background.setSize(1140, 640);
        background.setBackground(new Color(36, 37, 36));
////        // Arquvos para compor a guia de projetos(apenas um teste) os dados serão recuperados de um BD
////        String[] nomesArquivos = {"Projeto de LPIII", "Estudos de Java", "Prototipação de interfaces"};
////        for(int i=0; i<=2; i++){
////            guiasProjeto.addProjeto(nomesArquivos[i]);
////        }
         this.exibirGuiasDeprojeto();
////        for(Projeto projeto : usuario.getProjetos()){
////            for(String nomeProjeto : projeto.getNomeProjeto()){
////                this.guiasProjeto.addProjeto(nomeProjeto);
////            }
////        }
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
        var scrollBackground = new JScrollPane();
        scrollBackground.setViewportView(background);
        split.setRightComponent(scrollBackground);
        this.add(split, BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    public void exibirGuiasDeprojeto(){// esta função teria q ser chamada a cada inserção de um novo projeto?
        if(TratadorDeEvento.usuarioLogado.getProjetos().size() == 0){
            System.out.println("Nenhum projeto criado!");
//            this.guiasProjeto.addProjeto("nenhum projeto");// if senhum projeto
        }else{
            for(Projeto projeto : TratadorDeEvento.usuarioLogado.getProjetos()){
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
