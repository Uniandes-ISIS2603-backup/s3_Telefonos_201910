/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
public class FacturaDTO implements Serializable {
    
    /*
    Fecha de expedicion de la factura
    */
    private Date fecha;
    
    /*
    Referencia de la factura
    */
    private String referencia;
    
    /*
    ID asignado a la factura
    */
    private String id;
    
    /*
    Comprador relacionado con la factura
    */
    private CompradorDTO comprador;
    
    /*
    Proveedor relacionado con la factura
    */
    //private ProveedorDTO proveedor;
    
    /*
    Metodo constructor
    */
    public FacturaDTO(){
        
        
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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the comprador
     */
    public CompradorDTO getComprador() {
        return comprador;
    }

    /**
     * @param comprador the comprador to set
     */
    public void setComprador(CompradorDTO comprador) {
        this.comprador = comprador;
    }
    
    
}
