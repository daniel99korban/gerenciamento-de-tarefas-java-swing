
package model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import util.GerenteEntidade;

/**
 *
 * @author danie
 */
public class CadastrarDados {
    public static void main(String[] args) {
        EntityManager manager = GerenteEntidade.getGerenteDeEntidade();
        // cartões
        var c1 = new Cartao();
        var c2 = new Cartao();
        var c3 = new Cartao();
        var c4 = new Cartao();
        // tarefas
//        var  t1 = new Tarefa("Estudar JavaScript");
//        var  t2 = new Tarefa("Estudar NodeJS");
//        var  t3 = new Tarefa("Conceitos de POO");
//        var  t4 = new Tarefa("Ler Livro de BD");
//        var  t5 = new Tarefa("Estudar Java");
//        // fundamental para o join ter sucesso
//        t1.setCartao(c1);
//        t2.setCartao(c1);
//        t3.setCartao(c1);
//        t4.setCartao(c2);
//        t5.setCartao(c2);
        
//        c1.addTarefa(t1); c1.addTarefa(t2); c1.addTarefa(t3);
//        c2.addTarefa(t4); c2.addTarefa(t5);
        List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(c1); cartoes.add(c2); cartoes.add(c3); cartoes.add(c4);
        
        // projeto
        var projeto = new Projeto("Maratona de Estudos", cartoes);
        // fundamental para o join ter sucesso
        c1.setProjeto(projeto);
        c2.setProjeto(projeto);
        c3.setProjeto(projeto);
        c4.setProjeto(projeto);
        // usuario 
        Usuario u = new Usuario("aluno@gmail.com", "123");
        // fundamental para o join ter sucesso
        projeto.setUsuario(u);
        u.addProjeto(projeto);
        
        manager.getTransaction().begin();
        manager.persist(u);
        manager.getTransaction().commit();
        
//        manager.getTransaction().begin();
//        manager.persist(c2);
//        manager.getTransaction().commit();
        
        // fechar conexão
        manager.close();
    }
}
