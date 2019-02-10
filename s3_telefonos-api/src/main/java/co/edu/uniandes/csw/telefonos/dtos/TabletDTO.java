/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import java.io.Serializable;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
public class TabletDTO implements Serializable{
    
    /*
    * String que representa la marca de la Tablet
    */
    private String marca;
    
    /*
    * String que representa la referencia de la Tablet
    */
    private String referencia;
    
    /*
    * String que representa el modelo de la Tablet
    */
    private String modelo;
    
    /*
    * Boolean que representa si la Tableta esta registrada o no por un Proveedor
    */
    private boolean registrado;
    
    /*
    * Constructor por defecto
    */
    public TabletDTO(){
        
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the registrado
     */
    public boolean isRegistrado() {
        return registrado;
    }

    /**
     * @param registrado the registrado to set
     */
    public void setRegistrado(boolean registrado) {
        this.registrado = registrado;
    }
    
}
