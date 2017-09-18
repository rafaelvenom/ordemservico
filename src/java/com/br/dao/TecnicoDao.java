package com.br.dao;
import com.br.entidades.Tecnico;
import com.br.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Giuliano S Cunha
 *
 */
public class TecnicoDao {

    private EntityManager em;

    public TecnicoDao() {
        em = Singleton.getConection();
    }

    public void inserir(Tecnico p) {

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public boolean excluir(Tecnico p) {
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

    public void alterar(Tecnico p) {
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

    public Tecnico getTecnico(int id) {
        em.getTransaction().begin();
        Tecnico p = em.find(Tecnico.class, id);
        em.getTransaction().commit();
        return p;
    }

    public List getLista() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Tecnico e");
        List<Tecnico> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
