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
    
    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;
    
    private Integer costoTotal; 
    
    public CarritoDeComprasEntity create (CarritoDeComprasEntity carritoDeComprasEntity){
        em.persist(carritoDeComprasEntity);
        return carritoDeComprasEntity;
    }
    
    public CarritoDeComprasEntity find (Long carritoDeComprasId){
        return em.find(CarritoDeComprasEntity.class, carritoDeComprasId);
    }
    
    public List<CarritoDeComprasEntity> findAll(){
        TypedQuery<CarritoDeComprasEntity> query = em.createQuery("select u from CelularEntity u", CarritoDeComprasEntity.class);
        return query.getResultList();
    }
    
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
