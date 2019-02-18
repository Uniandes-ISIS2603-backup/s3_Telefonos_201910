/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable{
    /*
    Fecha de expedicion de la factura
    */
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    /*
    Referencia de la factura
    */
    private String referencia;

    /**
     * Constructor
     */
    public FacturaEntity(){
        
    }
    
    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }
    
    
}
