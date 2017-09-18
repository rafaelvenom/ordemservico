package com.br.controller;
import com.br.dao.TecnicoDao;
import com.br.entidades.Tecnico;
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
public class TecnicoController {

    private Tecnico user;
    private List<Tecnico> lista;
    private boolean alterar = false;

    public List<Tecnico> getLista() {
        lista = new TecnicoDao().getLista();
        return lista;
    }

    public Tecnico getUser() {
        return user;
    }

    public void setLista(List<Tecnico> lista) {
        this.lista = lista;
    }

    public void setUser(Tecnico user) {
        this.user = user;
    }

    public void excluir() {
        seleciona(user.getId());
        if (new TecnicoDao().excluir(user)) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", true);
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", false);
        }

    }

    public void seleciona(int id) {
        user = new TecnicoDao().getTecnico(id);
    }

    public void selecionaAlterar(int id) {
        user = new TecnicoDao().getTecnico(id);
        alterar = false;
    }

    public void salvar() {

        if (alterar) {
            new TecnicoDao().alterar(user);
            alterar = false;
            System.out.println("Entrou em Alterar");
        } else {

            new TecnicoDao().inserir(user);

        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void novo() {
        alterar = false;
        user = new Tecnico();
    }
}
