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
import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import java.io.Serializable;

public class CarritoDeComprasDTO implements Serializable{
	/**
         * Suma del costo de las publicaciones carrito de compras
         */
	private Double costoTotal;
	
        /**
         * constructor by default
         */
	public CarritoDeComprasDTO()
	{
		
	}
        
        /**
         * Constructor con Entity
         * @param carrito entity que se convierte a DTO
         */
        public CarritoDeComprasDTO(CarritoDeComprasEntity carrito){
            if(carrito!=null){
                this.costoTotal = carrito.getCostoTotal();
            }
	}
	
        /**
         * Retorna el costo total del carrito de compras
         * @return costo total del carrito
         */
	public Double getCostoTotal()
	{
		return costoTotal;
	}
	
        /**
         * Actualiza el costo total del carrito de compras
         * @param costoTotal el nuevo costo total del carrito de compras
         */
	public void setCostoTotal(Double costoTotal)
	{
		this.costoTotal = costoTotal;
	}
        
        /**
         * Metodo que convierte el DTO a un Entity
         * @return el nuevo carrito de compras en forma entity
         */
	public CarritoDeComprasEntity toEntity(){
            CarritoDeComprasEntity carrito = new CarritoDeComprasEntity();
            carrito.setCostoTotal(this.costoTotal);
            return carrito;
        }
}
