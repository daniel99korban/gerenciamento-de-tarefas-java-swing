package view;

import java.awt.Color;
import java.awt.GridLayout;
import view.Cartao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 *
 * @author danie
 */
public class PainelProjeto extends JPanel{
    
    private String nomeProjeto;
    private List<Cartao> cartoes;
    
    public PainelProjeto(String nomeProjeto){
        if(nomeProjeto == null){
            nomeProjeto = "undefined";
        }
        this.nomeProjeto = nomeProjeto;
        this.cartoes = new ArrayList<>();
        this.setSize(1090, 580);
        this.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(40, 40, 40, 40),  new EtchedBorder()));
        this.setLayout(new GridLayout(1, 4, 40,0));
    }
    /**
     * @param nomeCartao
     * @param coresRGB cor de destaque para cada tipo de cartão
     * posições onde ficaram cada cartão dentro do frame
     * @param x  
     * @param y 
     */
    private void criarCartao(int id, String nomeCartao, int[] coresRGB, int x, int y){
        Cartao c = new Cartao(nomeCartao, coresRGB);
        c.setSize(210, 500);
        c.posicaoX = x;
        c.posicaoY = y;
        c.id = id;
        cartoes.add(c);
    }
    
    public void exibirCartoes(){
        for(Cartao c: this.cartoes){
            c.setLocation(c.posicaoX, c.posicaoY);
            this.add(c);
        }
    }
    
    public void init() {
        int[] corCartao1 = {87, 138, 242};// azul
        int[] corCartao2 = {58, 189, 218};// azul-claro
        int[] corCartao3 = {34, 127, 35};// verde
        int[] corCartao4 = {182, 68, 246};// margenta
   
        this.criarCartao(1, "A fazer", corCartao1, 50, 50);
        this.criarCartao(2, "A fazer Hoje", corCartao2, 320, 50);
        this.criarCartao(3, "Em Progresso", corCartao3, 590, 50);
        this.criarCartao(4, "Feito", corCartao4, 860, 50);
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
