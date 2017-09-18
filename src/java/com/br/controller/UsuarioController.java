package com.br.controller;
import com.br.dao.UsuarioDao;
import com.br.entidades.Usuario;
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
public class UsuarioController {

    private Usuario user;
    private List<Usuario> lista;
    private boolean alterar = false;

    public List<Usuario> getLista() {
        lista = new UsuarioDao().getLista();
        return lista;
    }

    public Usuario getUser() {
        return user;
    }

    public void setLista(List<Usuario> lista) {
        this.lista = lista;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }

    public void excluir() {
        seleciona(user.getId());
        if (new UsuarioDao().excluir(user)) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", true);
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", false);
        }

    }

    public void seleciona(int id) {
        user = new UsuarioDao().getUsuario(id);
    }

    public void selecionaAlterar(int id) {
        user = new UsuarioDao().getUsuario(id);
        alterar = false;
    }

    public void salvar() {

        if (alterar) {
            new UsuarioDao().alterar(user);
            alterar = false;
            System.out.println("Entrou em Alterar");
        } else {

            new UsuarioDao().inserir(user);

        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void novo() {
        alterar = false;
        user = new Usuario();
    }
}
