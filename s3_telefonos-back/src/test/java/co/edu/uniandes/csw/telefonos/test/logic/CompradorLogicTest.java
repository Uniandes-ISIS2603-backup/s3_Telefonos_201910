/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.CompradorLogic;
import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
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
public class CompradorLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    /*
    Inyecta la dependencia de la clase CompradorLogic
     */
    @Inject
    private CompradorLogic compradorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<CompradorEntity> data = new ArrayList<CompradorEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompradorEntity.class.getPackage())
                .addPackage(CompradorPersistence.class.getPackage())
                .addPackage(CompradorLogic.class.getPackage())
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
        em.createQuery("delete from CompradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            CompradorEntity entity = factory.manufacturePojo(CompradorEntity.class);
            em.persist(entity);
            data.add(entity);
        }

    }

    /**
     * Prueba para crear un Comprador.
     */
    @Test
    public void createCompradorTest() throws BusinessLogicException {
        CompradorEntity newEntity = factory.manufacturePojo(CompradorEntity.class);
        CompradorEntity result = compradorLogic.createComprador(newEntity);
        Assert.assertNotNull(result);
        CompradorEntity entity = em.find(CompradorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
        Assert.assertEquals(newEntity.getCorreoElectronico(), entity.getCorreoElectronico());
    }

    /**
     * Prueba para crear un Comprador con el mismo usuario de un Comprador que
     * ya existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCompradorConMismoUsuarioTest() throws BusinessLogicException {
        CompradorEntity newEntity = factory.manufacturePojo(CompradorEntity.class);
        newEntity.setUsuario(data.get(0).getUsuario());
        compradorLogic.createComprador(newEntity);
    }

    /**
     * Prueba para crear un Comprador con el mismo correo de un Comprador que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createCompradorConMismoCorreoTest() throws BusinessLogicException {
        CompradorEntity newEntity = factory.manufacturePojo(CompradorEntity.class);
        newEntity.setCorreoElectronico(data.get(0).getCorreoElectronico());
        compradorLogic.createComprador(newEntity);
    }

    /**
     * Prueba para consultar la lista de Compradores.
     */
    @Test
    public void getCompradoresTest() {
        List<CompradorEntity> list = compradorLogic.getCompradores();
        Assert.assertEquals(data.size(), list.size());
        for (CompradorEntity entity : list) {
            boolean found = false;
            for (CompradorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Comprador.
     */
    @Test
    public void getCompradorTest() {
        CompradorEntity entity = data.get(0);
        CompradorEntity resultEntity = compradorLogic.getComprador(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getUsuario(), resultEntity.getUsuario());
    }

    /**
     * Prueba para actualizar un Comprador.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateCompradorTest() throws BusinessLogicException {
        CompradorEntity entity = data.get(0);
        CompradorEntity pojoEntity = factory.manufacturePojo(CompradorEntity.class);
        pojoEntity.setId(entity.getId());
        compradorLogic.updateComprador(pojoEntity.getId(), pojoEntity);
        CompradorEntity resp = em.find(CompradorEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getUsuario(), resp.getUsuario());
    }
    
    /**
     * Prueba para actualizar un Comprador con el mismo usuario de un Comprador que
     * ya existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCompradorConMismoUsuarioTest() throws BusinessLogicException {
        CompradorEntity entity = data.get(0);
        CompradorEntity pojoEntity = factory.manufacturePojo(CompradorEntity.class);
        pojoEntity.setUsuario(entity.getUsuario());
        compradorLogic.updateComprador(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Comprador con el mismo correo de un Comprador que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateCompradorConMismoCorreoTest() throws BusinessLogicException {
        CompradorEntity entity = data.get(0);
        CompradorEntity pojoEntity = factory.manufacturePojo(CompradorEntity.class);
        pojoEntity.setCorreoElectronico(entity.getCorreoElectronico());
        compradorLogic.updateComprador(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para eliminar un Comprador.
     *
     */
    @Test
    public void deleteCompradorTest() {
        CompradorEntity entity = data.get(1);
        compradorLogic.deleteComprador(entity.getId());
        CompradorEntity deleted = em.find(CompradorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
