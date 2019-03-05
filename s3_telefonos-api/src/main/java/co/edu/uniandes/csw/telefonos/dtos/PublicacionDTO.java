/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author rj.gonzalez10
 */
public class PublicacionDTO implements Serializable{
    //fecha de creación de la publicación
    private Date fechaCreacion;
    //arreglo de imagenes que se van a  usar en el post
    private ArrayList<String> imagenes; 
    //precio del objeto que esta siendo puesto en venta
    private Double precio;
    //id
    private Long id;
    
   public PublicacionDTO()
   {
       
   }
   public PublicacionDTO(PublicacionEntity publicacionEntity)
   { if(publicacionEntity != null){
       this.fechaCreacion = publicacionEntity.getFechaCreacion();
       this.imagenes = publicacionEntity.getImagenes();
       this.precio = publicacionEntity.getPrecio();
       this.id = publicacionEntity.getId();
   }
       
   }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public PublicacionEntity toEntity()
    {
        PublicacionEntity entidad = new PublicacionEntity();
        entidad.setFechaCreacion(this.fechaCreacion);
        entidad.setImagenes(this.imagenes);
        entidad.setPrecio(this.precio);
        entidad.setId(this.id);
        return entidad;
    }

}
