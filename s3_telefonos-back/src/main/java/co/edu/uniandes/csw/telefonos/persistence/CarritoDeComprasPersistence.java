/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Daniel Babativa
 */

@Stateless
public class CarritoDeComprasPersistence {
    /**
     * PERSISTENCE CONTEXT
     */
    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;
    
    private static final Logger LOGGER = Logger.getLogger(CarritoDeComprasPersistence.class.getName());
    /**
     * crea un carrito
     * @param carritoDeComprasEntity carrito a crear
     * @return carrito creado
     */
    public CarritoDeComprasEntity create (CarritoDeComprasEntity carritoDeComprasEntity){
         LOGGER.log(Level.INFO, "Creando un carrito de compras");
        em.persist(carritoDeComprasEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un carrito de compras nuevo");
        return carritoDeComprasEntity;
    }
    
    /**
     * Obtiene un carrito
     * @param carritoDeComprasId id del carrito a obtener
     * @return el carritoEntity que se obtiene
     */
    public CarritoDeComprasEntity find (Long carritoDeComprasId){
        LOGGER.log(Level.INFO, "Consultando lista de deseos con id={0}", carritoDeComprasId);
        return em.find(CarritoDeComprasEntity.class, carritoDeComprasId);
    }
    
    /**
     * Obtiene todos los carritos
     * @return lista de carritoEntity
     */
    public List<CarritoDeComprasEntity> findAll(){
        
        TypedQuery<CarritoDeComprasEntity> query = em.createQuery("select u from CarritoDeComprasEntity u", CarritoDeComprasEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza un carrito
     * @param carritoDeComprasEntity con el cual se actualiza
     * @return el carritoEntity actualizado
     */
    public CarritoDeComprasEntity update(CarritoDeComprasEntity carritoDeComprasEntity) {
        LOGGER.log(Level.INFO, "Actualizando carrito de compras con id = {0}", carritoDeComprasEntity.getId());
        return em.merge(carritoDeComprasEntity);
    }

    /**
     * Elimina un comprador de la base de datos recibiendo como parametro el id
     * del comprador
     *
     * @param compradorId Identificador del comprador a eliminar
     */
    public void delete(Long carritoDeComprasId) {
        CarritoDeComprasEntity carritoDeComprasEntity = em.find(CarritoDeComprasEntity.class, carritoDeComprasId);
        em.remove(carritoDeComprasEntity);
    }
    
  
}
