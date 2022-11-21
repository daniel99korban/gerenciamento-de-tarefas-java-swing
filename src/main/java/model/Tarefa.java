
package model;

import java.util.List;
import view.componente.Botao;
import view.componente.CheckBox;

/**
 *
 * @author danie
 */
public class Tarefa{
    
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
