/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
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
    private Long id;
    
    /*
    Comprador relacionado con la factura
    */
    private CompradorDTO comprador;
    
    /*
    Proveedor relacionado con la factura
    */
    private ProveedorDTO proveedor;
    
    /*
    Publicacion asociada a la factura
    */
    private PublicacionDTO publicacion;
    
    /*
    Metodo constructor
    */
    public FacturaDTO(){
        
    }
    
    /**
     * Crea un objeto FacturaDTO a partir de un objeto FacturaEntity.
     *
     * @param facturaEntity Entidad FacturaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public FacturaDTO(FacturaEntity facturaEntity) {
        if (facturaEntity != null) {
            this.id = facturaEntity.getId();
            this.fecha = facturaEntity.getFecha();
            this.referencia = facturaEntity.getReferencia();
            if(comprador!=null){
            this.comprador = new CompradorDTO(facturaEntity.getComprador());
            }
            if(proveedor!=null){
            this.proveedor = new ProveedorDTO(facturaEntity.getProveedor());
            }
            if(publicacion!=null){
            this.publicacion = new PublicacionDTO(facturaEntity.getPublicacion());
            }
        }
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
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
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

    /**
     * @return the publicacion
     */
    public PublicacionDTO getPublicacion() {
        return publicacion;
    }

    /**
     * @param publicacion the publicacion to set
     */
    public void setPublicacion(PublicacionDTO publicacion) {
        this.publicacion = publicacion;
    }

    /**
     * @return the proveedor
     */
    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    /**
     * @param proveedor the proveedor to set
     */
    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }
    
    /**
     * Convierte un objeto FacturaDTO a FacturaEntity.
     *
     * @return Nueva objeto FacturaEntity.
     *
     */
    public FacturaEntity toEntity() {
        FacturaEntity facturaEntity = new FacturaEntity();
        facturaEntity.setId(this.getId());
        facturaEntity.setFecha(this.getFecha());
        facturaEntity.setReferencia(this.getReferencia());
        facturaEntity.setComprador(this.getComprador().toEntity());
        facturaEntity.setProveedor(this.getProveedor().toEntity());
        facturaEntity.setPublicacion(this.getPublicacion().toEntity());
        return facturaEntity;
    }
}
