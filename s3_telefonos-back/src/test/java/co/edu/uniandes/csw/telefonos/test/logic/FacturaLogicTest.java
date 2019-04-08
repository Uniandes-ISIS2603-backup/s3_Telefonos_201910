/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.FacturaLogic;
import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.FacturaPersistence;
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
public class FacturaLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    /*
    Inyecta la dependencia de la clase FacturaLogic
     */
    @Inject
    private FacturaLogic facturaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
                .addPackage(FacturaLogic.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
        em.createQuery("delete from CompradorEntity").executeUpdate();
        em.createQuery("delete from ProveedorEntity").executeUpdate();
        em.createQuery("delete from PublicacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {

        for (int i = 0; i < 4; i++) {
            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        
        //Crea una factura con todos los atributos
        FacturaEntity facturaFull = data.get(0);
        CompradorEntity compradorEntity = factory.manufacturePojo(CompradorEntity.class);
        em.persist(compradorEntity);
        facturaFull.setComprador(compradorEntity);

        ProveedorEntity proveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        em.persist(proveedorEntity);
        facturaFull.setProveedor(proveedorEntity);
        
        PublicacionEntity publicacionEntity = factory.manufacturePojo(PublicacionEntity.class);
        em.persist(publicacionEntity);
        facturaFull.setPublicacion(publicacionEntity);
        
        //Crea una factura sin comprador
        FacturaEntity facturaComp = data.get(1);
   
        proveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        em.persist(proveedorEntity);
        facturaComp.setProveedor(proveedorEntity);
        
        publicacionEntity = factory.manufacturePojo(PublicacionEntity.class);
        em.persist(publicacionEntity);
        facturaComp.setPublicacion(publicacionEntity);
        
        //Crea una factura sin proveedor
        FacturaEntity facturaProv = data.get(2);
   
        compradorEntity = factory.manufacturePojo(CompradorEntity.class);
        em.persist(compradorEntity);
        facturaProv.setComprador(compradorEntity);
        
        publicacionEntity = factory.manufacturePojo(PublicacionEntity.class);
        em.persist(publicacionEntity);
        facturaProv.setPublicacion(publicacionEntity);
        
        //Crea una factura sin publicacion
        FacturaEntity facturaPub = data.get(3);
   
        compradorEntity = factory.manufacturePojo(CompradorEntity.class);
        em.persist(compradorEntity);
        facturaPub.setComprador(compradorEntity);
        
        proveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        em.persist(proveedorEntity);
        facturaProv.setProveedor(proveedorEntity);
    }

    /**
     * Prueba para crear una Factura.
     */
    @Test
    public void createFacturaTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setComprador(data.get(0).getComprador());
        newEntity.setProveedor(data.get(0).getProveedor());
        newEntity.setPublicacion(data.get(0).getPublicacion());
        FacturaEntity result = facturaLogic.createFactura(newEntity);
        Assert.assertNotNull(result);
        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
    }

    /**
     * Prueba para crear una Factura con la misma referencia de una Factura que
     * ya existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaConMismaReferenciaTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setReferencia(data.get(0).getReferencia());
        facturaLogic.createFactura(newEntity);
    }
    
    /**
     * Prueba para crear una Factura sin un comprador
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaSinCompradorTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setProveedor(data.get(1).getProveedor());
        newEntity.setPublicacion(data.get(1).getPublicacion());
        facturaLogic.createFactura(newEntity);
    }

    /**
     * Prueba para crear una Factura sin un proveedor
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaSinProveedorTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setComprador(data.get(2).getComprador());
        newEntity.setPublicacion(data.get(2).getPublicacion());
        facturaLogic.createFactura(newEntity);
    }
    
    /**
     * Prueba para crear una Factura sin una publicacion
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFacturaSinPublicacionTest() throws BusinessLogicException {
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        newEntity.setProveedor(data.get(3).getProveedor());
        newEntity.setComprador(data.get(3).getComprador());
        facturaLogic.createFactura(newEntity);
    }

    /**
     * Prueba para consultar la lista de Facturas.
     */
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = facturaLogic.getFacturas();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity entity : list) {
            boolean found = false;
            for (FacturaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Factura.
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity resultEntity = facturaLogic.getFactura(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getReferencia(), resultEntity.getReferencia());
    }

    /**
     * Prueba para actualizar una Factura.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateFacturaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setId(entity.getId());
        
        CompradorEntity compradorEntity = factory.manufacturePojo(CompradorEntity.class);
        pojoEntity.setComprador(compradorEntity);

        ProveedorEntity proveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        pojoEntity.setProveedor(proveedorEntity);
        
        PublicacionEntity publicacionEntity = factory.manufacturePojo(PublicacionEntity.class);
        pojoEntity.setPublicacion(publicacionEntity);
        
        facturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getReferencia(), resp.getReferencia());
    }
    
    /**
     * Prueba para actualizar una Factura con la misma referencia de una Factura que
     * ya existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateFacturaConMismaReferenciaTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        
        CompradorEntity compradorEntity = factory.manufacturePojo(CompradorEntity.class);
        pojoEntity.setComprador(compradorEntity);

        ProveedorEntity proveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        pojoEntity.setProveedor(proveedorEntity);
        
        PublicacionEntity publicacionEntity = factory.manufacturePojo(PublicacionEntity.class);
        pojoEntity.setPublicacion(publicacionEntity);
        
        pojoEntity.setReferencia(entity.getReferencia());
        facturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar una Factura sin comprador
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateFacturaSinCompradorTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setId(entity.getId());
        
        PublicacionEntity publicacionEntity = factory.manufacturePojo(PublicacionEntity.class);
        pojoEntity.setPublicacion(publicacionEntity);

        ProveedorEntity proveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        pojoEntity.setProveedor(proveedorEntity);
        
        facturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para actualizar una Factura sin proveedor
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateFacturaSinProveedorTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setId(entity.getId());
        
        CompradorEntity compradorEntity = factory.manufacturePojo(CompradorEntity.class);
        pojoEntity.setComprador(compradorEntity);

        PublicacionEntity publicacionEntity = factory.manufacturePojo(PublicacionEntity.class);
        pojoEntity.setPublicacion(publicacionEntity);
        
        facturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
    }

    /**
     * Prueba para actualizar una Factura sin publicacion
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void updateFacturaSinPublicacionTest() throws BusinessLogicException {
        FacturaEntity entity = data.get(0);
        FacturaEntity pojoEntity = factory.manufacturePojo(FacturaEntity.class);
        pojoEntity.setId(entity.getId());
        
        CompradorEntity compradorEntity = factory.manufacturePojo(CompradorEntity.class);
        pojoEntity.setComprador(compradorEntity);

        ProveedorEntity proveedorEntity = factory.manufacturePojo(ProveedorEntity.class);
        pojoEntity.setProveedor(proveedorEntity);
        
        facturaLogic.updateFactura(pojoEntity.getId(), pojoEntity);
    }
    
    /**
     * Prueba para eliminar una Factura.
     *
     */
    @Test
    public void deleteFacturaTest() {
        FacturaEntity entity = data.get(1);
        facturaLogic.deleteFactura(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
