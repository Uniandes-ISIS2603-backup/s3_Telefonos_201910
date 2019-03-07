/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.telefonos.ejb.ProveedorSegurosLogic;
import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
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
public class ProveedorSegurosLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ProveedorLogic proveedorLogic;
    @Inject
    private ProveedorSegurosLogic proveedorSegurosLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ProveedorEntity> data = new ArrayList<ProveedorEntity>();

    private List<SeguroEntity> segurosData = new ArrayList();
    
     /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ProveedorEntity.class.getPackage())
                .addPackage(ProveedorLogic.class.getPackage())
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
        em.createQuery("delete from ProveedorEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            SeguroEntity facturas = factory.manufacturePojo(SeguroEntity.class);
            em.persist(facturas);
            segurosData.add(facturas);
        }
        for (int i = 0; i < 3; i++) {
            ProveedorEntity entity = factory.manufacturePojo(ProveedorEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                segurosData.get(i).setProveedor(entity);
            }
        }
    }
    
    /**
     * Prueba para asociar un Seguro existente a un Proveedor.
     */
    @Test
    public void addSeguroTest() {
        ProveedorEntity entity = data.get(0);
        SeguroEntity seguroEntity = segurosData.get(1);
        SeguroEntity response = proveedorSegurosLogic.addSeguro(seguroEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(seguroEntity.getId(), response.getId());
    }
    
    /**
     * Prueba para obtener una colección de instancias de Seguros asociados a una
     * instancia Proveedor.
     */
    @Test
    public void getSegurosTest() {
        List<SeguroEntity> list = proveedorSegurosLogic.getSeguros(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }
    
    /**
     * Prueba para obtener una instancia de Seguro asociada a una instancia
     * Proveedor.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getSeguroTest() throws BusinessLogicException {
        ProveedorEntity entity = data.get(0);
        SeguroEntity seguroEntity = segurosData.get(0);
        SeguroEntity response = proveedorSegurosLogic.getSeguro(entity.getId(), seguroEntity.getId());

        Assert.assertEquals(seguroEntity.getId(), response.getId());
        Assert.assertEquals(seguroEntity.getAseguradora(), response.getAseguradora());
    }
    
    /**
     * Prueba para obtener una instancia de Seguro asociado a una instancia
     * Proveedor que no le pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void getSeguroNoAsociadoTest() throws BusinessLogicException {
        ProveedorEntity entity = data.get(0);
        SeguroEntity seguroEntity = segurosData.get(1);
        proveedorSegurosLogic.getSeguro(entity.getId(), seguroEntity.getId());
    }
}
