/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.PublicacionLogic;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.PublicacionPersistence;
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
 * @author rj.gonzalez10
 */
@RunWith(Arquillian.class)
public class PublicacionLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PublicacionLogic publicacionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PublicacionEntity> data = new ArrayList<PublicacionEntity>();

    

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PublicacionEntity.class.getPackage())
                .addPackage(PublicacionLogic.class.getPackage())
                .addPackage(PublicacionPersistence.class.getPackage())
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
        em.createQuery("delete from PublicacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
       
        for (int i = 0; i < 3; i++) {
            PublicacionEntity entity = factory.manufacturePojo(PublicacionEntity.class);
            em.persist(entity);
            data.add(entity);
            
            }
        }
     @Test
    public void createPublicacionTest() throws BusinessLogicException {
        PublicacionEntity newEntity = factory.manufacturePojo(PublicacionEntity.class);
        PublicacionEntity result = publicacionLogic.createPublicacion(newEntity);
        Assert.assertNotNull(result);
        PublicacionEntity entity = em.find(PublicacionEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
    }
  @Test
    public void getEditorialsTest() {
        List<PublicacionEntity> list = publicacionLogic.getPublicaciones();
        Assert.assertEquals(data.size(), list.size());
        for (PublicacionEntity entity : list) {
            boolean found = false;
            for (PublicacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Editorial.
     */
    @Test
    public void getEditorialTest() {
        PublicacionEntity entity = data.get(0);
        PublicacionEntity resultEntity = publicacionLogic.getPublicacion(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getPrecio(), resultEntity.getPrecio());
    }

    /**
     * Prueba para actualizar una publicacion.
     */
    @Test
    public void updateEditorialTest() {
        PublicacionEntity entity = data.get(0);
        PublicacionEntity pojoEntity = factory.manufacturePojo(PublicacionEntity.class);
        pojoEntity.setId(entity.getId());
        publicacionLogic.updatePublicacion(pojoEntity.getId(), pojoEntity);
        PublicacionEntity resp = em.find(PublicacionEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getPrecio(), resp.getPrecio());
    }

    /**
     * Prueba para eliminar un Editorial.
     *
     * @throws co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteEditorialTest() throws BusinessLogicException {
        PublicacionEntity entity = data.get(1);
        publicacionLogic.deletePublicacion(entity.getId());
        PublicacionEntity deleted = em.find(PublicacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    
}
