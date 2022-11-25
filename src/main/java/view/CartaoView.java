package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import model.Cartao;
import model.Tarefa;
import util.ArquivosProjeto;
import view.componente.Botao;

/**
 *
 * @author daniel
 */
public class CartaoView extends JPanel{
    
    public JLabel tituloCartao;
    // uma referência para o cartão a ser construido
    public Cartao cartaoModel;
    public Botao botaoAddTarefa;
    public int posicaoX;
    public int posicaoY;
    
    public CartaoView(String title, Cartao cartaoModel, int ...corCartao){ 
        tituloCartao = new JLabel(title);
        this.cartaoModel = cartaoModel;
        tituloCartao.setForeground(Color.white);
        
        var caixaTitulocartao = new JPanel();
        // botão adicionar tarefa
        caixaTitulocartao.add(tituloCartao);
        caixaTitulocartao.setBackground(new Color(corCartao[0], corCartao[1], corCartao[2]));    
        botaoAddTarefa = new Botao("Adicionar Tarefa", Color.WHITE, new Color(51, 51, 51));
        botaoAddTarefa.setIcon(new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("add-icon(174.84 - 464).png")));
        
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, caixaTitulocartao);
        this.add(BorderLayout.SOUTH, botaoAddTarefa);
        this.setBorder(new LineBorder(Color.WHITE, 1));
        this.setBackground(new Color(51, 51, 51));
        // simular tarefas no cartão(apenas teste) os dados serão recuparados de um BD
        for(int i=1; i<=30; i++){
            var t = new Tarefa("Tarefa " + i);
            t.setSubTitulo("na lista " + tituloCartao.getText());
            this.addTarefa(t);
        }
        // exibir tarefas no cartão
        this.exibirTarefas();
        
    }
    
    public void addTarefa(Tarefa tarefa){
        this.cartaoModel.addTarefa(tarefa);
    }
    
    public JPanel construirItemTarefaView(Tarefa tarefa){// painel
        var p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(new JLabel(tarefa.getTitulo()), BorderLayout.CENTER);
        var b = new Botao("Abrir Tarefa", Color.WHITE, new Color(163, 151,151)); 
        b.addActionListener((ActionEvent e) -> {
            // abrir tarefa
            if(e.getActionCommand().equals(("Abrir Tarefa"))){
                var f = new TarefaView(tarefa);
                f.iniciarComponentes();
                f.construirPainelTarefa();
                f.setVisible(true);
                DashBoardView.instanciaDashBoard.setEnabled(false);
            }
        });
        p.add(b, BorderLayout.EAST);
        p.setSize(200, 200);
        p.setBorder(new LineBorder(Color.WHITE, 2));
        p.setVisible(true);
        return p;
    }
    
    public void exibirTarefas(){
        var painelListaTarefa = new JPanel();
        var scroll = new JScrollPane(painelListaTarefa);
        painelListaTarefa.setLayout(new BoxLayout(painelListaTarefa, BoxLayout.Y_AXIS));
        // loop para simular inserção de elementos
        for(int i=0; i < this.cartaoModel.getListaTarefas().size(); i++){
            String nomeTarefa = this.cartaoModel.getTarefa(i).getTitulo();
            painelListaTarefa.add(
                    this.construirItemTarefaView(this.cartaoModel.getTarefa(i)));
        }
        painelListaTarefa.setForeground(new Color(134,131,131));
        painelListaTarefa.setBackground(new Color(51,51,51));
        this.add(scroll);
    }
    
}
