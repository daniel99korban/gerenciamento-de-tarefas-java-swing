
package model;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import view.componente.Botao;
import view.componente.CheckBox;

/**
 *
 * @author danie
 */
public class Tarefa extends JPanel{
    private String titulo;
    private String subTitulo;// cartão onde se encontra a tarefa
    private String nomeListSubTarefas;
    private List<CheckBox> checkList;
    private String descricao;
    private Botao botaoAbrirTarefa;

    public Tarefa(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getNomeListSubTarefas() {
        return nomeListSubTarefas;
    }

    public void setNomeListSubTarefas(String nomeListSubTarefas) {
        this.nomeListSubTarefas = nomeListSubTarefas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
}
