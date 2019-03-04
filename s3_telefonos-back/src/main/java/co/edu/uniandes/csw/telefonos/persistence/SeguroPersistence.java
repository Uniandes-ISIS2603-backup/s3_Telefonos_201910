/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Stateless
public class SeguroPersistence {
    private final static Logger LOGGER = Logger.getLogger(TabletPersistence.class.getName());

    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;

    public SeguroEntity create(SeguroEntity seguroEntity) {

        LOGGER.log(Level.INFO, "Creando un seguro nuevo");
        em.persist(seguroEntity);

        LOGGER.log(Level.INFO, "Saliendo de crear un seguro nuevo");

        return seguroEntity;
    }

    public SeguroEntity find(Long seguroId) {
        return em.find(SeguroEntity.class, seguroId);
    }
    
    public List<SeguroEntity> findAll(){
        
        TypedQuery<SeguroEntity> query = em.createQuery("select u from SeguroEntity u", SeguroEntity.class);
        return query.getResultList();
    }
    
    public SeguroEntity update(SeguroEntity seguroEntity){
        LOGGER.log(Level.INFO, "Actualizando seguro con id ={0}", seguroEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar seguro con id ={0}", seguroEntity.getId());
        return em.merge(seguroEntity);
    }
    
    public void delete(Long seguroId){
        LOGGER.log(Level.INFO, "Borrando seguro con id ={0}", seguroId);
        SeguroEntity entity = em.find(SeguroEntity.class, seguroId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Borrando seguro con id ={0}", seguroId);
    }
    
  
}
