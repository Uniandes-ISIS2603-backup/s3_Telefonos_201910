/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
import java.io.Serializable;

/**
 *
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */
public class SeguroDTO implements Serializable{
    
    /*
    Monto del seguro
    */
    private Double monto;
    
    /*
     *Aseguradora encargada del seguro 
     */
    private String aseguradora;
    
    /*
    Identificador del seguro
    */
    private Long id;
    
    
    /*
    Metodo constructor
    */
    public SeguroDTO(){
        
    }
    
    /**
     * Crea un objeto SeguroDTO a partir de un objeto SeguroEntity.
     *
     * @param seguroEntity Entidad SeguroEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public SeguroDTO(SeguroEntity seguroEntity) {
        if (seguroEntity != null) {
            this.id = seguroEntity.getId();
            this.monto = seguroEntity.getMonto();
            this.aseguradora = seguroEntity.getAseguradora();
            
        }
    }
    
    /**
     * Convierte un objeto SeguroDTO a SeguroEntity.
     *
     * @return Nueva objeto SeguroEntity.
     *
     */
    public SeguroEntity toEntity() {
        SeguroEntity seguroEntity = new SeguroEntity();
        seguroEntity.setId(this.getId());
        seguroEntity.setMonto(this.getMonto());
        seguroEntity.setAseguradora(this.getAseguradora());
        return seguroEntity;
    }

    /**
     * @return the monto
     */
    public Double getMonto() {
        return monto;
    }

    /**
     * @param monto the monto to set
     */
    public void setMonto(Double monto) {
        this.monto = monto;
    }

    /**
     * @return the aseguradora
     */
    public String getAseguradora() {
        return aseguradora;
    }

    /**
     * @param aseguradora the aseguradora to set
     */
    public void setAseguradora(String aseguradora) {
        this.aseguradora = aseguradora;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
    
}
