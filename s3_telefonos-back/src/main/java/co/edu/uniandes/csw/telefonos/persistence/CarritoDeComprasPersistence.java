/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import java.util.List;
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
    
    /**
     * costo total del carrito
     */
    private Integer costoTotal; 
    
    /**
     * crea un carrito
     * @param carritoDeComprasEntity carrito a crear
     * @return carrito creado
     */
    public CarritoDeComprasEntity create (CarritoDeComprasEntity carritoDeComprasEntity){
        em.persist(carritoDeComprasEntity);
        return carritoDeComprasEntity;
    }
    
    /**
     * Obtiene un carrito
     * @param carritoDeComprasId id del carrito a obtener
     * @return el carritoEntity que se obtiene
     */
    public CarritoDeComprasEntity find (Long carritoDeComprasId){
        return em.find(CarritoDeComprasEntity.class, carritoDeComprasId);
    }
    
    /**
     * Obtiene todos los carritos
     * @return lista de carritoEntity
     */
    public List<CarritoDeComprasEntity> findAll(){
        TypedQuery<CarritoDeComprasEntity> query = em.createQuery("select u from CelularEntity u", CarritoDeComprasEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza un carrito
     * @param carritoDeComprasEntity con el cual se actualiza
     * @return el carritoEntity actualizado
     */
    public CarritoDeComprasEntity update(CarritoDeComprasEntity carritoDeComprasEntity) {
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
