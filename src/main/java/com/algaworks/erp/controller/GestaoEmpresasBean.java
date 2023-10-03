package com.algaworks.erp.controller;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.model.TipoEmpresa;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

    private Empresa empresa = new Empresa();


    public Empresa getEmpresa() {
        return empresa;
    }

    public TipoEmpresa[] getTiposEmpresa() {
        return TipoEmpresa.values();
    }

    public void salvar(){
        System.out.println("Razao social: "+ empresa.getRazaoSocial()
                + "\nNome fantasia: "+ empresa.getNomeFantasia()
                + "\nTipo: "+ empresa.getTipo());
    }
}
