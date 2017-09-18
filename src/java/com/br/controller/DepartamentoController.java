package com.br.controller;
import com.br.dao.DepartamentoDao;
import com.br.entidades.Departamento;
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
public class DepartamentoController {

    private Departamento user;
    private List<Departamento> lista;
    private boolean alterar = false;

    public List<Departamento> getLista() {
        lista = new DepartamentoDao().getLista();
        return lista;
    }
    
    public Departamento getUser() {
        return user;
    }

    public void setLista(List<Departamento> lista) {
        this.lista = lista;
    }

    public void setUser(Departamento user) {
        this.user = user;
    }

    public void excluir() {
        seleciona(user.getId());
        if (new DepartamentoDao().excluir(user)) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", true);
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", false);
        }

    }

    public void seleciona(int id) {
        user = new DepartamentoDao().getDepartamento(id);
    }

    public void selecionaAlterar(int id) {
        user = new DepartamentoDao().getDepartamento(id);
        alterar = false;
    }

    public void salvar() {

        if (alterar) {
            new DepartamentoDao().alterar(user);
            alterar = false;
            System.out.println("Entrou em Alterar");
        } else {

            new DepartamentoDao().inserir(user);

        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void novo() {
        alterar = false;
        user = new Departamento();
    }

}
