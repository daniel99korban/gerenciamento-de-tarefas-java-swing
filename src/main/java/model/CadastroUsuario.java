
package model;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import util.GerenteEntidade;

/**
 *
 * @author danie
 */
public class CadastroUsuario {
    
    private EntityManager manager;
    private Usuario usuario;

    public CadastroUsuario() {
        this.manager = GerenteEntidade.getGerenteDeEntidade();
    }
    
    public int cadastrarUsuario(String email, String senha){
        System.out.println("Email: " + email);
        System.out.println("Senha: " + senha);
        // verificar se os campos de email ou senha estão vazios ou nulos
        if(email.isEmpty() || email==null){
            return 1;
        }
        if(senha.isEmpty() || senha==null){
            return 2;
        }
        // verificar se email ja esta cadastrado;
        int i = 1;
        while(true){
            Usuario useResult = manager.find(Usuario.class, i);
            if(useResult==null) break;
            if(useResult.getEmail().equals(email)){
                return  -1;
            }
            i++;
        }
        
        usuario = new Usuario(email, senha);
        // cadastrar usuario no banco
        manager.getTransaction().begin();
        manager.persist(usuario);
        manager.getTransaction().commit();      
        
        return 0;
    }
        
}
