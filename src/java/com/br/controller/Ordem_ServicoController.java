package com.br.controller;

import com.br.dao.ClienteDao;
import com.br.dao.DepartamentoDao;
import com.br.dao.Ordem_ServicoDao;
import com.br.dao.ServicoDao;
import com.br.dao.TecnicoDao;
import com.br.entidades.Cliente;
import com.br.entidades.Departamento;
import com.br.entidades.Ordem_Servico;
import com.br.entidades.Servico;
import com.br.entidades.Tecnico;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Giuliano S Cunha
 *
 */
@ManagedBean
@SessionScoped
public class Ordem_ServicoController {

    private Ordem_Servico user;
    private List<Ordem_Servico> lista;
    private boolean alterar = false;
    private int idCliente;
    private int idServico;
    private int idTecnico;
    private int idDepartamento;
    private String cnpj;

    public List<Ordem_Servico> getLista() {
        lista = new Ordem_ServicoDao().getLista();
        return lista;
    }

    public List<Ordem_Servico> getListaCliente() {
        lista = new Ordem_ServicoDao().getListaCliente(cnpj);
        return lista;
    }

    public Ordem_Servico getUser() {
        return user;
    }

    public void setLista(List<Ordem_Servico> lista) {
        this.lista = lista;
    }

    public void setUser(Ordem_Servico user) {
        this.user = user;
    }

    public void excluir() {
        seleciona(user.getId());
        if (new Ordem_ServicoDao().excluir(user)) {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", true);
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.addCallbackParam("sucesso", false);
        }

    }

    public void seleciona(int id) {
        user = new Ordem_ServicoDao().getOrdem_Servico(id);
    }

    public void selecionaAlterar(int id,int idCliente,int idServico,int idTecnico,int idDepartamento) {

        setUser(new Ordem_ServicoDao().getOrdem_Servico(id));

        setIdCliente(idCliente);

        setIdServico(idServico);

        setIdTecnico(idTecnico);

        setIdDepartamento(idDepartamento);
        
        setAlterar(false);

    }

    public void salvar() {

        Cliente c = new ClienteDao().getCliente(idCliente);
        user.setCliente(c);

        Servico s = new ServicoDao().getServico(idServico);
        user.setServico(s);

        Tecnico t = new TecnicoDao().getTecnico(idTecnico);
        user.setTecnico(t);

        Departamento d = new DepartamentoDao().getDepartamento(idDepartamento);
        user.setDepartamento(d);

        if (isAlterar()) {
            new Ordem_ServicoDao().alterar(user);
            setAlterar(false);
            System.out.println("Entrou em Alterar");
        } else {

            new Ordem_ServicoDao().inserir(user);

        }
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.addCallbackParam("sucesso", true);
    }

    public void novo() {
        setAlterar(false);
        user = new Ordem_Servico();
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the idServico
     */
    public int getIdServico() {
        return idServico;
    }

    /**
     * @param idServico the idServico to set
     */
    public void setIdServico(int idServico) {
        this.idServico = idServico;
    }

    /**
     * @return the idTecnico
     */
    public int getIdTecnico() {
        return idTecnico;
    }

    /**
     * @param idTecnico the idTecnico to set
     */
    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    /**
     * @return the idDepartamento
     */
    public int getIdDepartamento() {
        return idDepartamento;
    }

    /**
     * @param idDepartamento the idDepartamento to set
     */
    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }
    
    public void visualizarRelatorio(int id, String nome) {
        try {

            Connection conexao = this.getConexao();

            //---------- gera o relatorio ----------
            HashMap parametros = new HashMap();
            parametros.put("idOrdemServico", id);
            JasperReport jasperReport = JasperCompileManager.compileReport(getClass().getResourceAsStream("/com/br/relatorios/ordemservico.jrxml"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexao);

            byte[] b = JasperExportManager.exportReportToPdf(jasperPrint);
            HttpServletResponse res = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            res.setContentType("application/pdf");
            //Código abaixo gerar o relatório e disponibiliza diretamente na página 
            //res.setHeader("Content-disposition", "inline;filename=arquivo.pdf");
            //Código abaixo gerar o relatório e disponibiliza para o cliente baixar ou salvar 
            res.setHeader("Content-disposition", "attachment;filename=ordem" + nome.toLowerCase().trim().replace(" ", "") + ".pdf");
            res.getOutputStream().write(b);
            res.getCharacterEncoding();
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("saiu do visualizar relatorio");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void geraRelatorioPedi(int id) {
        try {
            Connection conexao = this.getConexao();

            String nomeRelatorio = System.getProperty("java.io.tmpdir") + "/Relatorio.pdf";
            InputStream caminhoRelatorio = getClass().getClassLoader().getResourceAsStream("/com/br/relatorios/ordemservico.jasper");
            HashMap par = new HashMap();
            par.put("idOrdemServico", id);
            JasperPrint printReport = JasperFillManager.fillReport(caminhoRelatorio, par, conexao);
            JasperExportManager.exportReportToPdfFile(printReport, nomeRelatorio);
            Runtime.getRuntime().exec("cmd /c start " + nomeRelatorio);
            File file = new File(nomeRelatorio);
            file.deleteOnExit();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Connection getConexao() {
        java.sql.Connection conexao = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/ordemservico";
            conexao = DriverManager.getConnection(url, "root", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conexao;
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
     * @return the alterar
     */
    public boolean isAlterar() {
        return alterar;
    }

    /**
     * @param alterar the alterar to set
     */
    public void setAlterar(boolean alterar) {
        this.alterar = alterar;
    }

}
