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
    
    @PodamExclude
    @ManyToMany(mappedBy = "carritoDeCompras")
    private List<PublicacionEntity> publicaciones;
    
    @PodamExclude
    @OneToOne(fetch = FetchType.LAZY)
    private CompradorEntity comprador;
    
    private Double costoTotal;
    
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
    
    
    public List<PublicacionEntity> getPublicaciones(){
        return this.publicaciones;
    }
     
    
    
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
    
}
