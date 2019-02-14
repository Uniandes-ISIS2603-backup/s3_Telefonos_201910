/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Entity;

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
}
