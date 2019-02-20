/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@RunWith(Arquillian.class)
public class CompradorPersistenceTest {

    @Inject
    private CompradorPersistence compradorPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<CompradorEntity> data = new ArrayList<CompradorEntity>();

    /**
     * 
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(CompradorPersistence.class.getPackage())
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
        em.createQuery("delete from CompradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);

            data.add(entity);

            em.persist(entity);
        }
    }

    /**
     * Prueba para crear un comprador
     */
    @Test
    public void createCompradorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        CompradorEntity newEntity = factory.manufacturePojo(CompradorEntity.class);
        CompradorEntity result = compradorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        CompradorEntity entity = em.find(CompradorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
    }

    /**
     * Prueba para obtener una lista de compradores
     */
    @Test
    public void getCompradoresTest() {
        List<CompradorEntity> list = compradorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (CompradorEntity ent : list) {
            boolean found = false;
            for (CompradorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para obtener un comprador
     */
    @Test
    public void getCompradorTest() {
        CompradorEntity entity = data.get(0);
        CompradorEntity newEntity = compradorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getUsuario(), newEntity.getUsuario());
    }
    
    /**
     * Prueba para actualizar un comprador
     */
    @Test
    public void updateCompradorTest() {
        CompradorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CompradorEntity newEntity = factory.manufacturePojo(CompradorEntity.class);

        newEntity.setId(entity.getId());

        compradorPersistence.update(newEntity);

        CompradorEntity resp = em.find(CompradorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getUsuario(), resp.getUsuario());
    }
    
    /**
     * Prueba para eliminar un comprador
     */
    @Test
    public void deleteCompradorTest() {
        CompradorEntity entity = data.get(0);
        compradorPersistence.delete(entity.getId());
        CompradorEntity deleted = em.find(CompradorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
