
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
}
