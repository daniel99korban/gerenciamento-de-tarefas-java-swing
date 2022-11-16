
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;

/**
 *
 * @author danie
 * contem uma lista de projetos
 */
public class GuiasDeProjetos extends JTabbedPane{
    
    private List<PainelProjeto> projetos; 

    public GuiasDeProjetos() {
        this.projetos = new ArrayList<>();
    }
    
    public void addProjeto(String nomeProjeto){
        // painel de tarefas
        PainelProjeto pj = new PainelProjeto(nomeProjeto);
        pj.setBounds(0, 36, 1140, 580);
        projetos.add(pj);
    }
    
//    public void simulacao(){
//        String[] nomesArquivos = {"Projeto de LPIII", "Estudos de JavaScript", "Prototipação de interfaces"};
//        for(int i=0; i<=2; i++){
//            this.addProjeto(nomesArquivos[i]);
//        }
//    }

    public List<PainelProjeto> getProjetos() {
        return projetos;
    }
    
    public void init(){
//        this.simulacao();
        for(PainelProjeto pj : this.projetos){
            pj.init();
            pj.exibirCartoes();
            this.addTab(pj.getNomeProjeto() ,pj);
        }
        this.setSelectedIndex(0); //selecionado
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
