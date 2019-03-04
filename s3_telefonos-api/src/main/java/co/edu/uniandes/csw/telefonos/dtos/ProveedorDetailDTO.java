/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */
public class ProveedorDetailDTO extends ProveedorDTO implements Serializable{
    
    
    /*
    Lista de facturas asociadas con un proveedor
    */
    private List<FacturaDTO> facturasDeVentas;
    
    /*
    Publicaciones asociadas con un proveedor
    */
    private List<PublicacionDTO> publicacionesDeVenta;
    
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
    
    /**
     * Crea un objeto ProveedorDetailDTO a partir de un objeto ProveedorEntity
     * incluyendo los atributos de ProveedorDTO.
     *
     * @param proveedorEntity Entidad ProveedorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public ProveedorDetailDTO(ProveedorEntity proveedorEntity) {
        super(proveedorEntity);
        if (proveedorEntity != null) {
            facturasDeVentas = new ArrayList<>();
            for (FacturaEntity entityFacturas : proveedorEntity.getFacturasDeVenta()) {
                facturasDeVentas.add(new FacturaDTO(entityFacturas));
            }
            publicacionesDeVenta = new ArrayList();
            for (PublicacionEntity entityPublicacion : proveedorEntity.getPublicaciones()) {
                publicacionesDeVenta.add(new PublicacionDTO(entityPublicacion));
            }
            
            seguros = new ArrayList();
            for (SeguroEntity entitySeguro : proveedorEntity.getSeguros()) {
                seguros.add(new SeguroDTO(entitySeguro));
            }
        }
    }

    /**
     * @return the facturasDeVentas
     */
    public List<FacturaDTO> getFacturasDeVentas() {
        return facturasDeVentas;
    }

    /**
     * @param facturasDeVentas the facturasDeVentas to set
     */
    public void setFacturasDeVentas(List<FacturaDTO> facturasDeVentas) {
        this.facturasDeVentas = facturasDeVentas;
    }

    /**
     * @return the publicacionesDeVenta
     */
    public List<PublicacionDTO> getPublicacionesDeVenta() {
        return publicacionesDeVenta;
    }

    /**
     * @param publicacionesDeVenta the publicacionesDeVenta to set
     */
    public void setPublicacionesDeVenta(List<PublicacionDTO> publicacionesDeVenta) {
        this.publicacionesDeVenta = publicacionesDeVenta;
    }

    /**
     * @return the seguros
     */
    public List<SeguroDTO> getSeguros() {
        return seguros;
    }

    /**
     * @param seguros the seguros to set
     */
    public void setSeguros(List<SeguroDTO> seguros) {
        this.seguros = seguros;
    }

    
    
    
}
