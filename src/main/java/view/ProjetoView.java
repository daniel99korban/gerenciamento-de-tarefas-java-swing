package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import model.Cartao;
import model.Projeto;
import util.GerenteEntidade;
import view.tratadoreventos.TratadorDeEvento;

/**
 *
 * @author danie
 */
public class ProjetoView extends JPanel{
    // variavel global que será util nas operações de movimentação dos cartões(acho que não é uma boa ideia)
    public static ProjetoView instanciaPainelProjeto;
    private Projeto projetoModel;// teste
    private String nomeProjeto;
    private List<CartaoView> cartoesView;

    public List<CartaoView> getCartoes() {
        return cartoesView;
    }
    
    public ProjetoView(String nomeProjeto){
        if(nomeProjeto == null){
            nomeProjeto = "undefined";
        }
        // recuparar projeto do banco de dados
        var manager = GerenteEntidade.getGerenteDeEntidade();
        Projeto p = manager.find(Projeto.class, TratadorDeEvento.usuarioLogado.getId());
        this.projetoModel = p;
        
        this.instanciaPainelProjeto = this;
        this.nomeProjeto = nomeProjeto;
        this.cartoesView = new ArrayList<>();
        this.setSize(1090, 580);
        this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(40, 40, 40, 40),  new EtchedBorder()));
        this.setLayout(null);
    }
    /**
     * @param nomeCartao
     * @param coresRGB cor de destaque para cada tipo de cartão
     * posições onde ficaram cada cartão dentro do frame
     * @param x  
     * @param y 
     */
    private void construirCartao(Cartao cartaoModel, String nomeCartao, int[] coresRGB, int x, int y){
        CartaoView c = new CartaoView(cartoesView, nomeCartao, cartaoModel, coresRGB);
        c.setSize(225, 60);// tamanho padrão inicial do cartão
        c.posicaoX = x;
        c.posicaoY = y;
        cartoesView.add(c);
    }
    
    public void exibirCartoes(){
        for(CartaoView c: this.cartoesView){
            int gap = 20 * c.cartaoModel.getListaTarefas().size();
            c.configurarExpansaoCard(c, gap);
            c.setLocation(c.posicaoX, c.posicaoY);
            this.add(c);
        }
    }
    
    public void init() {
        // simular estruturas
        int[] corCartao1 = {87, 138, 242};// azul
        int[] corCartao2 = {58, 189, 218};// azul-claro
        int[] corCartao3 = {34, 127, 35};// verde
        int[] corCartao4 = {182, 68, 246};// margenta
        // model
        Projeto pj = TratadorDeEvento.usuarioLogado.getProjetos().get(0);// incrementar para cada projeto do usuario
        Cartao c1 = pj.getCartoes().get(0);
        Cartao c2 = pj.getCartoes().get(1);
        Cartao c3 = pj.getCartoes().get(2);
        Cartao c4 = pj.getCartoes().get(3);
        // view
        this.construirCartao(c1, "A fazer", corCartao1, 50, 50);
        this.construirCartao(c2, "A fazer Hoje", corCartao2, 320, 50);
        this.construirCartao(c3, "Em Progresso", corCartao3, 590, 50);
        this.construirCartao(c4, "Feito", corCartao4, 860, 50);
        this.exibirCartoes();
        this.setBackground(new Color(30, 30, 30));
        this.setVisible(true);  
    }

    public String getNomeProjeto() {
        return this.projetoModel.getNomeProjeto();
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.projetoModel.setNomeProjeto(nomeProjeto);
    }
//    public static void main(String[] args) {
//        var pj = new ProjetoView("teste");
//        var f = new JFrame();
//        f.setSize(pj.getWidth(), pj.getHeight());
//        pj.init();
//        pj.exibirCartoes();
//        f.add(pj);
//        f.setVisible(true);
//    }
}
