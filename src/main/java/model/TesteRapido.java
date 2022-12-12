
package model;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import util.GerenteEntidade;

/**
 *
 * @author danie
 */
public class TesteRapido {
    public static void main(String[] args) {
        EntityManager manager = GerenteEntidade.getGerenteDeEntidade();
        // cartões
        var c1 = new Cartao();
        var c2 = new Cartao();
        // tarefas
        var  t1 = new Tarefa("Estudar JavaScript");
        var  t2 = new Tarefa("Estudar para BDII");
        var  t3 = new Tarefa("Treinar Guitarra");
        var  t4 = new Tarefa("terminar naruto");
        var  t5 = new Tarefa("organizar livros");
        // fundamental para o join ter sucesso
        t1.setCartao(c1);
        t2.setCartao(c1);
        t3.setCartao(c1);
        t4.setCartao(c2);
        t5.setCartao(c2);
        // projeto
//        // cartao 
        c1.addTarefa(t1); c1.addTarefa(t2); c1.addTarefa(t3);
        c2.addTarefa(t4); c2.addTarefa(t5);
        List<Cartao> cartoes = new ArrayList<>();
        cartoes.add(c1); cartoes.add(c2);
        
        // projeto
        var projeto = new Projeto("doideras-teste", cartoes);
        // fundamental para o join ter sucesso
        c1.setProjeto(projeto);
        c2.setProjeto(projeto);
        
        manager.getTransaction().begin();
        manager.persist(projeto);
        manager.getTransaction().commit();
        
//        manager.getTransaction().begin();
//        manager.persist(c2);
//        manager.getTransaction().commit();
        
        // fechar conexão
        manager.close();
    }
}
