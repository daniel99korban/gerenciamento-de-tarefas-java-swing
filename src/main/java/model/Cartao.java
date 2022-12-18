
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import util.GerenteEntidade;

/**
 *
 * @author daniel korban
 */
@Entity
@Table(name = "cartao")
public class Cartao {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cartao")
    private List<Tarefa> listaTarefas;
    
    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

//    public Cartao(int id) {
//        this.id = id;
//        this.listaTarefas = new ArrayList<>();
//    }
    public Cartao() {
        this.listaTarefas = new ArrayList<>();
    }

//    public Cartao() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void addTarefa(Tarefa tarefa){
        var manager = GerenteEntidade.getGerenteDeEntidade();
        Cartao cartao = manager.find(Cartao.class, this.id);
        manager.getTransaction().begin();
        cartao.getListaTarefas().add(tarefa);
//        this.getListaTarefas().add(tarefa);
        manager.getTransaction().commit();
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

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cartao other = (Cartao) obj;
        return this.id == other.id;
    }
    // metodos usados pelo jpa
    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }
    
}
