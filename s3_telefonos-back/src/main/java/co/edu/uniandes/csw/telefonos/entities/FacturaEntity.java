/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

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

    /*
    Comprador relacionado con la factura
    */
    @PodamExclude 
    @ManyToOne
    private CompradorEntity comprador;
    
    /*
    Proveedor relacionado con la factura
    */
    //@PodamExclude 
    //@ManyToOne
    //private ProveedorEntity proveedor;
    
    /*
    Publicacion asociada a la factura
    */
    @PodamExclude
    @OneToOne(mappedBy = "factura", fetch = FetchType.LAZY)
    private PublicacionEntity publicacion;
    
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

    /**
     * @return the comprador
     */
    public CompradorEntity getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(CompradorEntity comprador) {
        this.comprador = comprador;
    }

    /**
     * @return the publicacion
     */
    public PublicacionEntity getPublicacion() {
        return publicacion;
    }

    /**
     * @param publicacion the publicacion to set
     */
    public void setPublicacion(PublicacionEntity publicacion) {
        this.publicacion = publicacion;
    }
    
    
}
