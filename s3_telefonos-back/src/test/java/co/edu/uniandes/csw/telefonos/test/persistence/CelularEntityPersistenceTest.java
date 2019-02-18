/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.persistence.CelularPersistence;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Daniel Babativa
 */
@RunWith(Arquillian.class)
public class CelularEntityPersistenceTest {
    @Inject
    private CelularPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CelularEntity.class.getPackage())
                .addPackage(CelularPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createCelularTest(){
        PodamFactory factory = new PodamFactoryImpl();
        CelularEntity newEntity = factory.manufacturePojo(CelularEntity.class);
        CelularEntity ce = cp.create(newEntity);
        Assert.assertNotNull(ce);
        CelularEntity entity = em.find(CelularEntity.class, ce.getId());
        Assert.assertEquals(newEntity.getImei(), entity.getImei());
        
    }
}
