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
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Babativa
 */
@Stateless
public class CarritoDeComprasLogic {
    
    @Inject
    private CarritoDeComprasPersistence persistence;

    
    public CarritoDeComprasEntity createCarritoDeCompras(CarritoDeComprasEntity carrito)
    {
    
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
    }
     */

}
