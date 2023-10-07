package com.algaworks.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.RamoAtividade;
import com.algaworks.erp.model.TipoEmpresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.repository.RamoAtividades;
import com.algaworks.erp.service.CadastroEmpresaService;
import com.algaworks.erp.util.FacesMessages;
import org.primefaces.context.RequestContext;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Empresas empresas;

    @Inject
    private FacesMessages messages;

    @Inject
    private RamoAtividades ramoAtividades;

    private Converter ramoAtividadeConverter;

    private List<Empresa> listaEmpresas;

    private String termoPesquisa;

    private Empresa empresa;

    @Inject
    private CadastroEmpresaService cadastroEmpresaService;

    public void prepararNovaEmpresa(){
        empresa = new Empresa();
    }

    public void salvar(){
        cadastroEmpresaService.salvar(empresa);

        atualizarRegistros();

        messages.info("Empresa salva com sucesso.");

        RequestContext.getCurrentInstance().update(Arrays.asList(
                        "frm:empresasDataTable", "frm:messages"));
    }

    private void atualizarRegistros() {
        if(jaHouvePesquisa()){
            pesquisar();
        } else {
            todasEmpresas();
        }
    }

    private boolean jaHouvePesquisa(){
        return termoPesquisa != null && !"".equals(termoPesquisa);
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void pesquisar(){
        listaEmpresas = empresas.pesquisar(termoPesquisa);

        if (listaEmpresas.isEmpty()){
            messages.info("Sua consulta não encontrou registros");
        }
    }

    public String getTermoPesquisa() {
        return termoPesquisa;
    }

    public void setTermoPesquisa(String termoPesquisa) {
        this.termoPesquisa = termoPesquisa;
    }

    public void todasEmpresas() {
        listaEmpresas = empresas.todas();
    }

    public List<RamoAtividade> completarRamoAtividade(String termo){
        List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);

        ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);

        return listaRamoAtividades;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public TipoEmpresa[] getTiposEmpresa(){
        return TipoEmpresa.values();
    }

    public Converter getRamoAtividadeConverter() {
        return ramoAtividadeConverter;
    }

    public boolean isEmpresaSelecionada(){
        return empresa != null && empresa.getId() != null;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void prepararEdicao(){
        ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
    }

    public void excluir(){
        cadastroEmpresaService.excluir(empresa);

        empresa = null;

        atualizarRegistros();

        messages.info("Empresa excluída com sucesso.");
    }
}