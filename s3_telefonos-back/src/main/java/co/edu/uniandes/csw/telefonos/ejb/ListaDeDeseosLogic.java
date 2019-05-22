/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.ListaDeDeseosPersistence;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;


/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Stateless
public class ListaDeDeseosLogic {
    
    @Inject
    private ListaDeDeseosPersistence persistence;
    
    private static final Logger LOGGER = Logger.getLogger(ListaDeDeseosLogic.class.getName());
    
    public ListaDeDeseosEntity createListaDeDeseos(ListaDeDeseosEntity lista)throws BusinessLogicException{
        
        if(persistence.findByIdentificador(lista.getIdentificador())!=null){
            throw new BusinessLogicException("Ya existe una lista de deseos con el identificador \""+lista.getIdentificador());
        }
        lista = persistence.create(lista);
        return lista;
    }
    
    
    
    /*
    public ListaDeDeseosEntity agregarCelular(ListaDeDeseosEntity listaEntity,CelularEntity celularEntity )throws Exception{
        ArrayList<TabletEntity> tablets = (ArrayList<TabletEntity>) listaEntity.getTablets();
        ArrayList<CelularEntity> celulares = (ArrayList<CelularEntity>) listaEntity.getCelulares();
        if(tablets.size()+celulares.size()>=10){
           throw new BusinessLogicException("No se pudo registrar el celular en la lista de deseos. Solo se pueden tener 10 dispositivos como maximo");
       }
       celulares.add(celularEntity);
        listaEntity.setCelulares(tablets);
    return listaEntity;
    }
*/
    
     public List<ListaDeDeseosEntity> getListasDeDeseos(){
         LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las listas de deseos");
        List<ListaDeDeseosEntity> listas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las listas de deseos");
        return listas;
    }
    
    public ListaDeDeseosEntity getListaDeDeseos(Long identificador){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la lista de deseos con identificador = {0}", identificador);
        ListaDeDeseosEntity lista = persistence.find(identificador);
        if(lista == null){
            LOGGER.log(Level.SEVERE, "La lista de deseos con el identificador = {0} no existe", identificador);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la lista de deseos con identificador = {0}", identificador);
        return lista;
    }
}
