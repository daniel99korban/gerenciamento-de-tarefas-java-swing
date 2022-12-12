
package model;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "projeto")
public class Projeto {
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String nomeProjeto;
     
    @OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
    private List<Cartao> cartoes;
    
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    public Projeto(String nomeProjeto, List<Cartao> cartoes) {
        this.nomeProjeto = nomeProjeto;
        this.cartoes = cartoes;
    }
            
    public Projeto(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public List<Cartao> getCartoes() {
        return cartoes;
    }

    public void setCartoes(List<Cartao> cartoes) {
        this.cartoes = cartoes;
    }
    // metodos usados pelo jpa

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
