package com.br.dao;
import com.br.entidades.Ordem_Servico;
import com.br.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Giuliano S Cunha
 *
 */
public class Ordem_ServicoDao {

    private EntityManager em;

    public Ordem_ServicoDao() {
        em = Singleton.getConection();
    }

    public void inserir(Ordem_Servico p) {

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public boolean excluir(Ordem_Servico p) {
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

    public void alterar(Ordem_Servico p) {
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

    public Ordem_Servico getOrdem_Servico(int id) {
        em.getTransaction().begin();
        Ordem_Servico p = em.find(Ordem_Servico.class, id);
        em.getTransaction().commit();
        return p;
    }

    public List getLista() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Ordem_Servico e");
        List<Ordem_Servico> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }

    public List getListaCliente(String cnpj) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Ordem_Servico e where e.cliente.cnpj=:cnpj");
        query.setParameter("cnpj", cnpj);
        List<Ordem_Servico> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
}
