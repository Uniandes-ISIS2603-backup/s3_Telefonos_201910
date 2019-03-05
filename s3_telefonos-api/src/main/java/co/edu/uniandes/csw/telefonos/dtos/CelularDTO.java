/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

/**
 *
 * @author estudiante
 */
import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import java.io.Serializable;

public class CelularDTO implements Serializable {
   
	private String marca;
	private String referencia;
	private String modelo;
	private Long imei;             
	private boolean registrado;             
             
	public CelularDTO(){
		
	}
        
        public CelularDTO(CelularEntity celular){
            if(celular!=null){
                this.imei = celular.getImei();
                this.marca = celular.getMarca();
                this.modelo = celular.getModelo();
                this.referencia = celular.getReferencia();
                this.registrado = celular.isRegistrado();
            }
	}
	
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Long getImei() {
		return imei;
	}
	public void setImei(Long imei) {
		this.imei = imei;
	}
	public boolean isRegistrado() {
		return registrado;
	}
	public void setRegistrado(boolean registrado) {
		this.registrado = registrado;
	}
	
	
 public CelularEntity toEntity(){
            CelularEntity celular = new CelularEntity();
            celular.setImei(this.imei);
            celular.setMarca(this.marca);
            celular.setModelo(this.modelo);
            celular.setReferencia(this.referencia);
            celular.setRegistrado(this.registrado);
            return celular;
        }
}  

