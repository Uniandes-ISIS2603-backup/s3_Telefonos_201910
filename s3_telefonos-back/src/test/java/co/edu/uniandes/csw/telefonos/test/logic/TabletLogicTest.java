/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.TabletLogic;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.TabletPersistence;
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
 * @author Andres Felipe Daza Diaz
 */
@RunWith(Arquillian.class)
public class TabletLogicTest {
    
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
    private TabletLogic tabletLogic;
    
    private List<TabletEntity> data = new ArrayList<TabletEntity>();
    
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TabletEntity.class.getPackage())
                .addPackage(TabletPersistence.class.getPackage())
                .addPackage(TabletLogic.class.getPackage())
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
        em.createQuery("delete from TabletEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            TabletEntity entity = factory.manufacturePojo(TabletEntity.class);

            em.persist(entity);
            data.add(entity);

        }
    }
    
    @Test
    public void createTabletTest() throws BusinessLogicException{
        TabletEntity newEntity = factory.manufacturePojo(TabletEntity.class);
        TabletEntity resultado = tabletLogic.createTablet(newEntity);
        Assert.assertNotNull(resultado);
        TabletEntity entity = em.find(TabletEntity.class, resultado.getId());
        Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createTabletConMismaReferenciaTest() throws BusinessLogicException{
        TabletEntity newEntity = factory.manufacturePojo(TabletEntity.class);
        newEntity.setReferencia(data.get(0).getReferencia());
        tabletLogic.createTablet(newEntity);
    }
    
    @Test
    public void getTabletsTest(){
        List<TabletEntity> list = tabletLogic.getTablets();
        Assert.assertEquals(data.size(), list.size());
        for(TabletEntity entity: list){
            boolean encontro = false;
            for(TabletEntity storedEntity: data){
                if(entity.getId().equals(storedEntity.getId())){
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
}
