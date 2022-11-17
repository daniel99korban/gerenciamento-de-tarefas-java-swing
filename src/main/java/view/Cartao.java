package view;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import util.ArquivosProjeto;

/**
 *
 * @author daniel
 */
public class Cartao extends JPanel{
    
    public int id;
    public JLabel tituloCartao;
    public List<String> listaTarefas;
    public String nome;
    public int posicaoX;
    public int posicaoY;
    
    public Cartao(String title, int ...corCartao){
        nome = title; 
        tituloCartao = new JLabel(title);
        tituloCartao.setForeground(Color.white);
        listaTarefas = new ArrayList<>();
        
        var caixaTitulocartao = new JPanel();
        var botaoAddTarefa = new JPanel();
        
        Icon icon = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("add-icon(174.84 - 464).png"));
        var labelAddTarefa = new JLabel("Adicionar Tarefa", icon,JLabel.CENTER);
        
        caixaTitulocartao.add(tituloCartao);
        caixaTitulocartao.setBackground(new Color(corCartao[0], corCartao[1], corCartao[2]));    
        labelAddTarefa.setForeground(Color.WHITE);
        botaoAddTarefa.add(labelAddTarefa);
        botaoAddTarefa.setBackground(new Color(51, 51, 51));
        
        this.setLayout(new BorderLayout());
        this.add(BorderLayout.NORTH, caixaTitulocartao);
        this.add(BorderLayout.SOUTH, botaoAddTarefa);
        this.setBorder(new LineBorder(Color.WHITE, 1));
        this.setBackground(new Color(51, 51, 51));
        // simular tarefas no cartão(apenas teste)
        for(int i=1; i<=30; i++){
            this.addTarefa("Tarefa " + i);
        }
        // exibir tarefas no cartão
        this.exibirTarefas();
        
    }
    
    public void addTarefa(String tarefa){
        this.listaTarefas.add(tarefa);
    }
    
    public void exibirTarefas(){
        DefaultListModel model = new DefaultListModel();
        // iserir tarefas no cartão
        for(int i=0; i < listaTarefas.size(); i++){
            
            model.addElement(listaTarefas.get(i));
        }
        JList list = new JList(model);
//        list.setModel(model);
        JPanel painel = new JPanel();
        list.setVisibleRowCount(20);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // fonte
        Font fonte = new Font("Arial", Font.PLAIN, 26);
        list.setFont(fonte);
        list.setForeground(new Color(134,131,131));
        list.setBackground(new Color(51,51,51));
        this.add(new JScrollPane(list));
//	painel.setVisible(true);
        this.setBackground(Color.red);
//        painel.setSize(this.getWidth(), this.getHeight());
//        this.add(painel, BorderLayout.CENTER);
    }
    
}
