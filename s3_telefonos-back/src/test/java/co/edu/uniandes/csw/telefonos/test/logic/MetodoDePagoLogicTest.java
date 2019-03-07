/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.MetodoDePagoLogic;
import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.MetodoDePagoPersistence;
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
public class MetodoDePagoLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MetodoDePagoLogic metodoDePagoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MetodoDePagoEntity> data = new ArrayList<MetodoDePagoEntity>();

  
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MetodoDePagoEntity.class.getPackage())
                .addPackage(MetodoDePagoLogic.class.getPackage())
                .addPackage(MetodoDePagoPersistence.class.getPackage())
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
        em.createQuery("delete from MetodoDePagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
       
        for (int i = 0; i < 3; i++) {
            MetodoDePagoEntity entity = factory.manufacturePojo(MetodoDePagoEntity.class);
            em.persist(entity);
            data.add(entity);
          
        }
    }

    /**
     * Prueba para crear un MetodoDePago.
     *
     * @throws co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException
     */
    @Test
    public void createMetodoDePagoTest() throws BusinessLogicException {
        MetodoDePagoEntity newEntity = factory.manufacturePojo(MetodoDePagoEntity.class);
        MetodoDePagoEntity result = metodoDePagoLogic.createMetodoDePago(newEntity);
        Assert.assertNotNull(result);
        MetodoDePagoEntity entity = em.find(MetodoDePagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getBanco(), entity.getBanco());
    }

    /**
     * Prueba para consultar la lista de MetodoDePagos.
     */
    @Test
    public void getMetodosDePagoTest() {
        List<MetodoDePagoEntity> list = metodoDePagoLogic.getMetodosDePago();
        Assert.assertEquals(data.size(), list.size());
        for (MetodoDePagoEntity entity : list) {
            boolean found = false;
            for (MetodoDePagoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un MetodoDePago.
     */
    @Test
    public void getMetodoDePagoTest() {
        MetodoDePagoEntity entity = data.get(0);
        MetodoDePagoEntity resultEntity = metodoDePagoLogic.getMetodoDePago(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getBanco(), resultEntity.getBanco());
    }

    /**
     * Prueba para actualizar un MetodoDePago.
     */
    @Test
    public void updateMetodoDePagoTest() {
        MetodoDePagoEntity entity = data.get(0);
        MetodoDePagoEntity pojoEntity = factory.manufacturePojo(MetodoDePagoEntity.class);
        pojoEntity.setId(entity.getId());
        metodoDePagoLogic.updateMetodoDePago(pojoEntity.getId(), pojoEntity);
        MetodoDePagoEntity resp = em.find(MetodoDePagoEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getBanco(), resp.getBanco());
    }

    /**
     * Prueba para eliminar un MetodoDePago.
     *
     * @throws co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException
     */
    @Test
    public void deleteMetodoDePagoTest() throws BusinessLogicException {
        MetodoDePagoEntity entity = data.get(1);
        metodoDePagoLogic.deleteMetodoDePago(entity.getId());
        MetodoDePagoEntity deleted = em.find(MetodoDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
