/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.persistence.CarritoDeComprasPersistence;
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
public class CarritoDeComprasPersistenceTest {
    
    @Inject
    private CarritoDeComprasPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoDeComprasEntity.class.getPackage())
                .addPackage(CarritoDeComprasPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    @Test
    public void createCarritoDeComprasTest(){
        PodamFactory factory = new PodamFactoryImpl();
        CarritoDeComprasEntity newEntity = factory.manufacturePojo(CarritoDeComprasEntity.class);
        CarritoDeComprasEntity ce = cp.create(newEntity);
        Assert.assertNotNull(ce);
        CarritoDeComprasEntity entity = em.find(CarritoDeComprasEntity.class, ce.getId());
        Assert.assertEquals(newEntity.getCostoTotal(), entity.getCostoTotal());
        
    }
    
}
