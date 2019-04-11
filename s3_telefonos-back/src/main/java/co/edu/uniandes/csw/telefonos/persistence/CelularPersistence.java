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
    /**
     * PERSISTENCE CONTEXT
     */
    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;
    
    /**
     * crea un celular
     * @param celularEntity que se va a crear
     * @return el celularEntity creado
     */
    public CelularEntity create (CelularEntity celularEntity){
        em.persist(celularEntity);
        return celularEntity;
    }
    
    /**
     * Encuentra por id un celular
     * @param celularsId id del celular a encontrar
     * @return el celular encontrado
     */
    public CelularEntity find (Long celularsId){
        return em.find(CelularEntity.class, celularsId);
    }
    
    /**
     * obtiene por imei
     * @param celularsImei imei del celular
     * @return celular encontrado
     */
    public CelularEntity findByImei (Long celularsImei){
        return em.find(CelularEntity.class, celularsImei);
    }
    
    /**
     * obtiene celular no registrado
     * @param modelo del celular no registrado
     * @return el celular no registrado encontrado
     */
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
    
    /**
     * Obtiene todos los celulares
     * @return list de celularentity
     */
    public List<CelularEntity> findAll(){
        TypedQuery<CelularEntity> query = em.createQuery("select u from CelularEntity u", CelularEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza un celular
     * @param celular que actualiza
     * @return el celular actualizado
     */
    public CelularEntity update(CelularEntity celular) {
        return em.merge(celular);
    }
    
    /**
     * Borra celular por id
     * @param celularId id del celular a borrar
     */
    public void delete(Long celularId){
        CelularEntity celularEntity = em.find(CelularEntity.class, celularId);
        em.remove(celularEntity);
    }
    
    
    /**
     * Borra por ime
     * @param celularImei imei del celular a borrar 
     */
    public void deleteByImei(Long celularImei){
        CelularEntity celularEntity = this.findByImei(celularImei);
        em.remove(celularEntity);
    }
    
}
