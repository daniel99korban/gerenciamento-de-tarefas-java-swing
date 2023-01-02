
package view.tratadoreventos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import model.*;
import util.*;
import view.*;
import view.componente.*;

/**
 *
 * @author danie
 */
public class TratadorDeEvento implements ActionListener, MouseListener, TreeSelectionListener{
    
    private LoginView loginView;
    private CadastroView cadastroView;
    private CartaoView cartaoView;
    private List<CartaoView> cartoesView;
    private Tarefa tarefaModel;
    private static TarefaView tarefaView;
    private DashBoardView dashBoardView;
     // email e senha
    TextField email;
    password senha;
    Label labelEntrar;// label da tela de cadastro
    Label labelCadastrar;// label da tela de login
    // gerenciador de entidade
    EntityManager manager = GerenteEntidade.getGerenteDeEntidade();
    // instancia para o usuario no sistema
    public static Usuario usuarioLogado;

    public TratadorDeEvento(List<CartaoView> cartoesView, CartaoView cartaoView, Tarefa tarefaModel) {
        this.cartoesView = cartoesView;
        this.cartaoView = cartaoView;
        this.tarefaModel = tarefaModel;
    }
    
    public TratadorDeEvento(DashBoardView dashBoardView){
        this.dashBoardView = dashBoardView;
    }
    
    public TratadorDeEvento(CartaoView cartaoView) {
        this.cartaoView = cartaoView;
    }

    public TratadorDeEvento(Tarefa tarefaModel) {
        this.tarefaModel = tarefaModel;
    }

    public TratadorDeEvento(TextField email, password senha, LoginView loginView) {
        this.email = email;
        this.senha = senha;
        this.loginView = loginView;
    }
    
    public TratadorDeEvento(TextField email, password senha, CadastroView cadastroView) {
        this.email = email;
        this.senha = senha;
        this.cadastroView = cadastroView;
    }
 
    public TratadorDeEvento(){}
    
    public TratadorDeEvento(Label labelEntrar, CadastroView cadastroView) {
        this.labelEntrar = labelEntrar;
        this.cadastroView = cadastroView;
    }
    
    public TratadorDeEvento(Label labelCadastrar, LoginView loginView) {
        this.labelCadastrar = labelCadastrar;
        this.loginView = loginView;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // logar usuario
        if(e.getActionCommand().equals("Entrar")){
            logarUsuario(e);
        }
        // cadastrar usuario
        if(e.getActionCommand().equals("Cadastrar")){
            cadastrarUsuario();
        }
        // criar novo projeto
        if(e.getActionCommand().equals("Criar Novo Projeto")){
            criarProjeto();
        }
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
    
    private void cadastrarUsuario(){
        CadastroUsuario cadUse = new CadastroUsuario();
        int res = cadUse.cadastrarUsuario(email.getText(), senha.getText());
        if(res == 0){
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!");
            this.cadastroView.setVisible(false);
            new LoginView("LOGIN");
        }else if(res == 1 || res == 2){
            JOptionPane.showMessageDialog(null, "Insira os dados corretamente!");
        }else{
            JOptionPane.showMessageDialog(null, "Usuario já cadastrado no sistema!");  
        }
    }
    
    private void criarProjeto(){
        String nomeProjeto = this.obterInput();
        // projeto 
        var projeto = new Projeto(nomeProjeto, null);
        // cartões
        var c1 = new Cartao();
        var c2 = new Cartao();
        var c3 = new Cartao();
        var c4 = new Cartao();
        // relacionar entidades
        c1.setProjeto(projeto);
        c2.setProjeto(projeto);
        c3.setProjeto(projeto);
        c4.setProjeto(projeto);
        projeto.setCartoes(List.of(c1, c2, c3, c4));
        // relacionar entidade usuario a projeto
        projeto.setUsuario(usuarioLogado);
        manager.getTransaction().begin();
        manager.persist(projeto);
        manager.getTransaction().commit();
        // atualizar bord em tempo real(ainda não consigo resolver)
//          // atualizar bord em tempo real(ainda não consigo resolver)
        
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
            Tarefa t = new Tarefa(res);
            t.setSubTitulo("na lista " + cartaoView.tituloCartao.getText());
            // fundamental para que o join funcione
            t.setCartao(cartaoView.cartaoModel);
            // persistir no banco
            manager.getTransaction().begin();
            cartaoView.cartaoModel.addTarefa(t);   
            manager.getTransaction().commit();
        }
        
        if(res != null){// se a resposta for cancelar a expansão do card não acontece 
            cartaoView.configurarExpansaoCard(cartaoView, 40);
        }
        
        cartaoView.remove(cartaoView.scroll);
        cartaoView.exibirTarefas();

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
        if(!operacao.equalsIgnoreCase("mover")){// so atualizar tamanho do cartao
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
                    // atualizar remoção de tarefa no BD
                    Tarefa tarefaResult = manager.find(Tarefa.class, t.getId());
                    manager.getTransaction().begin();
                    manager.remove(tarefaResult);
                    //cartaoView.cartaoModel.removerTarefa(tarefaModel.getId());
                    manager.getTransaction().commit();
                    break;
                }
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
                    ProjetoView.instanciaPainelProjeto.getCartoes();// lista de cartõesView
                    // atualizar remoção de tarefa no BD
                    Tarefa tarefaResult = manager.find(Tarefa.class, tarefaModel.getId());
                    removerTarefa("mover");// apenas atualizar sua atual localização
                    // recuperando a tarefa para adiciona-la em outro cartão
                    this.tarefaModel = tarefaResult;
                    // atualizar cartão view
                    for(CartaoView cartoesView : this.cartoesView){
                        if(rb.getText().equalsIgnoreCase(cartoesView.tituloCartao.getText())){
                            cartaoView = cartoesView;
                            break;
                        }
                    }
                    
                    // adicionar na lista indicada atualizar no BD
                    manager.getTransaction().begin();
                    tarefaModel.setSubTitulo("na lista " + cartaoView.tituloCartao.getText());
                    
                    // fundamental para que o join funcione
                    tarefaModel.setCartao(cartaoView.cartaoModel);
                    manager.persist(tarefaModel); 
                    manager.getTransaction().commit();
                    
                    // Atualizar view
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
    
    private void logarUsuario(ActionEvent e) {
        usuarioLogado = Usuario.verificarUsuario(email.getText().toString(), senha.getText().toString());
        if(usuarioLogado!=null){
            this.loginView.setVisible(false);
            // chamar o projeto com todos os dados do usuario
            DashBoardView dashBoard = new DashBoardView("Software de Gerenciamento de Projetos");
        }else{
            JOptionPane.showMessageDialog(null, "Usuario ou senha Incorretos!");
                    //showConfirmDialog(null, "Usuario ou senha Incorretos!", "não autenticado", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE);
            //System.out.println("usuario não registrado! :(");
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        // System.out.println("mouse Clicked");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object origem = e.getSource();
        if(origem instanceof Label){
            Label lb = (Label) origem;
            if(lb.getText().equals("<html><u>Entrar</u>")){
                this.cadastroView.setVisible(false);
                new LoginView("LOGIN");
            }
            if(lb.getText().equals("<html><u>Cadastre-se</u>")){
                this.loginView.setVisible(false);
                new CadastroView();
            }
        }
        // System.out.println("mouse Pressed");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("mouse Released");
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
        if(origem instanceof Label){
            Label lb = (Label) origem;
            if(lb.getText().equals("Entrar")){
                this.labelEntrar.setText("<html><u>"+ labelEntrar.getText() +"</u>");
            }
            if(lb.getText().equals("Cadastre-se")){
                this.labelCadastrar.setText("<html><u>"+ labelCadastrar.getText() +"</u>");
            }
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
        if(origem instanceof Label){
            Label lb = (Label) origem;
            if(lb.getText().equalsIgnoreCase("<html><u>Entrar</u>")){
                this.labelEntrar.setText("Entrar");
            }
            if(lb.getText().equalsIgnoreCase("<html><u>Cadastre-se</u>")){
                this.labelCadastrar.setText("Cadastre-se");
            }
        }
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) e
            .getPath().getLastPathComponent();
        if(node.isRoot()){
            System.out.println("Esse é o nó raiz");
        }
        else{
            System.out.println("você selecionou " + node);
        }
    }
    
}
