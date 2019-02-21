/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;


import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosLogic;
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
     * pruebas. En el indice 0 se guardara una lista de deseos con 9 tablets, en el indice
     * 2 se guardara una con 9 celulares, en el indice 3 se guardara una con 5 tablets y 5 celulares
     */
    private void insertData() {
        
            ListaDeDeseosEntity entity = factory.manufacturePojo(ListaDeDeseosEntity.class);
            ArrayList<TabletEntity> tablets = (ArrayList<TabletEntity>) entity.getTablets();
            //ArrayList<CelularEntity> celulares = (ArrayList<CelularEntity>) entity.getCelulares();
            //celulares.clear();
            //entity.setCelulares(celulares);
            tablets.clear();
            for(int i=0;i<9;i++){
                TabletEntity tablet = factory.manufacturePojo(TabletEntity.class);
                tablets.add(tablet);
            }
            entity.setTablets(tablets);
            em.persist(entity);
            data.add(entity);
            
            entity = factory.manufacturePojo(ListaDeDeseosEntity.class);
            tablets = (ArrayList<TabletEntity>) entity.getTablets();
            ///elulares = (ArrayList<CelularEntity>) entity.getCelulares();
            ///celulares.clear();
            tablets.clear();
            for(int i=0;i<5;i++){
                //CelularEntity celular = factory.manufacturePojo(CelularEntity.class);
                //celulares.add(celular);
            }
            for(int i=0;i<10;i++){
                TabletEntity tablet = factory.manufacturePojo(TabletEntity.class);
                tablets.add(tablet);
            }
            //entity.setCelulares(celulares);
            entity.setTablets(tablets);
            em.persist(entity);
            data.add(entity);
        /*
            entity = factory.manufacturePojo(ListaDeDeseosEntity.class);
            tablets = (ArrayList<TabletEntity>) entity.getTablets();
            celulares = (ArrayList<CelularEntity>) entity.getCelulares();
            celulares.clear();
            tablets.clear();
            entity.setTablets(tablets);
            for(int i=0;i<9;i++){
                CelularEntity celular = factory.manufacturePojo(CelularEntity.class);
                celulares.add(celular);
            }
            entity.setCelulares(celulares);
            em.persist(entity);
            data.add(entity);
*/
    }
    
    @Test
    public void agregarTabletListaTest()throws BusinessLogicException{
        TabletEntity nuevaTablet = factory.manufacturePojo(TabletEntity.class);
        ListaDeDeseosEntity lista = data.get(0);
        lista = listaLogic.agregarTableta(lista, nuevaTablet);
        Assert.assertEquals(10, lista.getTablets().size()/*+lista.getCelulares().size()*/);
      
    }
    
    /*
    @Test
    public void agregarCelularListaTest()throws BusinessLogicException{
        CelularEntity nuevoCelular = factory.manufacturePojo(CelularEntity.class);
        ListaDeDeseosEntity lista = data.get(2);
        lista = listaLogic.agregarTableta(lista, nuevoCelular);
        Assert.assertEquals(10, lista.getTablets().size()+lista.getCelulares().size());
    }
*/
    
    @Test(expected=BusinessLogicException.class)
    public void ExcederCapacidadConTabletaTest()throws BusinessLogicException{
        ListaDeDeseosEntity lista = data.get(1);
        TabletEntity tablet = factory.manufacturePojo(TabletEntity.class);
        lista = listaLogic.agregarTableta(lista, tablet);
    }
    /*
    @Test(expected=BusinessLogicException.class)
    public void ExcederCapacidadConCelularTest()throws BusinessLogicException{
        ListaDeDeseosEntity lista = data.get(2);
        CelularEntity celular = factory.manufacturePojo(CelularEntity.class);
        lista = listaLogic.agregarCelular(lista, celular);
    }
*/
}
