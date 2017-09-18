package com.br.controller;
import com.br.dao.ServicoDao;
import com.br.entidades.Servico;
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
public class ServicoController {

    private Servico user;
    private List<Servico> lista;
    private boolean alterar = false;

    public List<Servico> getLista() {
        lista = new ServicoDao().getLista();
        return lista;
    }

    public Servico getUser() {
        return user;
    }

    public void setLista(List<Servico> lista) {
        this.lista = lista;
    }

    public void setUser(Servico user) {
        this.user = user;
    }

    public void excluir() {
        seleciona(user.getId());
        if (new ServicoDao().excluir(user)) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", true);
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", false);
        }

    }

    public void seleciona(int id) {
        user = new ServicoDao().getServico(id);
    }

    public void selecionaAlterar(int id) {
        user = new ServicoDao().getServico(id);
        alterar = false;
    }

    public void salvar() {

        if (alterar) {
            new ServicoDao().alterar(user);
            alterar = false;
            System.out.println("Entrou em Alterar");
        } else {

            new ServicoDao().inserir(user);

        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void novo() {
        alterar = false;
        user = new Servico();
    }
}
