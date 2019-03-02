/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

/**
 *
 * @author rj.gonzalez10
 */
@Entity
public class PublicacionEntity extends BaseEntity implements Serializable{
    //fecha de creación de la publicación
    private Date fechaCreacion;
    //arreglo de imagenes que se van a  usar en el post
    private ArrayList<String> imagenes;
    //precio del producto ofrecido
    private Double precio;
    //celular que esta asociado a la publicación, tiene que ser null en caso de que la publicación ya tenga asociada 
    @OneToOne(mappedBy = "publicacion", cascade = CascadeType.ALL,
              fetch = FetchType.EAGER, optional = false)
    private CelularEntity celular;
    //Tablet que esta aspciada a la publicación, tiene que ser null si tiene una publicación asociada
    @OneToOne(mappedBy = "publicacion", cascade = CascadeType.ALL,
              fetch = FetchType.EAGER, optional = false)
    private TabletEntity tablet;

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public CelularEntity getCelular() {
        return celular;
    }

    public void setCelular(CelularEntity celular) {
        this.celular = celular;
    }

    public TabletEntity getTablet() {
        return tablet;
    }

    public void setTablet(TabletEntity tablet) {
        this.tablet = tablet;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }
     public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
