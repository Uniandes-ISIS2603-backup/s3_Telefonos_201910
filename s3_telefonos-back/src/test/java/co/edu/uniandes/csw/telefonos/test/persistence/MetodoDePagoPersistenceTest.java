/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.telefonos.persistence.MetodoDePagoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
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
 * @author rj.gonzalez10
 */
@RunWith(Arquillian.class)
public class MetodoDePagoPersistenceTest {
    @Inject
    private MetodoDePagoPersistence metodoDePagoPersistence; 
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    UserTransaction utx;
    
    private List<MetodoDePagoEntity> data = new ArrayList<MetodoDePagoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment()
    {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MetodoDePagoEntity.class.getPackage())
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
        em.createQuery("delete from MetodoDePagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            MetodoDePagoEntity entity = factory.manufacturePojo(MetodoDePagoEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    
    @Test
    public void createMetodoDePagoTest(){
        PodamFactory factory = new PodamFactoryImpl();
        MetodoDePagoEntity newEntity = factory.manufacturePojo(MetodoDePagoEntity.class);
        MetodoDePagoEntity result = metodoDePagoPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        MetodoDePagoEntity entity = em.find(MetodoDePagoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    @Test
    public void getMetodosDePagoTest(){
        List<MetodoDePagoEntity> list = metodoDePagoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for(MetodoDePagoEntity ent : list)
        {
            boolean encontrado = false;
            for (MetodoDePagoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                   encontrado = true;}
                
        }
            Assert.assertTrue(encontrado);
    }
}
    @Test
    public void getMetodoDePagoTest(){
        MetodoDePagoEntity entity = data.get(0);
        MetodoDePagoEntity newEntity = metodoDePagoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
                
    }
    @Test
    public void deleteMetodoDePagoTest(){
        MetodoDePagoEntity entity = data.get(0);
        metodoDePagoPersistence.delete(entity.getId());
        MetodoDePagoEntity deleted = em.find(MetodoDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    @Test
    public void updateMetodoTest(){
        MetodoDePagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        MetodoDePagoEntity newEntity = factory.manufacturePojo(MetodoDePagoEntity.class);

        newEntity.setId(entity.getId());

        metodoDePagoPersistence.update(newEntity);

        MetodoDePagoEntity resp = em.find(MetodoDePagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
}
