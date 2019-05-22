/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.CarritoDeComprasPersistence;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Babativa
 */
@Stateless
public class CarritoDeComprasLogic {
    
    /**
     * persistencia
     */
    @Inject
    private CarritoDeComprasPersistence persistence;

    
    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasLogic.class.getName());
    /**
     * Crea un carrito
     * @param carrito que se crea
     * @return el carrito creado entity
     * @throws BusinessLogicException 
     */
     public CarritoDeComprasEntity createCarritoDeCompras(CarritoDeComprasEntity carrito)throws BusinessLogicException{
        
        if(persistence.find(carrito.getId())!=null){
            throw new BusinessLogicException("Ya existe un carrito de compras con el identificador \""+carrito.getId());
        }
        carrito = persistence.create(carrito);
        return carrito;
    }
    
   /**
    * public PublicacionEntity agregarPublicacion(PublicacionEntity publicacion, CarritoDeComprasEntity carrito)throws BusinessLogicException
    {
        List<PublicacionEntity> publicaciones = carrito.getPublicaciones();
        if(publicaciones.size()>=10)
        {
            throw new BusinessLogicException("Tamaño máximo del carrito de compras ha sido alcanzado");
        }
        publicaciones.add(publicacion);
        carrito.setPublicaciones(publicaciones);
        return publicacion;
    }
    * @return 
    */
  /**
   * Obtiene todos los carritos de compras
   * @return lista de los carritosEntity 
   */
   public List<CarritoDeComprasEntity> getCarritoDeCompras(){
         
        List<CarritoDeComprasEntity> carritos = persistence.findAll();
        return carritos;
    }
    
   /**
    * obtiene un carrito por su id
    * @param id del carrito
    * @return el carritoentity
    */
    public CarritoDeComprasEntity getCarritoDeCompras(Long id){
        
        CarritoDeComprasEntity carrito = persistence.find(id);     
        return carrito;
    }

}
