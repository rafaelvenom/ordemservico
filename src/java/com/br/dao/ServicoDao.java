package com.br.dao;
import com.br.entidades.Servico;
import com.br.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Giuliano S Cunha
 *
 */
public class ServicoDao {

    private EntityManager em;

    public ServicoDao() {
        em = Singleton.getConection();
    }

    public void inserir(Servico p) {

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public boolean excluir(Servico p) {
        boolean excluir = true;
        try {
            em.getTransaction().begin();
            em.remove(p);
            em.getTransaction().commit();
            excluir = true;
        } catch (Exception e) {
            em.getTransaction().begin();
            em.getTransaction().rollback();
            e.printStackTrace();
            excluir = false;
        }
        return excluir;
    }

    public void alterar(Servico p) {
        try {
            em.getTransaction().begin();
            em.merge(p);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().begin();
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public Servico getServico(int id) {
        em.getTransaction().begin();
        Servico p = em.find(Servico.class, id);
        em.getTransaction().commit();
        return p;
    }

    public List getLista() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Servico e");
        List<Servico> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
