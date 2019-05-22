6666644666666/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.TabletPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Stateless
public class TabletLogic {
    
    @Inject
    private TabletPersistence persistence;
    
    private static final Logger LOGGER = Logger.getLogger(TabletLogic.class.getName());
    
    public TabletEntity createTablet(TabletEntity tablet)throws BusinessLogicException{
        
        if(persistence.findByReferencia(tablet.getReferencia())!=null){
            throw new BusinessLogicException("Ya existe una tablet con la referencia \""+tablet.getReferencia());
        }
        tablet = persistence.create(tablet);
        return tablet;
    }
    
    
    public List<TabletEntity> getTablets(){
         LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las tabletas");
        List<TabletEntity> tablets = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las tabletas");
        return tablets;
    }
    
    public TabletEntity getTablet(String referencia){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la tableta con referencia = {0}", referencia);
        TabletEntity tablet = persistence.findByReferencia(referencia);
        if(tablet == null){
            LOGGER.log(Level.SEVERE, "La tableta con la referencia = {0} no existe", referencia);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la tableta con referencia = {0}", referencia);
        return tablet;
    }
    

    public TabletEntity updateTablet(String tabletId, TabletEntity tabletEntity) throws BusinessLogicException {
        
        //La referencia de cada factura debe ser unica
        if(persistence.findByReferencia(tabletEntity.getReferencia())!=null){
            throw new BusinessLogicException("Ya existe una Tablet con la referencia \"" + tabletEntity.getReferencia() + "\"");
        }
        
       TabletEntity newEntity = persistence.update(tabletEntity);
        return newEntity;
    }
    
    /**
     * Borrar una tablet
     *
     * @param tabletId: id de la factura a borrar
     */
    public void deleteTablet(Long tabletId) {
        persistence.delete(tabletId);
    }
    
    
}
