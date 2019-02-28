/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.persistence.ListaDeDeseosPersistence;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Stateless
public class CompradorListaDeDeseosLogic {
    
    @Inject 
    private ListaDeDeseosPersistence listaPersistence;
    
    @Inject
    private CompradorPersistence compradorPersistence;
    
    
    /*
    * Vacia la lsta de deseos de un comprador
    *
    *@param idComprador El id del comprador a actualizar
    */
    public void vaciarListaDeDeseos(Long idComprador){
        CompradorEntity comprador = compradorPersistence.find(idComprador);
        ListaDeDeseosEntity lista = comprador.getListaDeDeseos();
        ArrayList<TabletEntity> tablets = (ArrayList<TabletEntity>) lista.getTablets();
        //ArrayList<CelularEntity> celulares = lista.getCelulares();
        tablets.clear();
        //celulares.clear();
        lista.setTablets(tablets);
        //lista.setCelulares(celulares);
        comprador.setListaDeDeseos(lista);
    }
    
}
