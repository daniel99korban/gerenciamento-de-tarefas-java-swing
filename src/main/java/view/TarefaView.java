
package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import util.ArquivosProjeto;
import model.Tarefa;
import view.componente.Botao;
import view.componente.CheckBox;
import view.componente.Label;
import view.tratadoreventos.TratadorDeEvento;
import java.util.List;

/**
 *
 * @author danie
 */
public class TarefaView extends JFrame{

    private GridBagConstraints c;
    // opcionais da tarefa
    private Botao addCheckList;
    private Botao addDatas;
    private Botao addAnexo;
    private Botao moverCartao;
    private Botao botaoEditar;
    private Botao excluirTarefa;
    // uma referência para tarefa a ser construida
    private Tarefa tarefa;
    // uma referência para uma view do cartão
    private CartaoView cartaoView;
    // uma referência para uma lista de cartoes view
    private List<CartaoView> cartoesView;
    /***
     * @param cartoesView
     * @param cartaoView view de onde deve vir o cartao
     * @param tarefa tarefa a ser exibida na view da tarefa 
     */
    public TarefaView(List<CartaoView> cartoesView, CartaoView cartaoView, Tarefa tarefa){
        super("Visulizar Tarefa");
        this.tarefa = tarefa;
        this.cartaoView = cartaoView;
        this.cartoesView = cartoesView;
        this.setSize(790, 450);
        this.setLocationRelativeTo(null);
        //Adiciona um escutador de eventos para o evento de fechar o frame
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt){
                DashBoardView.instanciaDashBoard.setEnabled(true);
                DashBoardView.instanciaDashBoard.repaint();
            }
        });
    }
    
    public void iniciarComponentes(){
        c = new GridBagConstraints();
        c.weightx = 1.0;
        c.weighty = 1.0;
//        c.fill = GridBagConstraints.BOTH;// componente ocupa todo os espaco disponivel
//        c.fill = GridBagConstraints.RELATIVE;// Ocupa o tamanho necessrio para o conteudo
//        c.fill = GridBagConstraints.NONE;// sem padding
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 5;
        c.ipady = 10;
    }
    
    public void construirPainelTarefa(){
       var painelEsquerdo = this.construirPainelEsquerdo();
       var painelDireito = this.construirPainelDireito();
       painelEsquerdo.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),  new EtchedBorder()));
       
       JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, painelEsquerdo,painelDireito);
       sp.setOneTouchExpandable(true);
       this.add(sp, BorderLayout.CENTER);
       
    }
    
    private JPanel construirPainelEsquerdo(){
        var containerEsquerdo = new JPanel();
        containerEsquerdo.setLayout(new GridBagLayout());
        containerEsquerdo.setBackground(Color.WHITE);
        
        var pTituloSubTitulo = new JPanel();
        pTituloSubTitulo.setSize(420, 120);
        pTituloSubTitulo.setLayout(new GridLayout(2, 1));
        pTituloSubTitulo.setBackground(Color.WHITE);
        
        Font fonteTitulo = new Font("Arial", Font.BOLD, 16);
        Font fonte = new Font("Arial", Font.PLAIN, 16);
//        pTituloSubTitulo.add(new Label("Nome da Tarefa", Color.BLACK, fonteTitulo));
//        pTituloSubTitulo.add(new Label("cartão onde se encontra a tarefa", Color.BLACK, fonte));
        pTituloSubTitulo.add(new Label(tarefa.getTitulo(), Color.BLACK, fonteTitulo));
        pTituloSubTitulo.add(new Label(tarefa.getSubTitulo(), Color.BLACK, fonte));
        c.gridx = 0;
        c.gridy = 0;
        containerEsquerdo.add(pTituloSubTitulo, c);
        // descrição da tarefa
        var descricaoTarefa = new JPanel();
        descricaoTarefa.setLayout(new GridLayout(1, 2, 120, 0));
        descricaoTarefa.setSize(250, 140);
        descricaoTarefa.setBackground(Color.WHITE);

        var lbDesc = new JLabel("Descricao");
        lbDesc.setIcon(new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo("descricao-icon.png")));
        descricaoTarefa.add(lbDesc);
        
        botaoEditar = new Botao("Editar", Color.BLACK, new Color(163, 151,151));
        descricaoTarefa.add(botaoEditar);
        
        c.gridy = 1;
        containerEsquerdo.add(descricaoTarefa, c);
        // area de texto para a descrição
        var ta = new JTextArea("Uma breve descrição da tarefa");
        ta.setBackground(new Color(217, 217, 217));
        JScrollPane sp = new JScrollPane(ta);
	sp.setSize(400, 850);
	sp.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.gridy = 2;
        c.ipady = 45;
        containerEsquerdo.add(sp, c);
        c.ipady = 5;
        // check list
        var checkList = new JPanel();
        checkList.setLayout(new GridBagLayout());
        checkList.setBackground(new Color(217, 217, 217));
        checkList.setSize(800, 150);
        c.gridy = 0;
        checkList.add(new JLabel("Introdução a Java"), c);
        for(int i=1; i <= 4;i++){
            c.gridy = i;
            c.ipadx = 45;
            checkList.add(new CheckBox("sub-tarefa" + i, Color.BLACK, new Color(217, 217, 217)), c);
        }
        c.gridy = 3;
        containerEsquerdo.add(checkList, c);
        redefinirGrid();
        return containerEsquerdo;
    }
    
    private JPanel construirPainelDireito(){
        JPanel containerEsquerdo = new JPanel();
        containerEsquerdo.setLayout(new GridBagLayout());
        containerEsquerdo.setBackground(Color.WHITE);
        
        // para agrupar opções de adicionar ao cartão
        var pAddaoCartao = new JPanel();
        pAddaoCartao.setLayout(new GridLayout(4, 1, 0, 20));
        pAddaoCartao.setBackground(Color.WHITE);
        pAddaoCartao.setBorder(BorderFactory.createTitledBorder("Adicionar ao cartão"));  
        
        // criar elementos
        String[] nomeIcon = {"check-list-simbol.png", "date-simbol.png","anexo-simbol.png", "delete-icon.png"};
        
        // botões
        Color corFundo = new Color(217, 217, 217);
        addAnexo = new Botao("Anexo", Color.BLACK , corFundo);
        addDatas = new Botao("Datas", Color.BLACK, corFundo);
        addCheckList = new Botao("CheckList", Color.BLACK, corFundo);
        excluirTarefa = new Botao("Excluir Tarefa", Color.WHITE, new Color(214, 16, 16));
        ImageIcon iconAnexo = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo(nomeIcon[0]));
        ImageIcon iconDatas = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo(nomeIcon[1]));
        ImageIcon iconCheckList = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo(nomeIcon[2]));
        ImageIcon iconExcluirTarefa = new ImageIcon(ArquivosProjeto.getCaminhoDoArquivo(nomeIcon[3]));
        addAnexo.setIcon(iconAnexo);
        addDatas.setIcon(iconDatas);
        addCheckList.setIcon(iconCheckList);
        excluirTarefa.setIcon(iconExcluirTarefa);
        // adicionando eventos aos botões
        excluirTarefa.addActionListener(new TratadorDeEvento(null, cartaoView, tarefa));
        
        // addionando os botões ao painel
        pAddaoCartao.add(addAnexo);
        pAddaoCartao.add(addDatas);
        pAddaoCartao.add(addCheckList);
        pAddaoCartao.add(excluirTarefa);
     
        c.gridy = 0;
        containerEsquerdo.add(pAddaoCartao, c);
        c.gridy = 1;
        moverCartao = new Botao("Mover Cartão", Color.WHITE);
        moverCartao.setSize(40, 20);
        moverCartao.addActionListener(new TratadorDeEvento(cartoesView, cartaoView, tarefa));
        containerEsquerdo.add(moverCartao, c);
        redefinirGrid();
        return containerEsquerdo;
        
    }
    
    private void redefinirGrid(){
        // redefinir algumas propriedades do grid
        c.ipadx = 5;
        c.ipady = 10;
        c.gridx = 0;
        c.gridy = 0;
    }
    
    public static void main(String[] args) {
//        var f = new TarefaView(null, null);
//        f.iniciarComponentes();
//        f.construirPainelTarefa();
//        f.setVisible(true);
    }
    
}
