/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import java.io.Serializable;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
public class ListaDeDeseosDTO implements Serializable{
    
    private Double costoEstimado;

    public ListaDeDeseosDTO(){
        
    }
    /**
     * @return the costoEstimado
     */
    public Double getCostoEstimado() {
        return costoEstimado;
    }

    /**
     * @param costoEstimado the costoEstimado to set
     */
    public void setCostoEstimado(double costoEstimado) {
        this.costoEstimado = costoEstimado;
    }
}
