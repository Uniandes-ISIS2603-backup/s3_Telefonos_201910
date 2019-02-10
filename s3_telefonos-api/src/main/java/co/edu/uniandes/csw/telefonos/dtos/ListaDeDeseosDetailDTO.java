/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import java.io.Serializable;
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
    //private List<CelularDTO> celulares;
    
    /*
    * Constructor por defecto
    */
    public ListaDeDeseosDetailDTO(){
        
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
    
}
