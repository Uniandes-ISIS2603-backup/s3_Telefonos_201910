/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.ListaDeDeseosPersistence;
import co.edu.uniandes.csw.telefonos.persistence.TabletPersistence;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Stateless
public class ListaDeDeseosTabletLogic {
    
    @Inject 
    private TabletPersistence tabletPersistence;
    
    @Inject 
    private ListaDeDeseosPersistence listaPersistence;
    
    /**
     * Agregar una tablet a la lista de deseos
     *
     * @param tabletReferencia la referencia de la tablet a guardar
     * @param ListaId El id de la lista de deseos en la cual se va a guardar la
     * tablet.
     * @return La lista de deseos con la tablet agregada.
     */
       public ListaDeDeseosEntity agregarTableta(String tabletReferencia, Long listaId)throws BusinessLogicException{
          ListaDeDeseosEntity listaEntity = listaPersistence.find(listaId);
          TabletEntity tabletEntity = tabletPersistence.findByReferencia(tabletReferencia);
        ArrayList<TabletEntity> tablets = (ArrayList<TabletEntity>) listaEntity.getTablets();
        ArrayList<CelularEntity> celulares = (ArrayList<CelularEntity>) listaEntity.getCelulares();
        if(tablets.size()+celulares.size()>=10){
            throw new BusinessLogicException("No se pudo registrar la tableta en la lista de deseos. Solo se pueden tener 10 dispositivos como maximo");
        }
        tablets.add(tabletEntity);
        listaEntity.setTablets(tablets);
        return listaEntity;
    
    }
       
     /**
     * Borra una tableta de la lista de deseos
     *
     * @param tabletReferencia la referencia de la tableta a remover
     * @param ListaId El id de la lista de deseos en la cual se va a eliminar la tablet
     * @return la lista de deseos con la tablet removida.
     */
       public ListaDeDeseosEntity removerTableta(String tabletReferencia, Long listaId) throws BusinessLogicException{
          ListaDeDeseosEntity listaEntity = listaPersistence.find(listaId);
          TabletEntity tabletEntity = tabletPersistence.findByReferencia(tabletReferencia);
        ArrayList<TabletEntity> tablets = (ArrayList<TabletEntity>) listaEntity.getTablets();
         boolean encontro = false;
        for(TabletEntity tablet: tablets){
            if(tablet.getReferencia().equals(tabletReferencia)){
                encontro = true;
                tablets.remove(tablet);
            }
        }
        if(!encontro){
            throw new BusinessLogicException("La tablet que desea eliminar no se encuentra en la lista de deseos con id: "+listaId);
        }
        listaEntity.setTablets(tablets);
        return listaEntity;
    
    }
}
