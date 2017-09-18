package com.br.dao;
import com.br.entidades.Usuario;
import com.br.utils.Singleton;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author Giuliano S Cunha
 *
 */
public class UsuarioDao {

    private EntityManager em;

    public UsuarioDao() {
        em = Singleton.getConection();
    }

    public void inserir(Usuario p) {

        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
    }

    public boolean excluir(Usuario p) {
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

    public void alterar(Usuario p) {
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

    public Usuario getUsuario(int id) {
        em.getTransaction().begin();
        Usuario p = em.find(Usuario.class, id);
        em.getTransaction().commit();
        return p;
    }

    public List getLista() {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Usuario e");
        List<Usuario> lista = query.getResultList();
        em.getTransaction().commit();
        return lista;
    }
  
    public Usuario Logar(String email, String senha) {
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e FROM Usuario e where e.email=:email and e.senha=:senha");
        query.setParameter("email", email);
        query.setParameter("senha", senha.trim());
        List<Usuario> lista = query.getResultList();
        em.getTransaction().commit();
        Usuario e = new Usuario(-1);
        if (lista.size() > 0) {
            e = lista.get(0);
        }
        return e;
    }
}
