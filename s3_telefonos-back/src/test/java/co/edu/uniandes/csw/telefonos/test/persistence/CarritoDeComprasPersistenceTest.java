/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.persistence.CarritoDeComprasPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
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

    @Inject
    UserTransaction utx;

    private List<CarritoDeComprasEntity> data = new ArrayList<CarritoDeComprasEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoDeComprasEntity.class.getPackage())
                .addPackage(CarritoDeComprasPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from BookEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CarritoDeComprasEntity entity = factory.manufacturePojo(CarritoDeComprasEntity.class);

            em.persist(entity);
            data.add(entity);
        }
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
