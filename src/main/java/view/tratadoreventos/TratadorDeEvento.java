
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
    
    public TratadorDeEvento(CartaoView cartaoView) {
        this.cartaoView = cartaoView;
    }

    public TratadorDeEvento(Tarefa tarefaModel) {
        this.tarefaModel = tarefaModel;
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
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
                var t = new Tarefa(res);
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
        // abrir uma tarefa
        else if(e.getActionCommand().equals("Abrir Tarefa")){
            var tarefView = new TarefaView(tarefaModel);
                tarefView.iniciarComponentes();
                tarefView.construirPainelTarefa();
                tarefView.setVisible(true);
                DashBoardView.instanciaDashBoard.setEnabled(false);
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
    
}
