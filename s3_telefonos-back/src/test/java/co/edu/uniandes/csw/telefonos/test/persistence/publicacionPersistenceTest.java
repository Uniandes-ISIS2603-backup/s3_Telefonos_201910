/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.persistence.PublicacionPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

/**
 *
 * @author rj.gonzalez10
 */
@RunWith(Arquillian.class)
public class publicacionPersistenceTest {
     @Inject
    private PublicacionPersistence publicacionPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    
}
