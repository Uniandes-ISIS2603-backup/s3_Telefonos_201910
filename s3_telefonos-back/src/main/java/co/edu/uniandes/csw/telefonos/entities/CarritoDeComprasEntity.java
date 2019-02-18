/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Daniel Babativa
 */
@Entity
public class CarritoDeComprasEntity extends BaseEntity implements Serializable{
    
    private Integer costoTotal;
    
    public CarritoDeComprasEntity(){
        
    }

    /**
     * @return the costoTotal
     */
    public Integer getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(Integer costoTotal) {
        this.costoTotal = costoTotal;
    }
    
}
