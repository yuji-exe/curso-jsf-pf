package com.algaworks.erp.service;

import com.algaworks.erp.model.Empresa;
import com.algaworks.erp.repository.Empresas;
import com.algaworks.erp.util.Transacional;

import javax.inject.Inject;
import java.io.Serializable;

public class CadastroEmpresaService implements Serializable {

    @Inject
    private Empresas empresas;

    @Transacional
    public void salvar(Empresa empresa){
        empresas.guardar(empresa);
    }

    @Transacional
    public void excluir(Empresa empresa){
        empresas.remover(empresa);
    }
}
