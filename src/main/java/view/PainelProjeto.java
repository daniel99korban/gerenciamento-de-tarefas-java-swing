package view;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import model.Cartao;

/**
 *
 * @author danie
 */
public class PainelProjeto extends JPanel{
    
    private String nomeProjeto;
    private List<CartaoView> cartoes;
    
    public PainelProjeto(String nomeProjeto){
        if(nomeProjeto == null){
            nomeProjeto = "undefined";
        }
        this.nomeProjeto = nomeProjeto;
        this.cartoes = new ArrayList<>();
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
    private void criarCartao(Cartao cartaoModel, String nomeCartao, int[] coresRGB, int x, int y){
        CartaoView c = new CartaoView(nomeCartao, cartaoModel, coresRGB);
        c.setSize(225, 60);// tamanho padrão inicial do cartão
        c.posicaoX = x;
        c.posicaoY = y;
        cartoes.add(c);
    }
    
    public void exibirCartoes(){
        for(CartaoView c: this.cartoes){
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
        Cartao c1 = new Cartao(1);
        Cartao c2 = new Cartao(2);
        Cartao c3 = new Cartao(3);
        Cartao c4 = new Cartao(4);
        // view
        this.criarCartao(c1, "A fazer", corCartao1, 50, 50);
        this.criarCartao(c2, "A fazer Hoje", corCartao2, 320, 50);
        this.criarCartao(c3, "Em Progresso", corCartao3, 590, 50);
        this.criarCartao(c4, "Feito", corCartao4, 860, 50);
        this.exibirCartoes();
        this.setBackground(new Color(30, 30, 30));
        this.setVisible(true);  
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }
//    public static void main(String[] args) {
//        var pj = new PainelProjeto("teste");
//        var f = new JFrame();
//        f.setSize(pj.getWidth(), pj.getHeight());
//        pj.init();
//        pj.exibirCartoes();
//        f.add(pj);
//        f.setVisible(true);
//    }
}
