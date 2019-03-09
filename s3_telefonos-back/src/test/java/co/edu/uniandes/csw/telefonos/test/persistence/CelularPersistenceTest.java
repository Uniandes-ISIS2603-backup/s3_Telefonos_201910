/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;


import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.persistence.CelularPersistence;
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
public class CelularPersistenceTest {
    @Inject
    private CelularPersistence cp;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<CelularEntity> data = new ArrayList<CelularEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CelularEntity.class.getPackage())
                .addPackage(CelularPersistence.class.getPackage())
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
        em.createQuery("delete from CelularEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CelularEntity entity = factory.manufacturePojo(CelularEntity.class);

            em.persist(entity);
            data.add(entity);
        }
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
    
     @Test
    public void getCarritosTest() {
        List<CelularEntity> list = cp.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CelularEntity ent : list) {
            boolean found = false;
            for (CelularEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    
     @Test
    public void getCelularTest() {
        CelularEntity entity = data.get(0);
        CelularEntity newEntity = cp.find(entity.getId());
        Assert.assertNotNull(newEntity);
      Assert.assertEquals(entity.getId(), newEntity.getId());
        Assert.assertEquals(entity.getImei(), newEntity.getImei());
        Assert.assertEquals(entity.getMarca(), newEntity.getMarca());
        Assert.assertEquals(entity.getReferencia(), newEntity.getReferencia());
        Assert.assertEquals(entity.getReferencia(), newEntity.getReferencia());
    }
    
    
    @Test
     public void deleteCelularTest() {
        CelularEntity entity = data.get(0);
        cp.delete(entity.getId());
        CelularEntity deleted = em.find(CelularEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
    @Test
    public void updateCelularTest() {
        CelularEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CelularEntity newEntity = factory.manufacturePojo(CelularEntity.class);

        newEntity.setId(entity.getId());

        cp.update(newEntity);

        CelularEntity resp = em.find(CelularEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getId(), resp.getId());
        Assert.assertEquals(newEntity.getImei(), resp.getImei());
        Assert.assertEquals(newEntity.getMarca(), resp.getMarca());
        Assert.assertEquals(newEntity.getReferencia(), resp.getReferencia());
        Assert.assertEquals(newEntity.getReferencia(), resp.getReferencia());
    }
}
