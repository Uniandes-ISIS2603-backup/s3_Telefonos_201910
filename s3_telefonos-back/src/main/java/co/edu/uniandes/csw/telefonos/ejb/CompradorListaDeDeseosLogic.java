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
     * Borrar una lista de deseos de un comprador. 
     *
     * @param compradorId El comprador que se desea borrar de la lista de deseos.
     */
    public void removeListaDeDeseos(Long compradorId) {

        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);       
        ListaDeDeseosEntity listaEntity = listaPersistence.find(compradorEntity.getListaDeDeseos().getId());
        listaPersistence.delete(compradorEntity.getListaDeDeseos().getId());
        compradorEntity.setListaDeDeseos(null);
        listaEntity.setComprador(null);
        
}
    
}
