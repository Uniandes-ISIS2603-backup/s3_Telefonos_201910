/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

/**
 *
 * @author Daniel Babativa
 */
import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import java.io.Serializable;

public class CelularDTO implements Serializable {

        /**
         * Marca del celular
         */
	private String marca;
	
        /**
         * Referencia del celular
         */
        private String referencia;
        
        /**
         * Modelo del celular
         */
	private String modelo;
        
        /**
         * imei del celular
         */
	private Long imei;    
        
        /**
         * Boolean para saber si el celular está registrado, true si lo está false de lo contrario
         */
	private Boolean registrado;             
             
        /**
         * Constructor by default
         */
	public CelularDTO(){
		
	}
        
        /**
         * Constructor con entity como parametro (convierte el entity en el dto)
         * @param celular entity que se convierte a dto
         */
        public CelularDTO(CelularEntity celular){
            if(celular!=null){
                this.imei = celular.getImei();
                this.marca = celular.getMarca();
                this.modelo = celular.getModelo();
                this.referencia = celular.getReferencia();
                this.registrado = celular.isRegistrado();
            }
	}
	
	/**
         * Retorna la marca del celular
         * @return la marca del celular
         */
	public String getMarca() {
		return marca;
	}
        
        /**
         * Actualiza la marca del celular
         * @param marca la marca que reemplaza la marca antigua
         */
	public void setMarca(String marca) {
		this.marca = marca;
	}
        
        /**
         * Retorna la referencia
         * @return the referencia
         */
	public String getReferencia() {
		return referencia;
	}
        
        /**
         * actualiza la referencia
         * @param referencia la referencia que reemplaza la anterior
         */
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
        
        /**
         * Retorna el modelo del celular
         * @return the modelo
         */
	public String getModelo() {
		return modelo;
	}
        
        /**
         * Actualiza el modelo  
         * @param modelo, modelo que actualiza el anterior
         */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
        
        /**
         * Retorna el imei del celular
         * @return imei del celular
         */
	public Long getImei() {
		return imei;
	}
        
        
        /**
         * Actualiza el imei del celular
         * @param imei el imei que actualiza el anterior
         */
	public void setImei(Long imei) {
		this.imei = imei;
	}
        
        /**
         * Retorna si el celular está registrado o no, true si lo está, false de lo contrario
         * @return registrado
         */
	public Boolean getRegistrado() {
		return this.registrado;
	}
        
        /**
         * Actualiza el estado de registrado del celular
         * @param registrado , el nuevo estado de registrado
         */
	public void setRegistrado(Boolean registrado) {
            this.registrado = registrado;
	}
	
	/**
         * Metodo que convierte DTO a entity
         * @return el nuevo objeto entity
         */
 public CelularEntity toEntity(){
            CelularEntity celular = new CelularEntity();
            celular.setImei(this.getImei());
            celular.setMarca(this.getMarca());
            celular.setModelo(this.getModelo());
            celular.setReferencia(this.getReferencia());
            celular.setRegistrado(this.getRegistrado());
            return celular;
        }

 

 
}  

