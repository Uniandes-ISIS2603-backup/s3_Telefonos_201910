/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Daniel Babativa
 */
@Entity
public class CarritoDeComprasEntity extends BaseEntity implements Serializable{
    /**
     * un carrito tiene muchas publicaciones
     */
    @PodamExclude
    @ManyToMany(mappedBy = "carritoDeCompras")
    private List<PublicacionEntity> publicaciones;
    
    
    /**
     * un comprador tiene un carrito
     */
    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private CompradorEntity comprador;
    
    /**
     * el costo total de las publicaciones del carrito
     */
    private Double costoTotal;
    
    /**
     * el identificador del carrito de compras
     */
    private Long id;
    
    /**
     * constructor by default
     */
    public CarritoDeComprasEntity(){
        
    }

    /**
     * @return the costoTotal
     */
    public Double getCostoTotal() {
        return costoTotal;
    }

    /**
     * @param costoTotal the costoTotal to set
     */
    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }
    
    /**
     * obtiene las publicaciones
     * @return lista de publicacionEntity
     */
    public List<PublicacionEntity> getPublicaciones(){
        return this.publicaciones;
    }
     
    
    /**
     * actualiza las publicaciones
     * @param publicaciones parametro con el cual se actualiza
     */
  public void setPublicaciones(List<PublicacionEntity> publicaciones){
        this.publicaciones = publicaciones;
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
    
    
    public Long getId()
    {
        return this.id;
    }
    
    public void setId(Long id){
        this.id=id;
    }
    
}
