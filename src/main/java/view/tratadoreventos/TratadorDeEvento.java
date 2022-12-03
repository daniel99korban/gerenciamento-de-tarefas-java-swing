
package view.tratadoreventos;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import model.Tarefa;
import util.ArquivosProjeto;
import view.CartaoView;
import view.DashBoardView;
import view.PainelProjeto;
import view.TarefaView;
import view.componente.Botao;

/**
 *
 * @author danie
 */
public class TratadorDeEvento implements ActionListener, MouseListener{
    
    private CartaoView cartaoView;
    private List<CartaoView> cartoesView;
    private Tarefa tarefaModel;
    private static TarefaView tarefaView;

    public TratadorDeEvento(List<CartaoView> cartoesView, CartaoView cartaoView, Tarefa tarefaModel) {
        this.cartoesView = cartoesView;
        this.cartaoView = cartaoView;
        this.tarefaModel = tarefaModel;
    }
    
    public TratadorDeEvento(CartaoView cartaoView) {
        this.cartaoView = cartaoView;
    }

    public TratadorDeEvento(Tarefa tarefaModel) {
        this.tarefaModel = tarefaModel;
    }
    
    public TratadorDeEvento() {}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Adicionar uma nova tarefa
        if(e.getActionCommand().equals("Adicionar Tarefa")){
            adicionarTarefa(e);
        }
        if(e.getActionCommand().equals("Excluir Tarefa")){
            removerTarefa("excluir");
        }
        if(e.getActionCommand().equals("Mover Cartão")){
            moverCartao(e);
        }
        abrirTarefa(e);
    }
    
    private void adicionarTarefa(ActionEvent e) {
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
            
            if(res != null){// se a resposta for cancelar a expansão do card não acontece 
                cartaoView.configurarExpansaoCard(cartaoView, 40);
            }
            
            DashBoardView.instanciaDashBoard.repaint();
            origem.setBackground(corFundoOriginal);
            origem.repaint();
       
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
    
    /**
     * 
     * @param operacao vai permitir o seu reaproveitamento
     */
    private void removerTarefa(String operacao) {
            // fazer uma busca a saber qual  tarefa deseja-se remover
            for(Tarefa t : cartaoView.cartaoModel.getListaTarefas()){
                if(tarefaModel.getId() == t.getId()){
                    // confirmacao de remoção de cartão
                    if(operacao.equalsIgnoreCase("excluir")){
                        int res = JOptionPane.showConfirmDialog(null, "Deseja excluir tarefa ["+t.getTitulo()+"]?", "Remover Tarefa", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                        if(res == 1){//  0 -> sim 1 -> não
                            return;
                        }
                    }
                    cartaoView.cartaoModel.removerTarefa(tarefaModel.getId());
                    break;
                }
            }
            // atualizar painel atual de tarefas;
            cartaoView.remove(cartaoView.scroll);
            cartaoView.exibirTarefas();
            // pra resolver o bug de remoção do ultimo item
            if(cartaoView.cartaoModel.getListaTarefas().size()==0){
                cartaoView.setSize(cartaoView.getWidth(), 60);// height -> tamanho inicial do frame
            }else{
                cartaoView.configurarExpansaoCard(cartaoView, -40);
            }
            
            tarefaView.setVisible(false); // fechar tarefa após a exclusão
            DashBoardView.instanciaDashBoard.setEnabled(true);
            DashBoardView.instanciaDashBoard.repaint();
            DashBoardView.instanciaDashBoard.setVisible(true);
    }
    
    private void abrirTarefa(ActionEvent e){
         // abrir uma tarefa
        if(e.getActionCommand().equals("Abrir Tarefa")){
            tarefaView = new TarefaView(cartoesView, cartaoView, tarefaModel);
            tarefaView.iniciarComponentes();
            tarefaView.construirPainelTarefa();
            tarefaView.setVisible(true);
            DashBoardView.instanciaDashBoard.setEnabled(false);
        }
    }

    private void moverCartao(ActionEvent e) {
        // titulos para as opcoes
        String[] labels = {"A Fazer","A Fazer Hoje", "Em Progresso", "Feito"};

        var opcoes = new ButtonGroup();
        List<JRadioButton> botoes = new ArrayList<>();
        Object[] params = new Object[labels.length];
        for(int i=0; i<labels.length; i++){
            JRadioButton radioButon = new JRadioButton(labels[i], false);
            botoes.add(radioButon);
            // indentificar cartão onde se econtra a tarefa exibida
            if(labels[i].equalsIgnoreCase(cartaoView.tituloCartao.getText())){
                radioButon.setForeground(Color.GREEN);
                radioButon.setSelected(true);
            }
            // adicionando eventos
            radioButon.addMouseListener(new TratadorDeEvento());
            opcoes.add(radioButon);
            params[i] = radioButon;
        }

        String message = "Pra onde você deseja mover o cartão?";
        int res = JOptionPane.showConfirmDialog(null, params, "Disconnect Products", JOptionPane.YES_NO_OPTION);
        // 0 -> sim | 1 -> não
        if(res == 0){
            // varer uma lista de radio buttons para saber qual opção escolhida
            for(JRadioButton rb : botoes){
                if(rb.isSelected()){
                    PainelProjeto.instanciaPainelProjeto.getCartoes();// lista de cartõesView
                    // remover a tarefa desta lista atual
                    // copia da tarefa a ser removida
                    Tarefa tarefa = cartaoView.cartaoModel.getTarefa(tarefaModel.getId());
                    // remover do model original
                    removerTarefa("mover");// apenas atualizar sua atual localização
                    // recuperando a tarefa para adiciona-la em outro cartão
                    this.tarefaModel = tarefa;// parei aqui
                    // atualizar cartão view
                    for(CartaoView cartoesView : this.cartoesView){
                        if(rb.getText().equalsIgnoreCase(cartoesView.tituloCartao.getText())){
                            cartaoView = cartoesView;
                            break;
                        }
                    }
                    // adicionar na lista indicada
                    tarefaModel.setSubTitulo("na lista " + cartaoView.tituloCartao.getText());
                    cartaoView.cartaoModel.addTarefa(tarefaModel);  
                    cartaoView.remove(cartaoView.scroll);
                    cartaoView.exibirTarefas();
                    // expandir cartão de tarefa
                    cartaoView.configurarExpansaoCard(cartaoView, 40);
                    DashBoardView.instanciaDashBoard.repaint();
                    break;
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        Object origem = e.getSource();
        if(origem instanceof JRadioButton){
            // manter marcado cartão que indica onde a tarefa esta
            if(((JRadioButton) origem).getForeground() == Color.GREEN){
                return;
            }
            ((JRadioButton) origem).setForeground(Color.GRAY);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        Object origem = e.getSource();
        if(origem instanceof JRadioButton){
            // manter marcado cartão que indica onde a tarefa esta
            if(((JRadioButton) origem).getForeground() == Color.GREEN){
                return;
            }
            ((JRadioButton) origem).setForeground(Color.BLACK);
        }
    }
    
}
