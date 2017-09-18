package com.br.dao;
import com.br.entidades.Cliente;
import com.br.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Giuliano S Cunha
 *
 */
public class ClienteDao {

    private EntityManager em;

    public ClienteDao() {
        em = Singleton.getConection();
    }

    public void inserir(Cliente p) {

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public boolean excluir(Cliente p) {
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

    public void alterar(Cliente p) {
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

    public Cliente getCliente(int id) {
        em.getTransaction().begin();
        Cliente p = em.find(Cliente.class, id);
        em.getTransaction().commit();
        return p;
    }

    public List getLista() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Cliente e");
        List<Cliente> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
           }
    
    public Cliente Logar(String cnpj) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Cliente e where e.cnpj=:cnpj");
        query.setParameter("cnpj", cnpj);
        List<Cliente> lista = query.getResultList();
        em.getTransaction().commit();
        Cliente e = new Cliente(-1);
        if (lista.size() > 0) {
            e = lista.get(0);
        }
        return e;
    }
}
