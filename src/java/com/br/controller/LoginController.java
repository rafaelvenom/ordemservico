/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.br.controller;
import com.br.dao.ClienteDao;
import com.br.dao.UsuarioDao;
import com.br.entidades.Cliente;
import com.br.entidades.Usuario;
import com.br.utils.Singleton;
import java.io.IOException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Giuliano S Cunha
 */
@SessionScoped
@ManagedBean
public class LoginController {

    private Usuario usuario;
    private Cliente cliente;
    private String email;
    private String senha;
    private String cnpj;
    private List<Cliente> lista;
    
    public String logar() {
        String logado = "Logado";
        try {

            setUsuario(new UsuarioDao().Logar(getEmail(), getSenha()));

            if (getUsuario().getId() != -1) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                session.setAttribute("usuarioLogado", getUsuario());
                Singleton.setUsuario(getUsuario());
                logado = "Logado";
                FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/administrador/index.xhtml");
            } else {
                logado = "falha";
                System.out.println("Falha");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FALHA", "Usuário ou senha Invalidos"));
            }
            return logado;

        } catch (Exception e) {
            e.printStackTrace();
            return logado;
        }
    }
    
    public String logarCliente() {
        String logado = "Logado";
        try {

            setCliente (new ClienteDao().Logar(getCnpj()));

            if (getCliente().getId() != -1) {
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
                session.setAttribute("clienteLogado", getCliente());
                Singleton.setCliente(getCliente());
                logado = "Logado";
                FacesContext.getCurrentInstance().getExternalContext().redirect("../faces/cliente/index.xhtml");
            } else {
                logado = "falha";
                System.out.println("Falha");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "FALHA", "CNPJ Inválido"));
            }
            return logado;

        } catch (Exception e) {
            e.printStackTrace();
            return logado;
        }
    }

    public void verificaSessao() {
        boolean opc = true;
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

            if (usuarioLogado.equals(null)) {
                opc = false;
            }
        } catch (NullPointerException e) {
            opc = false;
        }

        if (!opc) {
            try {
                FacesContext.getCurrentInstance()
                        .getExternalContext().redirect("../login.xhtml");
            } catch (IOException ex) {
            }
        }

    }
    
    public void verificaSessaoCliente() {
        boolean opc = true;
        try {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            Cliente clienteLogado = (Cliente) session.getAttribute("clienteLogado");

            if (clienteLogado.equals(null)) {
                opc = false;
            }
        } catch (NullPointerException e) {
            opc = false;
        }

        if (!opc) {
            try {
                FacesContext.getCurrentInstance()
                        .getExternalContext().redirect("/login.xhtml");
            } catch (IOException ex) {
            }
        }

    }

    public void logout() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("../login.xhtml");

    }
    
     public void logoutCliente() throws IOException {

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("./login.xhtml");
    }
    
    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the lista
     */
    public List<Cliente> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<Cliente> lista) {
        this.lista = lista;
    }

}
