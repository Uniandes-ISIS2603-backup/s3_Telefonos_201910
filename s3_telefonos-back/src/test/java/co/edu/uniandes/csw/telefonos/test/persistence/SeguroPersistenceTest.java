/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
import co.edu.uniandes.csw.telefonos.persistence.SeguroPersistence;
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
public class SeguroPersistenceTest {

    @Inject
    private SeguroPersistence seguroPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<SeguroEntity> data = new ArrayList<SeguroEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(SeguroEntity.class.getPackage())
                .addPackage(SeguroPersistence.class.getPackage())
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
        em.createQuery("delete from SeguroEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            SeguroEntity entity = factory.manufacturePojo(SeguroEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un seguro
     */
    @Test
    public void createSeguroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        SeguroEntity newEntity = factory.manufacturePojo(SeguroEntity.class);
        SeguroEntity result = seguroPersistence.create(newEntity);

        Assert.assertNotNull(result);

        SeguroEntity entity = em.find(SeguroEntity.class, result.getId());

        Assert.assertEquals(newEntity.getAseguradora(), entity.getAseguradora());
    }

    /**
     * Prueba para obtener una lista de seguros
     */
    @Test
    public void getSegurosTest() {
        List<SeguroEntity> list = seguroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (SeguroEntity ent : list) {
            boolean found = false;
            for (SeguroEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para obtener un seguro
     */
    @Test
    public void getSeguroTest() {
        SeguroEntity entity = data.get(0);
        SeguroEntity newEntity = seguroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }

    /**
     * Prueba para actualizar unn seguro
     */
    @Test
    public void updateSeguroTest() {
        SeguroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        SeguroEntity newEntity = factory.manufacturePojo(SeguroEntity.class);

        newEntity.setId(entity.getId());

        seguroPersistence.update(newEntity);

        SeguroEntity resp = em.find(SeguroEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getAseguradora(), resp.getAseguradora());
    }

    /**
     * Prueba para eliminar un seguro
     */
    @Test
    public void deleteSeguroTest() {
        SeguroEntity entity = data.get(0);
        seguroPersistence.delete(entity.getId());
        SeguroEntity deleted = em.find(SeguroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
