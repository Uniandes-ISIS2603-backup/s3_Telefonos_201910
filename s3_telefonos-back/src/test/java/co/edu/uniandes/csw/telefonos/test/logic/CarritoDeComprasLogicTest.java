/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.CarritoDeComprasLogic;
import co.edu.uniandes.csw.telefonos.ejb.CarritoDeComprasPublicacionLogic;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.CarritoDeComprasPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CarritoDeComprasLogicTest {
       
    private PodamFactory factory = new PodamFactoryImpl();
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    private UserTransaction utx;
    
    @Inject
    private CarritoDeComprasLogic carritoLogic;
    
    @Inject
    private CarritoDeComprasPublicacionLogic carritoPublicacionLogic;
    
   
    
    private List<CarritoDeComprasEntity> data = new ArrayList<CarritoDeComprasEntity>();
    
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CarritoDeComprasEntity.class.getPackage())
                .addPackage(CarritoDeComprasPersistence.class.getPackage())
                .addPackage(CarritoDeComprasLogic.class.getPackage())
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
        em.createQuery("delete from CarritoDeComprasEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas. En el indice 0 se guardara una lista de deseos con 9 tablets, en el indice
     * 1 se guardara una con 9 celulares, en el indice 2 se guardara una con 5 tablets y 5 celulares
     */
    private void insertData() {
      
            for (int i = 0; i < 3; i++) {
            CarritoDeComprasEntity entity = factory.manufacturePojo(CarritoDeComprasEntity.class);
            
            em.persist(entity);
            data.add(entity);
            }
         

    }
    
    /**
     * @Test
    public void agregarPublicacionCarritoTest()throws BusinessLogicException{
        PublicacionEntity nueva = factory.manufacturePojo(PublicacionEntity.class);
        CarritoDeComprasEntity carrito = data.get(0);
        carrito = carritoPublicacionLogic.agregarPublicacion(nueva.getId(), carrito.getId());
        Assert.assertEquals(10, carrito.getPublicaciones().size());
      
    }
    
     */

    
    /**
     * @Test(expected=BusinessLogicException.class)
    public void agregarPublicacionCarritoFailTest()throws BusinessLogicException{
        CarritoDeComprasEntity carrito = data.get(1);
        PublicacionEntity publi = factory.manufacturePojo(PublicacionEntity.class);
        carrito = carritoPublicacionLogic.agregarPublicacion(publi.getId(), carrito.getId());
    }
    
     */
    
    @Test
    public void createCarritoDeComprasTest() throws BusinessLogicException{
        CarritoDeComprasEntity newEntity = factory.manufacturePojo(CarritoDeComprasEntity.class);
        CarritoDeComprasEntity resultado = carritoLogic.createCarritoDeCompras(newEntity);
        Assert.assertNotNull(resultado);
        CarritoDeComprasEntity entity = em.find(CarritoDeComprasEntity.class, resultado.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCarritoDeComprasMismoIdTest() throws BusinessLogicException{
        CarritoDeComprasEntity newEntity = factory.manufacturePojo(CarritoDeComprasEntity.class);
        newEntity.setId(data.get(0).getId());
        carritoLogic.createCarritoDeCompras(newEntity);
    }
}
