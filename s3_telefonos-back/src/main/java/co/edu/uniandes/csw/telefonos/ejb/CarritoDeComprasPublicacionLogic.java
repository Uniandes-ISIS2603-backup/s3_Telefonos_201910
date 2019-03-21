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
import co.edu.uniandes.csw.telefonos.persistence.PublicacionPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
public class CarritoDeComprasPublicacionLogic {

    @Inject
    private PublicacionPersistence publicacionPersistence;

    @Inject
    private CarritoDeComprasPersistence carritoPersistence;

    public CarritoDeComprasEntity agregarPublicacion(Long publicacionId, Long carritoId) throws BusinessLogicException {
        CarritoDeComprasEntity carrito = carritoPersistence.find(carritoId);
        PublicacionEntity publicacion = publicacionPersistence.find(publicacionId);
        List<PublicacionEntity> publicaciones = carrito.getPublicaciones();
        if (publicaciones.size() >= 10) {
            throw new BusinessLogicException("Tamaño máximo del carrito de compras ha sido alcanzado");
        }
        publicaciones.add(publicacion);
        carrito.setPublicaciones(publicaciones);
        return carrito;

    }

    
    public CarritoDeComprasEntity removerPublicacion(Long publicacionId, Long carritoId) throws BusinessLogicException {
        CarritoDeComprasEntity carritoEntity = carritoPersistence.find(carritoId);

        ArrayList<PublicacionEntity> publicaciones = (ArrayList<PublicacionEntity>) carritoEntity.getPublicaciones();
        boolean encontro = false;
        for (PublicacionEntity publi : publicaciones) {
            if (publi.getId() == publicacionId) {
                encontro = true;
                publicaciones.remove(publi);
            }
        }
        if (!encontro) {
            throw new BusinessLogicException("La publicación no se pudo encontrar en el carrito de compras identificado con: " + carritoEntity.getId() );
        }
        carritoEntity.setPublicaciones(publicaciones);
        return carritoEntity;

    }

}
