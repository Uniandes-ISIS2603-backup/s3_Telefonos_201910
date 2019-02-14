/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Stateless
public class CompradorPersistence {
    
    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;
    
    
    public CompradorEntity create(CompradorEntity compradorEntity){
        em.persist(compradorEntity);
        return compradorEntity;
    }
}
