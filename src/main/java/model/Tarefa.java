
package model;

import java.util.List;
import view.componente.CheckBox;

/**
 *
 * @author danie
 */
public class Tarefa{
    
    private int id;
    private String titulo;
    private String subTitulo;// cartão onde se encontra a tarefa
    private String nomeListSubTarefas;
    private List<CheckBox> checkList;
    private String descricao;

    public Tarefa(String titulo, int id) {
        this.titulo = titulo;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
