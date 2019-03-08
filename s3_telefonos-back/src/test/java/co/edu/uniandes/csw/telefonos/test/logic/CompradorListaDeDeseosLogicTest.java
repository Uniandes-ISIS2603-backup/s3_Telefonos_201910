/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.test.logic;

import co.edu.uniandes.csw.telefonos.ejb.CompradorListaDeDeseosLogic;
import co.edu.uniandes.csw.telefonos.ejb.CompradorLogic;
import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.persistence.ListaDeDeseosPersistence;
import java.util.ArrayList;
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
public class CompradorListaDeDeseosLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    @Inject 
    private CompradorListaDeDeseosLogic compradorListaLogic;
    
    private CompradorEntity comprador;
    
    private ListaDeDeseosEntity lista;
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ListaDeDeseosEntity.class.getPackage())
                .addPackage(CompradorLogic.class.getPackage())
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
        em.createQuery("delete from CompradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        comprador = factory.manufacturePojo(CompradorEntity.class);
        lista = factory.manufacturePojo(ListaDeDeseosEntity.class);
        ArrayList<TabletEntity> tablets = new ArrayList<TabletEntity>();
        ArrayList<CelularEntity> celulares = new ArrayList<CelularEntity>();
        for(int i=0;i<3;i++){
            TabletEntity tablet = factory.manufacturePojo(TabletEntity.class);
            CelularEntity celular = factory.manufacturePojo(CelularEntity.class);
        }
        lista.setTablets(tablets);
        lista.setCelulares(celulares);
        comprador.setListaDeDeseos(lista);
        em.persist(comprador);
    }
    
    
    @Test
    public void vaciarListaDeDeseosTest(){
        compradorListaLogic.vaciarListaDeDeseos(comprador.getId());
        ListaDeDeseosEntity listaComprador = comprador.getListaDeDeseos();
        int elementos = listaComprador.getTablets().size()/*+listaComprador.getCelulares.size()*/;
        Assert.assertEquals(0, elementos);
    }
    
}
