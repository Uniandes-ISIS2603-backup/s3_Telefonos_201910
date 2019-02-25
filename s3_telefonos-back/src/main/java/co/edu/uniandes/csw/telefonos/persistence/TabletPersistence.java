/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

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
public class TabletPersistence {

    private final static Logger LOGGER = Logger.getLogger(TabletPersistence.class.getName());

    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;

    public TabletEntity create(TabletEntity tabletEntity) {

        LOGGER.log(Level.INFO, "Creando una tableta nueva");
        em.persist(tabletEntity);

        LOGGER.log(Level.INFO, "Saliendo de crear una tableta nueva");

        return tabletEntity;
    }

    public TabletEntity find(Long tabletasId) {
        return em.find(TabletEntity.class, tabletasId);
    }
    
    public List<TabletEntity> findAll(){
        
        TypedQuery<TabletEntity> query = em.createQuery("select u from TabletEntity u", TabletEntity.class);
        return query.getResultList();
    }
    
    public TabletEntity update(TabletEntity tabletEntity){
        LOGGER.log(Level.INFO, "Actualizando tableta con id ={0}", tabletEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar tableta con id ={0}", tabletEntity.getId());
        return em.merge(tabletEntity);
    }
    
    public void delete(Long tabletsId){
        LOGGER.log(Level.INFO, "Borrando tableta con id ={0}", tabletsId);
        TabletEntity entity = em.find(TabletEntity.class, tabletsId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Borrando tableta con id ={0}", tabletsId);
    }
    
    public TabletEntity findByReferencia(String pReferencia){
        LOGGER.log(Level.INFO, "Buscando tablet con referencia ", pReferencia);    
        TypedQuery query=em.createQuery("Select e From TabletEntity e where e.referencia = :referencia", TabletEntity.class);
        query = query.setParameter("referencia", pReferencia);
        List<TabletEntity> mismaReferencia = query.getResultList();
        TabletEntity resultado;
        if(mismaReferencia == null){
            resultado=null;
        }else if(mismaReferencia.isEmpty()){
            resultado = null;
        }else{
            resultado=mismaReferencia.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar tableta con referencia ", pReferencia);     
        return resultado;
    }
}
