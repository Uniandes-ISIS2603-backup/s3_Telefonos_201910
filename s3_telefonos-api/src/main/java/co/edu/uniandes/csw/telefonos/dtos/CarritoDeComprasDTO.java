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
        
        private Long id;
	
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
                this.id = carrito.getId();
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
            carrito.setId(this.id);
            return carrito;
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
}
