/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.persistence;

import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.persistence.ListaDeDeseosPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
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
 * @author Andres Felipe Daza Diaz
 */
@RunWith(Arquillian.class)
public class ListaDeDeseosPersistenceTest {
    
    @Inject 
    private ListaDeDeseosPersistence listaPersistence;
    
    /**
     * Contexto de Persistencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    /**
     * Lista que tiene los datos de prueba.
     */
    private List<ListaDeDeseosEntity> data = new ArrayList<ListaDeDeseosEntity>();
    

     /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Editorial, el descriptor de la
     * base de datos y el archivo beans.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ListaDeDeseosEntity.class.getPackage())
                .addPackage(ListaDeDeseosPersistence.class.getPackage())
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
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from ListaDeDeseosEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            ListaDeDeseosEntity entity = factory.manufacturePojo(ListaDeDeseosEntity.class);

            em.persist(entity);

            data.add(entity);
        }
}
    
    /**
     * Prueba para crear una lista de deseos.
     *
     *
     */
    @Test
    public void createListaDeDeseosTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ListaDeDeseosEntity newEntity = factory.manufacturePojo(ListaDeDeseosEntity.class);
        ListaDeDeseosEntity result = listaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ListaDeDeseosEntity entity = em.find(ListaDeDeseosEntity.class, result.getId());

        Assert.assertEquals(newEntity, entity);
        //Assert.assertEquals(newEntity.getCelulares(), entity.getCelulares());
        
}
    
    /**
     * Prueba para eliminar una lista de deseos.
     *
     *
     */
    @Test
    public void deleteListaDeDeseosTest() {
        ListaDeDeseosEntity entity = data.get(0);
        listaPersistence.delete(entity.getId());
        ListaDeDeseosEntity deleted = em.find(ListaDeDeseosEntity.class, entity.getId());
        Assert.assertNull(deleted);
}
    
    @Test
    public void FindTabletByReferencia(){
        ListaDeDeseosEntity entity=data.get(0);
        ListaDeDeseosEntity newEntity = listaPersistence.findByIdentificador(entity.getIdentificador());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getIdentificador(), entity.getIdentificador());
    }
}
