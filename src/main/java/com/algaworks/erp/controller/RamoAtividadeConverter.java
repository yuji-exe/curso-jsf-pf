package com.algaworks.erp.controller;

import com.algaworks.erp.model.RamoAtividade;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import java.util.List;

public class RamoAtividadeConverter implements Converter {

    private List<RamoAtividade> listaRamoAtividades;

    public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividades) {
        this.listaRamoAtividades = listaRamoAtividades;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(s == null){
            return null;
        }

        Long id = Long.valueOf(s);

        for(RamoAtividade ramoAtividade: listaRamoAtividades){
            if(id.equals(ramoAtividade.getId())){
                return ramoAtividade;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if(o == null){
            return null;
        }
        RamoAtividade ramoAtividade = (RamoAtividade) o;
        return ramoAtividade.getId().toString();
    }
}
