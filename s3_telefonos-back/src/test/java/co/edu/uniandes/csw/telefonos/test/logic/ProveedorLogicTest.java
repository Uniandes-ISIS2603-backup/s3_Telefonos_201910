/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.ProveedorPersistence;
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
public class ProveedorLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    /*
    Inyecta la dependencia de la clase ProveedorLogic
     */
    @Inject
    private ProveedorLogic proveedorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ProveedorEntity> data = new ArrayList<ProveedorEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorPersistence.class.getPackage())
                .addPackage(ProveedorLogic.class.getPackage())
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
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);
            em.persist(entity);
            data.add(entity);
        }

    }

    /**
     * Prueba para crear un Proveedor.
     */
    @Test
    public void createProveedorTest() throws BusinessLogicException {
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        ProveedorEntity result = proveedorLogic.createProveedor(newEntity);
        Assert.assertNotNull(result);
        ProveedorEntity entity = em.find(ProveedorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
        Assert.assertEquals(newEntity.getCorreoElectronico(), entity.getCorreoElectronico());
    }

    /**
     * Prueba para crear un Proveedor con el mismo usuario de un Proveedor que
     * ya existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createProveedorConMismoUsuarioTest() throws BusinessLogicException {
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        newEntity.setUsuario(data.get(0).getUsuario());
        proveedorLogic.createProveedor(newEntity);
    }

    /**
     * Prueba para crear un Proveedor con el mismo correo de un Proveedor que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createProveedorConMismoCorreoTest() throws BusinessLogicException {
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        newEntity.setCorreoElectronico(data.get(0).getCorreoElectronico());
        proveedorLogic.createProveedor(newEntity);
    }

    /**
     * Prueba para consultar la lista de Proveedores.
     */
    @Test
    public void getProveedoresTest() {
        List<ProveedorEntity> list = proveedorLogic.getProveedores();
        Assert.assertEquals(data.size(), list.size());
        for (ProveedorEntity entity : list) {
            boolean found = false;
            for (ProveedorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Proveedor.
     */
    @Test
    public void getProveedorTest() {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity resultEntity = proveedorLogic.getProveedor(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getUsuario(), resultEntity.getUsuario());
    }

    /**
     * Prueba para actualizar un Proveedor.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateProveedorTest() throws BusinessLogicException {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity pojoEntity = factory.manufacturePojo(ProveedorEntity.class);
        pojoEntity.setId(entity.getId());
        proveedorLogic.updateProveedor(pojoEntity.getId(), pojoEntity);
        ProveedorEntity resp = em.find(ProveedorEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getUsuario(), resp.getUsuario());
    }
    
    /**
     * Prueba para actualizar un Proveedor con el mismo usuario de un Proveedor que
     * ya existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateProveedorConMismoUsuarioTest() throws BusinessLogicException {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity pojoEntity = factory.manufacturePojo(ProveedorEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setUsuario(entity.getUsuario());
        proveedorLogic.updateProveedor(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar un Proveedor con el mismo correo de un Proveedor que ya
     * existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateProveedorConMismoCorreoTest() throws BusinessLogicException {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity pojoEntity = factory.manufacturePojo(ProveedorEntity.class);
        pojoEntity.setId(entity.getId());
        pojoEntity.setCorreoElectronico(entity.getCorreoElectronico());
        proveedorLogic.updateProveedor(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para eliminar un Proveedor.
     *
     */
    @Test
    public void deleteProveedorTest() {
        ProveedorEntity entity = data.get(1);
        proveedorLogic.deleteProveedor(entity.getId());
        ProveedorEntity deleted = em.find(ProveedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
