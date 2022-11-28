
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daniel korban
 */
public class Cartao {
    
    private int id;
    private List<Tarefa> listaTarefas;

    public Cartao(int id) {
        this.id = id;
        this.listaTarefas = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void addTarefa(Tarefa tarefa){
        this.getListaTarefas().add(tarefa);
    }
    
    public Tarefa getTarefa(int index){
        return this.getListaTarefas().get(index);
    }

    public List<Tarefa> getListaTarefas() {
        return listaTarefas;
    }
    
    public void removerTarefa(int id){
        for(Tarefa tarefa : this.listaTarefas){
            if(tarefa.getId() == id){
                listaTarefas.remove(tarefa);
                break;
            }
        }
    }

//    public void setListaTarefas(List<Tarefa> listaTarefas) {
//        this.listaTarefas = listaTarefas;
//    }
    
}
