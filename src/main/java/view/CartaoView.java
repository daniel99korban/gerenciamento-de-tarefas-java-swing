package view;


import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import model.Cartao;
import model.Tarefa;
import util.ArquivosProjeto;
import view.componente.Botao;
import view.tratadoreventos.TratadorDeEvento;

/**
 *
 * @author daniel
 */
public class CartaoView extends JPanel{
    
    public JLabel tituloCartao;
    public JPanel painelListaTarefa;
    public JScrollPane scroll;
    // uma referência para o cartão a ser construido
    public Cartao cartaoModel;
    // uma referência para o cartão view
    public CartaoView cartaoView;
    public Botao botaoAddTarefa;
    public int posicaoX;
    public int posicaoY;
    
    public CartaoView(String title, Cartao cartaoModel, int ...corCartao){ 
        tituloCartao = new JLabel(title);
        this.cartaoModel = cartaoModel;
        this.cartaoView = this;
        tituloCartao.setForeground(Color.white);
        
        var caixaTitulocartao = new JPanel();
        caixaTitulocartao.add(tituloCartao);
        caixaTitulocartao.setBackground(new Color(corCartao[0], corCartao[1], corCartao[2]));    
        // botão adicionar tarefa
        botaoAddTarefa = new Botao("Adicionar Tarefa", Color.WHITE, new Color(51, 51, 51));
        botaoAddTarefa.addActionListener(new TratadorDeEvento(this));
        botaoAddTarefa.setIcon(new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("add-icon(174.84 - 464).png")));
        
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, caixaTitulocartao);
        this.add(BorderLayout.SOUTH, botaoAddTarefa);
        this.setBorder(new LineBorder(Color.WHITE, 1));
        this.setBackground(new Color(51, 51, 51));
        // simular tarefas no cartão(apenas teste) os dados serão recuparados de um BD
//        for(int i=1; i<=10; i++){
//            var t = new Tarefa("Tarefa " + i, i);
//            t.setSubTitulo("na lista " + tituloCartao.getText());
//            this.addTarefa(t);
//        }
        // exibir tarefas no cartão
        this.exibirTarefas();
        
    }
    
    public void addTarefa(Tarefa tarefa){
        this.cartaoModel.addTarefa(tarefa);
    }
    
    public JPanel construirItemTarefaView(Tarefa tarefa){// painel
        var painel = new JPanel();
        painel.setLayout(new BorderLayout());
        painel.add(new JLabel(tarefa.getTitulo()), BorderLayout.WEST);
        var botao = new Botao("Abrir Tarefa", Color.WHITE, new Color(163, 151,151));
        botao.setBorderPainted(false);
        botao.addActionListener(new TratadorDeEvento(this, tarefa));
        painel.add(botao, BorderLayout.EAST);
        painel.setSize(200, 200);
        painel.setBorder(new LineBorder(Color.WHITE, 2));
        painel.setVisible(true);
        return painel;
    }
    
    public void exibirTarefas(){// as tarefas serão exibidas dentro de um scroll em cada cartão
        painelListaTarefa = new JPanel();
        painelListaTarefa.setLayout(new BoxLayout(painelListaTarefa, BoxLayout.Y_AXIS));
        // loop para simular inserção de elementos
        scroll = new JScrollPane(painelListaTarefa);
        for(int i=0; i < this.cartaoModel.getListaTarefas().size(); i++){
            String nomeTarefa = this.cartaoModel.getTarefa(i).getTitulo();
            painelListaTarefa.add(
                    this.construirItemTarefaView(this.cartaoModel.getTarefa(i)));
        }
        painelListaTarefa.setForeground(new Color(134,131,131));
        painelListaTarefa.setBackground(new Color(51,51,51));
        this.add(scroll);
    }
    
    /***
     * função/gambiarra para que a view se comporte de maneira adequada apos cada inclusão/exclusão
     * @param cartaoView
     * @param gap valor a ser calculado a medida que o cartão se expande coma inserção de tarefas
     */
    public void configurarExpansaoCard(CartaoView cartaoView, int gap){
        if(cartaoView.cartaoModel.getListaTarefas().size() >= 10){
            cartaoView.setSize(cartaoView.getWidth(), 450);
        }else{
            // se cartao não estiver vazio!
            if(!cartaoView.cartaoModel.getListaTarefas().isEmpty()){
                cartaoView.setSize(cartaoView.getWidth(), cartaoView.getHeight() + gap);
            }
        }
    }

}
