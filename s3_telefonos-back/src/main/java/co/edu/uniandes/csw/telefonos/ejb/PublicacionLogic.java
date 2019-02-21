/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.persistence.PublicacionPersistence;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class PublicacionLogic {
    private static final Logger LOGGER = Logger.getLogger(PublicacionLogic.class.getName());
    
    @Inject
    private PublicacionPersistence persistence;
    
    
}
