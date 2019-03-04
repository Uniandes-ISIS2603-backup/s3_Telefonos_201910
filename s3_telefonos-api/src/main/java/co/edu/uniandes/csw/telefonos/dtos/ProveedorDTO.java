/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import java.io.Serializable;

/**
 *
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */
public class ProveedorDTO implements Serializable{
    
    /*
    Usuario con que se registra el proveedor
    */
    private String usuario;
    
    /*
    Contrasenia con que se registra el proveedor
    */
    private String contrasenia;
    
    /*
    ID que se le asigna al proveedor
    */
    private Long id;
    
    /*
    Correo con que se registra el comprador
    */
    private String correoElectronico; 
    
    /*
    Raiting
    */
    private Integer raitig;
    
    /*
    Nombre del proveedor
    */
    private String nombre;
    
    /*
    Metodo constructor
    */
    public ProveedorDTO(){
        
    }
    /**
     * Crea un objeto ProveedorDTO a partir de un objeto ProveedorEntity.
     *
     * @param proveedorEntity Entidad ProveedorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public ProveedorDTO(ProveedorEntity proveedorEntity) {
        if (proveedorEntity != null) {
            this.usuario = proveedorEntity.getUsuario();
            this.contrasenia = proveedorEntity.getContrasenia();
            this.id = proveedorEntity.getId();
            this.correoElectronico = proveedorEntity.getCorreoElectronico();
            this.raitig = proveedorEntity.getRaitig();
            this.nombre = proveedorEntity.getNombre();
        }
    }
    
     /**
     * Convierte un objeto ProveedorDTO a ProveedorEntity.
     *
     * @return Nueva objetoProveedorEntity.
     *
     */
    public ProveedorEntity toEntity() {
        ProveedorEntity proveedorEntity = new ProveedorEntity();
        proveedorEntity.setId(this.getId());
        proveedorEntity.setUsuario(this.getUsuario());
        proveedorEntity.setContrasenia(this.getContrasenia());
        proveedorEntity.setNombre(this.getNombre());
        proveedorEntity.setCorreoElectronico(this.getCorreoElectronico());
        proveedorEntity.setRaitig(this.getRaitig());
        return proveedorEntity;
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
     * @return the raitig
     */
    public Integer getRaitig() {
        return raitig;
    }

    /**
     * @param raitig the raitig to set
     */
    public void setRaitig(Integer raitig) {
        this.raitig = raitig;
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
