package view;


import view.Cartao;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;

/**
 *
 * @author danie
 */
public class DashBoardView extends JFrame{
    
    private List<Cartao> cartoes;
    
    public DashBoardView(){
        super("Software de Gerenciamento de Projetos");
    }
    
    public void init(){
        // configurações gerais
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1140, 640);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        this.cartoes = new ArrayList<>();
    }
    /**
     * @param nomeCartao
     * @param coresRGB cor de destaque para cada tipo de cartão
     * posições onde ficaram cada cartão dentro do frame
     * @param x
     * @param y 
     */
    public void addCartao(int id, String nomeCartao, int[] coresRGB, int x, int y){
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
    // classe para teste
    public static void main(String[] args) {
        DashBoardView dashBoard = new DashBoardView();
        int[] corCartao1 = {87, 138, 242};
        int[] corCartao2 = {58, 189, 218};
        int[] corCartao3 = {34, 127, 35};
        int[] corCartao4 = {182, 68, 246};
        
        dashBoard.init();
        dashBoard.addCartao(1, "A fazer", corCartao1, 50, 50);
        dashBoard.addCartao(2, "A fazer Hoje", corCartao2, 320, 50);
        dashBoard.addCartao(3, "Em Progresso", corCartao3, 590, 50);
        dashBoard.addCartao(4, "Feito", corCartao4, 860, 50);
        dashBoard.exibirCartoes();
        dashBoard.setVisible(true);
        dashBoard.setBackground(Color.yellow);
    }
}
