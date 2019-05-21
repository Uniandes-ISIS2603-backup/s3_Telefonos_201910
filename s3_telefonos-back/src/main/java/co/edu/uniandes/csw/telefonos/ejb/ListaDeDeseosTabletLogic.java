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
import java.util.List;
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
     * @param listaId El id de la lista de deseos en la cual se va a guardar la
     * tablet.
     * @return La lista de deseos con la tablet agregada.
     */
       public ListaDeDeseosEntity agregarTableta(String tabletReferencia, Long listaId)throws BusinessLogicException{
          ListaDeDeseosEntity listaEntity = listaPersistence.find(listaId);
          TabletEntity tabletEntity = tabletPersistence.findByReferencia(tabletReferencia);
        List<TabletEntity> tablets = (List<TabletEntity>) listaEntity.getTablets();
        List<CelularEntity> celulares = (List<CelularEntity>) listaEntity.getCelulares();
        if(tablets.size()+celulares.size()>=10){
            throw new BusinessLogicException("No se pudo registrar la tableta en la lista de deseos. Solo se pueden tener 10 dispositivos como maximo");
        }
        tablets.add(tabletEntity);
        listaEntity.setTablets(tablets);
        listaPersistence.update(listaEntity);
        return listaPersistence.find(listaId);
    
    }
       
       /**
     * Retorna todas las tablets asociadas a una lista de deseos
     *
     * @param listaId El ID de la lista buscada
     * @return La lista de tablets de la lista de deseos.
     */
    public List<TabletEntity> getTablets(Long listaId) {
        return listaPersistence.find(listaId).getTablets();
}
       
     /**
     * Borra una tableta de la lista de deseos
     *
     * @param tabletReferencia la referencia de la tableta a remover
     * @param listaId El id de la lista de deseos en la cual se va a eliminar la tablet
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
       
        /**
     * Retorna una tablet asociada a una lista de deseos
     *
     * @param listaId El id de la lista a buscar.
     * @param tabletRef El id de la tablet a buscar
     * @return La tablet encontrada dentro de la lista de deseos
     * @throws BusinessLogicException Si la tablet no se encuentra en la lista
     */
    public TabletEntity getTablet(Long listaId, String tabletRef) throws BusinessLogicException {
        List<TabletEntity> tablets = listaPersistence.find(listaId).getTablets();
        TabletEntity tabletEntity = tabletPersistence.findByReferencia(tabletRef);
        int index = tablets.indexOf(tabletEntity);

        if (index >= 0) {
            return tablets.get(index);
        }
        throw new BusinessLogicException("La tablet no está asociada a la lista de deseos.");
}
    
}
