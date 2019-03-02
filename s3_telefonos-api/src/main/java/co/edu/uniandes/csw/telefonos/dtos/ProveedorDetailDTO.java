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
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */
public class ProveedorDetailDTO extends ProveedorDTO implements Serializable{
    
    
    /*
    Lista de facturas asociadas con un proveedor
    */
    private List<FacturaDTO> facturasDeVenta;
    
    /*
    Publicaciones asociadas con un proveedor
    */
    private List<PublicacionDTO> publicaciones;
    
    /*
    Seguros asociados con un proveedor
    */
    private List<SeguroDTO> seguros;
    
    /*
    Metodo constructor
    */
    public ProveedorDetailDTO(){
        super();
    }
    
    //TODO: Agregar constructor que recibe entity

    /**
     * @return the facturasDeVenta
     */
    public List<FacturaDTO> getFacturasDeVenta() {
        return facturasDeVenta;
    }

    /**
     * @param facturasDeVenta the facturasDeVenta to set
     */
    public void setFacturasDeVenta(List<FacturaDTO> facturasDeVenta) {
        this.facturasDeVenta = facturasDeVenta;
    }

    /**
     * @return the publicaciones
     */
    public List<PublicacionDTO> getPublicaciones() {
        return publicaciones;
    }

    /**
     * @param publicaciones the publicaciones to set
     */
    public void setPublicaciones(List<PublicacionDTO> publicaciones) {
        this.publicaciones = publicaciones;
    }
    
    
}
