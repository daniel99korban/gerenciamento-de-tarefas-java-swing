package view;


import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import util.ArquivosProjeto;

/**
 *
 * @author daniel
 */
public class Cartao extends JPanel{
    
    public int id;
    public JLabel tituloCartao;
    public String nome;
    public int posicaoX;
    public int posicaoY;
    
    public Cartao(String title, int ...corCartao){
        nome = title; 
        tituloCartao = new JLabel(title);
        tituloCartao.setForeground(Color.white);
        
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
        
    }
    
}
