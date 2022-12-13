
package model;

import java.util.List;
import javax.persistence.*;
import view.componente.CheckBox;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "tarefa")
public class Tarefa{
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private String subTitulo;// cartão onde se encontra a tarefa
    private String nomeListSubTarefas;
    //@Transient private List<CheckBox> checkList;// por enquanto será ignorado pelo hibernate
    private String descricao;
    @ManyToOne()
//    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "cartao_id", nullable = false)
    private Cartao cartao;// será mapeado pelo jpa
//    public Tarefa(String titulo, int id) {// original
//        this.titulo = titulo;
//        this.id = id;
//    }
    public Tarefa(String titulo) {
        this.titulo = titulo;
    }

//    public Tarefa(String titulo, String subTitulo, String nomeListSubTarefas, String descricao) {
//        this.titulo = titulo;
//        this.subTitulo = subTitulo;
//        this.nomeListSubTarefas = nomeListSubTarefas;
//        this.descricao = descricao;
//    }
    
    
    public Tarefa(){}

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
    
    // a camada de pessistencia jpa precisará destes metodos
    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }
    
}
