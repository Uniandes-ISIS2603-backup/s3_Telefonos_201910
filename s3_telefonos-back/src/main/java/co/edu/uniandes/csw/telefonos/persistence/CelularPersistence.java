/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
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
public class CelularPersistence {
    
    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;
    
    public CelularEntity create (CelularEntity celularEntity){
        em.persist(celularEntity);
        return celularEntity;
    }
    
    public CelularEntity find (Long celularsId){
        return em.find(CelularEntity.class, celularsId);
    }
    
    public List<CelularEntity> findAll(){
        TypedQuery<CelularEntity> query = em.createQuery("select u from CelularEntity u", CelularEntity.class);
        return query.getResultList();
    }
    
}
