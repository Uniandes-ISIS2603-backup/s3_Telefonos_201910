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
    
    public ListaDeDeseosEntity agregarTableta(ListaDeDeseosEntity listaEntity,TabletEntity tabletEntity )throws BusinessLogicException{
        ArrayList<TabletEntity> tablets = (ArrayList<TabletEntity>) listaEntity.getTablets();
        //ArrayList<CelularEntity> celulares = (ArrayList<CelularEntity>) listaEntity.getCelulares();
        if(tablets.size()/*+celulares.size()*/>=10){
            throw new BusinessLogicException("No se pudo registrar la tableta en la lista de deseos. Solo se pueden tener 10 dispositivos como maximo");
        }
        tablets.add(tabletEntity);
        listaEntity.setTablets(tablets);
        return listaEntity;
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
}
