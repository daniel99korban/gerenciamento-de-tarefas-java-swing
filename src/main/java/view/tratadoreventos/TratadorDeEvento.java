
package view.tratadoreventos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import model.Tarefa;
import util.ArquivosProjeto;
import view.CartaoView;
import view.DashBoardView;
import view.TarefaView;
import view.componente.Botao;

/**
 *
 * @author danie
 */
public class TratadorDeEvento implements ActionListener{
    
    private CartaoView cartaoView;
    private Tarefa tarefaModel;

    public TratadorDeEvento(CartaoView cartaoView, Tarefa tarefaModel) {
        this.cartaoView = cartaoView;
        this.tarefaModel = tarefaModel;
    }
    
    public TratadorDeEvento(CartaoView cartaoView) {
        this.cartaoView = cartaoView;
    }

    public TratadorDeEvento(Tarefa tarefaModel) {
        this.tarefaModel = tarefaModel;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        adicionarTarefa(e);
        abrirTarefa(e);
        removerTarefa(e);
    }
    
    private void adicionarTarefa(ActionEvent e) {
        // Adicionar uma nova tarefa
        if(e.getActionCommand().equals("Adicionar Tarefa")){
            Botao origem = (Botao) e.getSource();
            Color corFundoOriginal = origem.getBackground();// guardar cor de fundo orginal
            // identificar visualmente aonde será inserida a tarefa
            origem.setBackground(Color.GRAY);
            String res = obterInput();
            // válidar entrada de usuáiro(se o usuário cancelar ou deixar o input vazio, nada acontece)
            if(res != null && !res.isEmpty()){
                // cria-se e se adiciona uma nova tarefa
                Tarefa t;
                // verificar se o cartão esta vazio
                if(cartaoView.cartaoModel.getListaTarefas().isEmpty()){
                    t = new Tarefa(res, 0);
                }else{
                    int ultimaTarefa = cartaoView.cartaoModel.getListaTarefas().size()-1;
                    t = new Tarefa(res, cartaoView.cartaoModel.getTarefa(ultimaTarefa).getId()+1);
                }
                t.setSubTitulo("na lista " + cartaoView.tituloCartao.getText());
                cartaoView.cartaoModel.addTarefa(t);   
            }
            cartaoView.remove(cartaoView.scroll);
            cartaoView.exibirTarefas();
            cartaoView.configurarExpansaoCard(cartaoView, 40);
            
            DashBoardView.instanciaDashBoard.repaint();
            origem.setBackground(corFundoOriginal);
            origem.repaint();
        }
       
    }
    
    private String obterInput(){// nome de tarefa
        return (String) JOptionPane.showInputDialog(
                null,
                "Nome para a tarefa", "Escolha um nome para sua tarefa",
                JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("check-icon.png")),
                null,
                "sem nome");
    }
    
    private void removerTarefa(ActionEvent e) {
        if(e.getActionCommand().equals("Excluir Tarefa")){
            // fazer uma busca a saber qual  tarefa deseja-se remover
            for(Tarefa t : cartaoView.cartaoModel.getListaTarefas()){
                if(tarefaModel.getId() == t.getId()){
                    cartaoView.cartaoModel.removerTarefa(tarefaModel.getId());
                    break;
                }
            }
            // atualizar painel atual de tarefas;
            cartaoView.remove(cartaoView.scroll);
            cartaoView.exibirTarefas();
            // pra resolver o bug de remoção do ultimo item
            if(cartaoView.cartaoModel.getListaTarefas().size()==0){
                cartaoView.setSize(cartaoView.getWidth(), 60);
            }else{
                cartaoView.configurarExpansaoCard(cartaoView, -40);
            }
            DashBoardView.instanciaDashBoard.repaint();
        }
    }
    
    private void abrirTarefa(ActionEvent e){
         // abrir uma tarefa
        if(e.getActionCommand().equals("Abrir Tarefa")){
            var tarefView = new TarefaView(cartaoView, tarefaModel);
            tarefView.iniciarComponentes();
            tarefView.construirPainelTarefa();
            tarefView.setVisible(true);
            DashBoardView.instanciaDashBoard.setEnabled(false);
        }
    }
    
}
