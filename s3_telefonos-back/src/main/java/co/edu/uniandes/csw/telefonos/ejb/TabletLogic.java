/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.TabletPersistence;
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
    
    public TabletEntity createTablet(TabletEntity tablet)throws BusinessLogicException{
        
        if(persistence.findByReferencia(tablet.getReferencia())!=null){
            throw new BusinessLogicException("Ya existe una tablet con la referencia \""+tablet.getReferencia());
        }
        tablet = persistence.create(tablet);
        return tablet;
    }
}
