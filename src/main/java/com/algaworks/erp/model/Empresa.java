package com.algaworks.erp.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_fantasia", nullable = false, length = 80)
    private String nomeFantasia;

    @Column(name = "razao_social", nullable = false, length = 120)
    private String razaoSocial;

    @Column(nullable = false, length = 18)
    private String cnpj;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_fundacao")
    private Date dataFundacao;

    @ManyToOne
    @JoinColumn(name = "ramo_atividade_id", nullable = false)
    private RamoAtividade ramoAtividade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TipoEmpresa tipo;

    public TipoEmpresa getTipo() {
        return tipo;
    }

    public void setTipo(TipoEmpresa tipo) {
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Date getDataFundacao() {
        return dataFundacao;
    }

    public void setDataFundacao(Date dataFundacao) {
        this.dataFundacao = dataFundacao;
    }

    public RamoAtividade getRamoAtividade() {
        return ramoAtividade;
    }

    public void setRamoAtividade(RamoAtividade ramoAtividade) {
        this.ramoAtividade = ramoAtividade;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Empresa empresa = (Empresa) o;

        return id.equals(empresa.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
