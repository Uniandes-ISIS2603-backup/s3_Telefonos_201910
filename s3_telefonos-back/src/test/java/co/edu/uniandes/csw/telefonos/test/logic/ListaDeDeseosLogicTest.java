/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;


import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosCelularLogic;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosLogic;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosTabletLogic;
import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.ListaDeDeseosPersistence;
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
 * @author Andres Felipe Daza Diaz
 */
@RunWith(Arquillian.class)
public class ListaDeDeseosLogicTest {
    
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
    private ListaDeDeseosLogic listaLogic;
    

    
    private List<ListaDeDeseosEntity> data = new ArrayList<ListaDeDeseosEntity>();
    
 
    
     @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ListaDeDeseosEntity.class.getPackage())
                .addPackage(ListaDeDeseosPersistence.class.getPackage())
                .addPackage(ListaDeDeseosLogic.class.getPackage())
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
        em.createQuery("delete from ListaDeDeseosEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
             for (int i = 0; i < 3; i++) {
            ListaDeDeseosEntity entity = factory.manufacturePojo(ListaDeDeseosEntity.class);
            em.persist(entity);
            data.add(entity);
            }
    }


    @Test
    public void createListaDeDeseosTest() throws BusinessLogicException{
        ListaDeDeseosEntity newEntity = factory.manufacturePojo(ListaDeDeseosEntity.class);
        ListaDeDeseosEntity resultado = listaLogic.createListaDeDeseos(newEntity);
        Assert.assertNotNull(resultado);
        ListaDeDeseosEntity entity = em.find(ListaDeDeseosEntity.class, resultado.getId());
        Assert.assertEquals(newEntity.getIdentificador(), entity.getIdentificador());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
    @Test(expected = BusinessLogicException.class)
    public void createListaDeDeseosConMismoIdentificadorTest() throws BusinessLogicException{
        ListaDeDeseosEntity newEntity = factory.manufacturePojo(ListaDeDeseosEntity.class);
        newEntity.setIdentificador(data.get(0).getIdentificador());
        listaLogic.createListaDeDeseos(newEntity);
    }
}
