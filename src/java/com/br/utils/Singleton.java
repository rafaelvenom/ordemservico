package com.br.utils;
import com.br.entidades.Cliente;
import com.br.entidades.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Giuliano S Cunha
 *
 */
public class Singleton {

    private static Usuario usuario;
    private static Cliente cliente;
    
    private static EntityManager conection;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ORDEM");

    //getConexão
    public static EntityManager getConection() {
        if (conection == null) {
            setConection();
        }
        return conection;
    }

    //seta a conexão
    public static void setConection() {
        conection = emf.createEntityManager();
    }

    /**
     * @return the usuario
     */
    public static Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param aUsuario the usuario to set
     */
    public static void setUsuario(Usuario aUsuario) {
        usuario = aUsuario;
    }

    /**
     * @return the cliente
     */
    public static Cliente getCliente() {
        return cliente;
    }

    /**
     * @param aCliente the cliente to set
     */
    public static void setCliente(Cliente aCliente) {
        cliente = aCliente;
    }
  
}
