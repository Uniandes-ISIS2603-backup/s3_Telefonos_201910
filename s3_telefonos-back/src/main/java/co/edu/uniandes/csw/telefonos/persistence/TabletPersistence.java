/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Stateless
public class TabletPersistence {
    
    private final static Logger LOGGER = Logger.getLogger(TabletPersistence.class.getName());
    
    @PersistenceContext(unitName= "telefonosPU")
    protected EntityManager em;
    
    public TabletEntity create(TabletEntity tabletEntity){
        
        LOGGER.log(Level.INFO, "Creando una tableta nueva");
        em.persist(tabletEntity);
        
        LOGGER.log(Level.INFO, "Saliendo de crear una tableta nueva");

        return tabletEntity;
    }
}
