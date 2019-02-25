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
    
    public CelularEntity findByImei (Long celularsImei){
        return em.find(CelularEntity.class, celularsImei);
    }
    
    public CelularEntity findNoRegistrado (String modelo){
        TypedQuery query = em.createQuery("Select e From CelularEntity e where e.modelo = :modelo", CelularEntity.class);

        query = query.setParameter("modelo", modelo);

        List<CelularEntity> sameModelo = query.getResultList();
        CelularEntity result;
        if (sameModelo == null) {
            result = null;
        } else if (sameModelo.isEmpty()) {
            result = null;
        } else {
            result = sameModelo.get(0);
        }

        return result;
    }
    
    public List<CelularEntity> findAll(){
        TypedQuery<CelularEntity> query = em.createQuery("select u from CelularEntity u", CelularEntity.class);
        return query.getResultList();
    }
    
    public CelularEntity update(CelularEntity celular) {
        return em.merge(celular);
    }
    
    public void delete(Long celularId){
        CelularEntity celularEntity = em.find(CelularEntity.class, celularId);
        em.remove(celularEntity);
    }
    
    public void deleteByImei(Long celularImei){
        CelularEntity celularEntity = this.findByImei(celularImei);
        em.remove(celularEntity);
    }
    
}
