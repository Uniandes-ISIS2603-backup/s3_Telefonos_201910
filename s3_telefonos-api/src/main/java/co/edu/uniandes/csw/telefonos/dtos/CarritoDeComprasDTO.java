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
import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import java.io.Serializable;

public class CarritoDeComprasDTO implements Serializable{
	
	private Double costoTotal;
	
	public CarritoDeComprasDTO()
	{
		
	}
        
         public CarritoDeComprasDTO(CarritoDeComprasEntity carrito){
            if(carrito!=null){
                this.costoTotal = carrito.getCostoTotal();
            }
	}
	
	public Double getCostoTotal()
	{
		return costoTotal;
	}
	
	public void setCostoTotal(Double costoTotal)
	{
		this.costoTotal = costoTotal;
	}
	public CarritoDeComprasEntity toEntity(){
            CarritoDeComprasEntity carrito = new CarritoDeComprasEntity();
            carrito.setCostoTotal(this.costoTotal);
            return carrito;
        }
}
