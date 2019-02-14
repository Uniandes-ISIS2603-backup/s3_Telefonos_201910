/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import java.io.Serializable;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
public class CompradorDTO implements Serializable {
    
    /*
    Usuario con que se registra el comprador
    */
    private String usuario;
    
    /*
    Contrasenia con que se registra el comprador
    */
    private String contrasenia;
    
    /*
    ID que se le asigna al comprador
    */
    private Long id;
    
    /*
    Apodo (nickname) con que se registra el comprador
    */
    private String apodo;
    
    /*
    Correo con que se registra el comprador
    */
    private String correoElectronico;
    
    /*
    Carrito de compras del comprador
    */
    //private CarritoDeComprasDTO carritoDeCompras;
    
    /*
    Lista de deseos del comprador
    */
    //private ListaDeDeseosDTO listaDeDeseos;
    /*
    Metodo constructor
    */
    public CompradorDTO(){
        
        
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
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the apodo
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * @param apodo the apodo to set
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
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
    
}
