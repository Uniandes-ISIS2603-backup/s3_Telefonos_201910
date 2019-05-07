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
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.ListaDeDeseosPersistence;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import java.util.ArrayList;
import java.util.List;
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
    
    
   /**
     * Agregar una lista de deseos al comprador
     *
     * @param compradorId El id del comprador en el cual se va a guardar la
     * lista de deseos
     * @param listaId El id de la lista de deseos que se va a guardar
     * @return La lista de deseos creada.
     */
    public ListaDeDeseosEntity addLista(Long listaId, Long compradorId)throws BusinessLogicException {
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        ListaDeDeseosEntity listaEntity = listaPersistence.find(listaId);
        if(compradorEntity.getListaDeDeseos()==null){
            throw new BusinessLogicException("El comprador con id "+compradorId+" ya tiene una lista de deseos asociada.");
        }
        compradorEntity.setListaDeDeseos(listaEntity);
        return listaPersistence.find(listaId);
    }
    
            /**
     * Retorna la lista de deseos asociada a un comprador
     *
     * @param compradorId El ID del comprador buscado
     * @return La lista de deseos asociada al comprador buscado.
     */
    public ListaDeDeseosEntity getListaDeDeseos(Long compradorId) {
        return compradorPersistence.find(compradorId).getListaDeDeseos();
    }
    
}
