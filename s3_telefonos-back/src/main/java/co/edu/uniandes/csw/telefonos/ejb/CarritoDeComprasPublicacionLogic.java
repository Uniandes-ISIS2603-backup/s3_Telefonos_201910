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
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Babativa
 */
@Stateless
public class CarritoDeComprasPublicacionLogic {

    /**
     * Persistencia de la publicacion
     */
    @Inject
    private PublicacionPersistence publicacionPersistence;

    
    /**
     * Persistencia del carrito
     */
    @Inject
    private CarritoDeComprasPersistence carritoPersistence;

    /**
     * Método que agrega una publicacion al carrito (la asocia)
     * @param publicacionId id de la publicacion
     * @param carritoId id del carrito
     * @return el entity del carrito de compras
     * @throws BusinessLogicException 
     */
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
    
    /**
     * Retorna una publicacion de un carrito de compras
     * @param cId id del carrito
     * @param pId id de la publicacion
     * @return un entity de la publicacion
     * @throws BusinessLogicException 
     */
    public PublicacionEntity getPublicacion(Long cId, Long pId) throws BusinessLogicException {
        List<PublicacionEntity> ps = carritoPersistence.find(cId).getPublicaciones();
        PublicacionEntity pEntity = publicacionPersistence.find(pId);
        int index = ps.indexOf(pEntity);

        if (index >= 0) {
            return ps.get(index);
        }
        throw new BusinessLogicException("La publicacion no está asociada al carrito de compras");
}
    /**
     * Retorna la lista de publicaciones de un carrito
     * @param carritoId id del carrito
     * @return lista de publicaciones entity
     */
    public List<PublicacionEntity> getPublicaciones(Long carritoId)
    {
        return carritoPersistence.find(carritoId).getPublicaciones();
    }
    
    /**
     * Quita una publicacion de un carrito
     * @param publicacionId id de la publicacion
     * @param carritoId id del carrito
     * @return El carrito Entity sin la publicacion
     * @throws BusinessLogicException 
     */
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
