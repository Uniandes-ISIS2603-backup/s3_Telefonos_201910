/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
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
public class TabletPersistenceTest {
    
    @Inject
    private TabletPersistence tabletPersistence;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<TabletEntity> data = new ArrayList<TabletEntity>();
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TabletEntity.class.getPackage())
                .addPackage(TabletPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
                
    }
    
    @Before
    public void configTest(){
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        }catch(Exception e){
            e.printStackTrace();
           try{
               utx.rollback();
           }catch(Exception ee){
               ee.printStackTrace();
           }
        }
    }
    
    private void clearData(){
        em.createQuery("delete from TabletEntity").executeUpdate();
    }
    
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3;i++){
            TabletEntity entity = factory.manufacturePojo(TabletEntity.class);
           em.persist(entity);
           data.add(entity);
        }
    }
    
    @Test
    public void createTabletTest(){
        PodamFactory factory=new PodamFactoryImpl();
        TabletEntity newEntity = factory.manufacturePojo(TabletEntity.class);
        TabletEntity result = tabletPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        TabletEntity entity = em.find(TabletEntity.class, result.getId());
        Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
    }
    
    
    @Test
    public void deleteTabletTest(){
        TabletEntity entity=data.get(0);
        tabletPersistence.delete(entity.getId());
        TabletEntity borrada = em.find(TabletEntity.class, entity.getId());
        Assert.assertNull(borrada);
    }
    
    @Test
    public void FindTabletByReferencia(){
        TabletEntity entity=data.get(0);
        TabletEntity newEntity = tabletPersistence.findByReferencia(entity.getReferencia());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getReferencia(), entity.getReferencia());
    }
}
