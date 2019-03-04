/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */
@Entity
public class ProveedorEntity extends BaseEntity implements Serializable{
    
    /*
    Usuario con que se registra el proveedor
    */
    private String usuario;
    
    /*
    Contrasenia con que se registra el  proveedor
    */
    private String contrasenia;
    
    /*
   Nombre con que se registra el proveedor
    */
    private String nombre;
    
    /*
    Correo con que se registra el  proveedor
    */
    private String correoElectronico;

    /*
    Lista de facturas asociadas con un comprador
    */ 
    @PodamExclude
    @OneToMany(mappedBy = "proveedor",fetch = FetchType.LAZY )
    private List<FacturaEntity> facturasDeVenta;
    
    /*
    Seguros asociados con un proveedor
    */
    @PodamExclude
    @OneToMany(mappedBy = "proveedor",fetch = FetchType.LAZY)
    private List<SeguroEntity> seguros;
    
     /*
    Publicaciones asociadas con un proveedor
    */
    //@PodamExclude
    //@OneToMany(mappedBy = "proveedor",fetch = FetchType.LAZY)
    //private List<PublicacionEntity> publicaciones;

    public ProveedorEntity(){
        
    }
    
    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the facturasDeCompra
     */
    public List<FacturaEntity> getFacturasDeCompra() {
        return facturasDeVenta;
    }

    /**
     * @param facturasDeVenta the facturasDeCompra to set
     */
    public void setFacturasDeCompra(List<FacturaEntity> facturasDeVenta) {
        this.facturasDeVenta = facturasDeVenta;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
