/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
public class ListaDeDeseosDetailDTO extends ListaDeDeseosDTO implements Serializable{
    
    /*
    *Esta lista de tipo TabletDTO contiene las tablets asociadas a una lista de deseos
    */
    private List<TabletDTO> tablets;
    
    /*
    *Esta lista de tipo CelularDTO contiene los celulares asociadas a una lista de deseos
    */
    private List<CelularDTO> celulares;
    
    /*
    * Constructor por defecto
    */
    public ListaDeDeseosDetailDTO(){
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param listaDeDeseosEntity: Es la entidad que se va a convertir a DTO
     */
    public ListaDeDeseosDetailDTO(ListaDeDeseosEntity listaEntity) {
        super(listaEntity);
        if (listaEntity != null) {
           if(listaEntity.getTablets() != null){
               tablets = new ArrayList<>();
               for(TabletEntity tabletEntity: listaEntity.getTablets()){
                   tablets.add(new TabletDTO(tabletEntity));
               }
           }
           if(listaEntity.getCelulares() != null){
               celulares = new ArrayList<>();
               for(CelularEntity celularEntity: listaEntity.getCelulares()){
                   celulares.add(new CelularDTO(celularEntity));
               }
           }
        }
    }
    
     /**
     * Transformar un DTO a un Entity
     *
     * @return El DTO de la editorial para transformar a Entity
     */
    @Override
    public ListaDeDeseosEntity toEntity() {
        ListaDeDeseosEntity listaEntity = super.toEntity();
        if (tablets != null) {
            List<TabletEntity> tabletsEntity = new ArrayList<>();
            for (TabletDTO dtoTablet : tablets) {
                tabletsEntity.add(dtoTablet.toEntity());
            }
            listaEntity.setTablets(tabletsEntity);
        }
        if (celulares != null) {
            List<CelularEntity> celularesEntity = new ArrayList<>();
            for (CelularDTO dtoCelular : celulares) {
                //celularesEntity.add(dtoCelular.toEntity());
            }
            listaEntity.setCelulares(celularesEntity);
        }
        return listaEntity;
}

    /**
     * @return the tablets
     */
    public List<TabletDTO> getTablets() {
        return tablets;
    }

    /**
     * @param tablets the tablets to set
     */
    public void setTablets(List<TabletDTO> tablets) {
        this.tablets = tablets;
    }

    /**
     * @return the celulares
     */
    public List<CelularDTO> getCelulares() {
        return celulares;
    }

    /**
     * @param celulares the celulares to set
     */
    public void setCelulares(List<CelularDTO> celulares) {
        this.celulares = celulares;
    }
    
}
