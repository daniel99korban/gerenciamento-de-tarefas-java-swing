
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;

/**
 *
 * @author danie
 contem uma lista de projetosView
 */
public class GuiasDeProjetos extends JTabbedPane{
    
    private List<ProjetoView> projetosView; 

    public GuiasDeProjetos() {
        this.projetosView = new ArrayList<>();
    }
    
    public void addProjeto(String nomeProjeto){
        // painel de tarefas
        ProjetoView pj = new ProjetoView(nomeProjeto);
        pj.setBounds(0, 36, 1140, 580);
        projetosView.add(pj);
    }
    
//    public void simulacao(){
//        String[] nomesArquivos = {"Projeto de LPIII", "Estudos de JavaScript", "Prototipação de interfaces"};
//        for(int i=0; i<=2; i++){
//            this.addProjeto(nomesArquivos[i]);
//        }
//    }

    public List<ProjetoView> getProjetos() {
        return projetosView;
    }
    
    public void init(){
        System.out.println("chamou init dentro de guas projetos");
//        this.simulacao();
        for(ProjetoView pj : this.projetosView){
            pj.init();
            pj.exibirCartoes();
            this.addTab(pj.getNomeProjeto() ,pj);
        }
        //this.setSelectedIndex(0); //selecionado
    }
//    public static void main(String[] args) {
//        var f = new JFrame();
//        var tbp = new GuiasDeProjetos();
//        tbp.init();
//    
//        f.setSize(tbp.getWidth(), tbp.getHeight());
//        f.setLocationRelativeTo(null);
//        f.setLayout(null);
//        f.add(tbp);
// 
//        f.setVisible(true);
//    }
}
