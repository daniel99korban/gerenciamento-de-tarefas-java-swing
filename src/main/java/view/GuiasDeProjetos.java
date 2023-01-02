
package view;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;
import view.tratadoreventos.TratadorDeEvento;

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
//        this.simulacao();
        int i = 0;
        for(ProjetoView pj : this.projetosView){
            if(pj==null){
                System.out.println("\n\n\nPROJETO VIEW\n\n\n");
                break;
            }
            if(TratadorDeEvento.usuarioLogado.getProjetos().size() < (i+1)){
                // o carregamento de projeto do usuario foi feito completamente
                break;
            }
            pj.init(i);
            i++;
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
