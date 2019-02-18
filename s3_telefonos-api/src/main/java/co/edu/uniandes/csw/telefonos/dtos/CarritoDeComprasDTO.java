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
import java.io.Serializable;

public class CarritoDeComprasDTO implements Serializable{
	
	private double costoTotal;
	
	public CarritoDeComprasDTO()
	{
		
	}
	
	public double getCostoTotal()
	{
		return costoTotal;
	}
	
	public void setCostoTotal(double costoTotal)
	{
		this.costoTotal = costoTotal;
	}
	
}
