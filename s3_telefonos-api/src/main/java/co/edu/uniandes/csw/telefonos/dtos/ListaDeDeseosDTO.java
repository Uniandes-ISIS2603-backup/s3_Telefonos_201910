/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import java.io.Serializable;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
public class ListaDeDeseosDTO implements Serializable{
    
    private Double costoEstimado;
    
    private Long identificador;
    
  

    public ListaDeDeseosDTO(){
        
    }
    
    /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param editorialEntity: Es la entidad que se va a convertir a DTO
     */
    public ListaDeDeseosDTO(ListaDeDeseosEntity listaEntity) {
        if (listaEntity != null) {
            this.costoEstimado = listaEntity.getCostoEstimado();
            this.identificador = listaEntity.getId();
            
        }
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
    public void setCostoEstimado(Double costoEstimado) {
        this.costoEstimado = costoEstimado;
    }

    /**
     * @return the identificador
     */
    public Long getIdentificador() {
        return identificador;
    }

    /**
     * @param identificador the identificador to set
     */
    public void setIdentificador(Long identificador) {
        this.identificador = identificador;
    }
    
    public ListaDeDeseosEntity toEntity(){
        ListaDeDeseosEntity listaEntity = new ListaDeDeseosEntity();
        listaEntity.setId(this.identificador);
        listaEntity.setCostoEstimado(this.costoEstimado);
        return listaEntity;
    }
}
