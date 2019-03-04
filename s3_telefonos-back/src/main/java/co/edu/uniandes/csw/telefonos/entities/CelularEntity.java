/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author Daniel Babativa
 */
@Entity
public class CelularEntity extends BaseEntity implements Serializable{
    
    private String marca;
    
    private String referencia;
    
    private String modelo;
    
    private Long imei;
    
    private boolean registrado;
    
    public CelularEntity(){
        
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
     * @return the imei
     */
    public Long getImei() {
        return imei;
    }

    /**
     * @param imei the imei to set
     */
    public void setImei(Long imei) {
        this.imei = imei;
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
