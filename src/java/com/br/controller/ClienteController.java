package com.br.controller;
import com.br.dao.ClienteDao;
import com.br.entidades.Cliente;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Giuliano S Cunha
 *
 */
@ManagedBean
@SessionScoped
public class ClienteController {

    private Cliente user;
    private List<Cliente> lista;
    private boolean alterar = false;
  
    
    public List<Cliente> getLista() {
        lista = new ClienteDao().getLista();
        return lista;
    }

    public Cliente getUser() {
        return user;
    }

    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

    public void setUser(Cliente user) {
        this.user = user;
    }

    public void excluir() {
        seleciona(user.getId());
        if (new ClienteDao().excluir(user)) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", true);
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", false);
        }

    }

    public void seleciona(int id) {
        user = new ClienteDao().getCliente(id);
    }

    public void selecionaAlterar(int id) {
        user = new ClienteDao().getCliente(id);
        alterar = false;
    }

    public void salvar() {

        if (alterar) {
            new ClienteDao().alterar(user);
            alterar = false;
            System.out.println("Entrou em Alterar");
        } else {
            try {
                new ClienteDao().inserir(user);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void novo() {
        alterar = false;
        user = new Cliente();
    }

}
