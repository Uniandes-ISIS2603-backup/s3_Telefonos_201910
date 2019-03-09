/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.CelularLogic;
import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.CelularPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class CelularLogicTest {
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
    private CelularLogic celularLogic;
    
    private List<CelularEntity> data = new ArrayList<CelularEntity>();
    
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CelularEntity.class.getPackage())
                .addPackage(CelularPersistence.class.getPackage())
                .addPackage(CelularLogic.class.getPackage())
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
        em.createQuery("delete from CelularEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            CelularEntity entity = factory.manufacturePojo(CelularEntity.class);

            em.persist(entity);
            data.add(entity);

        }
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCelularTest() throws BusinessLogicException{
        CelularEntity newEntity = factory.manufacturePojo(CelularEntity.class);
        CelularEntity resultado = celularLogic.createCelular(newEntity);
        Assert.assertNotNull(resultado);
        CelularEntity entity = em.find(CelularEntity.class, resultado.getId());
        Assert.assertEquals(newEntity.getImei(), entity.getImei());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createCelularFailTest() throws BusinessLogicException{
        CelularEntity newEntity = factory.manufacturePojo(CelularEntity.class);
        newEntity.setImei(data.get(0).getImei());
        celularLogic.createCelular(newEntity);
    }
    
    @Test
    public void getCelularesTest(){
        List<CelularEntity> list = celularLogic.getCelulares();
        Assert.assertEquals(data.size(), list.size());
        for(CelularEntity entity: list){
            boolean encontro = false;
            for(CelularEntity storedEntity: data){
                if(entity.getId().equals(storedEntity.getId())){
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
}
