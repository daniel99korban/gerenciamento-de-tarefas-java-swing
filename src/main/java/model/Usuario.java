
package model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import util.GerenteEntidade;

/**
 *
 * @author danie
 */
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    private String email;
    private String senha;

    // referencia para uma lista de projetos
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    @Column(nullable = true)
    private List<Projeto> projetos;
    
    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
        this.projetos = new ArrayList<>();
    }

    public Usuario() {}
    
    public static Usuario verificarUsuario(String email, String senha){
        EntityManager gerente = GerenteEntidade.getGerenteDeEntidade();
        int i=1;
        Usuario usuario;
        
        while(true){
            usuario = gerente.find(Usuario.class, i);
            if(usuario==null) break;
            if(usuario.getSenha().equals(senha) && usuario.getEmail().equals(email)) 
                break;
            i++;
        }
        return usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }
    
    public void addProjeto(Projeto projeto){
        // transação deve ocorrer?
//        var gerenciador = GerenteEntidade.getGerenteDeEntidade();
//        gerenciador.getTransaction().begin();
//        gerenciador.persist(projeto);
//        gerenciador.getTransaction().commit();
//        gerenciador.close();
        this.getProjetos().add(projeto);
    }
    
}
