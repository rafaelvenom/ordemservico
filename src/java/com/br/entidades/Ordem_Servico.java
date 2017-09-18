package com.br.entidades;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Giuliano S Cunha
 *
 */
@Entity
public class Ordem_Servico implements Serializable {

    public Ordem_Servico() {

    }

    public Ordem_Servico(int id) {
        this.id = id;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 30)
    private String situacao;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_inicio;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date data_fim;

    @Column(length = 200)
    private String detalhe_servico;

    @Column(length = 200)
    private String defeito_relato;

   
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date hora_inicio;

    @Temporal(javax.persistence.TemporalType.TIME)
    private Date hora_fim;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    @OneToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @OneToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    // Get e Set
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) getId();
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordem_Servico)) {
            return false;
        }
        Ordem_Servico other = (Ordem_Servico) object;
        if (this.getId() != other.getId()) {
            return false;
        }
        return true;
    }

    /**
     * @return the situacao
     */
    public String getSituacao() {
        return situacao;
    }

    /**
     * @param situacao the situacao to set
     */
    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    /**
     * @return the data_inicio
     */
    public Date getData_inicio() {
        return data_inicio;
    }

    /**
     * @param data_inicio the data_inicio to set
     */
    public void setData_inicio(Date data_inicio) {
        this.data_inicio = data_inicio;
    }

    /**
     * @return the data_fim
     */
    public Date getData_fim() {
        return data_fim;
    }

    /**
     * @param data_fim the data_fim to set
     */
    public void setData_fim(Date data_fim) {
        this.data_fim = data_fim;
    }

    /**
     * @return the detalhe_servico
     */
    public String getDetalhe_servico() {
        return detalhe_servico;
    }

    /**
     * @param detalhe_servico the detalhe_servico to set
     */
    public void setDetalhe_servico(String detalhe_servico) {
        this.detalhe_servico = detalhe_servico;
    }

    /**
     * @return the defeito_relato
     */
    public String getDefeito_relato() {
        return defeito_relato;
    }

    /**
     * @param defeito_relato the defeito_relato to set
     */
    public void setDefeito_relato(String defeito_relato) {
        this.defeito_relato = defeito_relato;
    }

   

    /**
     * @return the hora_inicio
     */
    public Date getHora_inicio() {
        return hora_inicio;
    }

    /**
     * @param hora_inicio the hora_inicio to set
     */
    public void setHora_inicio(Date hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    /**
     * @return the hora_fim
     */
    public Date getHora_fim() {
        return hora_fim;
    }

    /**
     * @param hora_fim the hora_fim to set
     */
    public void setHora_fim(Date hora_fim) {
        this.hora_fim = hora_fim;
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
     * @return the servico
     */
    public Servico getServico() {
        return servico;
    }

    /**
     * @param servico the servico to set
     */
    public void setServico(Servico servico) {
        this.servico = servico;
    }

    /**
     * @return the tecnico
     */
    public Tecnico getTecnico() {
        return tecnico;
    }

    /**
     * @param tecnico the tecnico to set
     */
    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    /**
     * @return the departamento
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * @param departamento the departamento to set
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

}
