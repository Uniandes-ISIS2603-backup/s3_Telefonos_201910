/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
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
public class ProveedorPersistenceTest {

    @Inject
    private ProveedorPersistence proveedorPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ProveedorEntity> data = new ArrayList<ProveedorEntity>();

    /**
     * 
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorPersistence.class.getPackage())
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
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

           ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);

            data.add(entity);

            em.persist(entity);
        }
    }

    /**
     * Prueba para crear un proveedor
     */
    @Test
    public void createProveedorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);
        ProveedorEntity result = proveedorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ProveedorEntity entity = em.find(ProveedorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
    }

    /**
     * Prueba para obtener una lista de proveedores
     */
    @Test
    public void getProveedoresTest() {
        List<ProveedorEntity> list = proveedorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ProveedorEntity ent : list) {
            boolean found = false;
            for (ProveedorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para obtener un proveedor
     */
    @Test
    public void getProveedorTest() {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity newEntity = proveedorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getUsuario(), newEntity.getUsuario());
    }
    
    /**
     * Prueba para actualizar un proveedor
     */
    @Test
    public void updateProveedorTest() {
        ProveedorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ProveedorEntity newEntity = factory.manufacturePojo(ProveedorEntity.class);

        newEntity.setId(entity.getId());

        proveedorPersistence.update(newEntity);

        ProveedorEntity resp = em.find(ProveedorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getUsuario(), resp.getUsuario());
    }
    
    /**
     * Prueba para eliminar un proveedor
     */
    @Test
    public void deleteCompradorTest() {
        ProveedorEntity entity = data.get(0);
        proveedorPersistence.delete(entity.getId());
        ProveedorEntity deleted = em.find(ProveedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para encontrar un proveedor por su usuario
     */
     @Test
    public void findProveedorByUsuarioTest() {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity newEntity = proveedorPersistence.findByUsername(entity.getUsuario());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getUsuario(), newEntity.getUsuario());

        newEntity = proveedorPersistence.findByUsername(null);
        Assert.assertNull(newEntity);
    }
    
    /**
     * Prueba para encontrar un proveedor por su correo
     */
     @Test
    public void findProveedorByCorreoTest() {
        ProveedorEntity entity = data.get(0);
        ProveedorEntity newEntity = proveedorPersistence.findByEmail(entity.getCorreoElectronico());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getUsuario(), newEntity.getUsuario());

        newEntity = proveedorPersistence.findByEmail(null);
        Assert.assertNull(newEntity);
    }
}
