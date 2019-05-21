/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.CelularPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Babativa
 */
@Stateless
public class CelularLogic {
    
    @Inject
    private CelularPersistence persistence;
    
    /**
     * Crea un celular
     * @param celular el celular a ser creado
     * @return el celular en caso de ser creado
     * @throws Exception en caso de que el celular sea robado o 
     */
     public CelularEntity createCelular(CelularEntity celular)throws BusinessLogicException
    {
        /**
         * HACER VERIFICACIÖN CON IMEI
         */
        
        if(persistence.find(celular.getImei())!=null)
        {
            throw new BusinessLogicException("Ya existe un celular con el IMEI " + celular.getImei());
        }
        
        /**
         * HACER ROBADOS
        
        if((Math.random()%2)!=0)
        {
            throw new BusinessLogicException("El celular con el IMEI " + celular.getImei() + " está reportado como robado.");
        }
         */
        
        celular = persistence.create(celular);
        return celular;
    }
    
     /**
     * Borrar un celular
     */
    public void deleteCelular(Long imei) {
        persistence.delete(imei);
    }
    
    /**
     * Obtiene al lista de celulares
     * @return lista de celulares entity
     */
     public List<CelularEntity> getCelulares() {
       List<CelularEntity> celulares = persistence.findAll();
        return celulares;
    }
     
     /**
      * obtiene un celular registrado por su imei
      * @param imei del celular a obtener
      * @return el celular obtenido entity
      */
     public CelularEntity getCelularRegistrado (Long imei)
     {
         CelularEntity celular = persistence.find(imei);
         return celular;
     }
     
     
     /**
      * Obtiene un cleular no registrado por modelo
      * @param modelo del celular
      * @return el celular entity obtenido
      */
     public CelularEntity getCelularNoRegistrado (String modelo)
     {
         CelularEntity celular = persistence.findNoRegistrado(modelo);
         return celular;
     }
     
     /**
      * Actualiza un celular
      * @param celular contiene los datos del celular actualizado
      * @return el celular entity actualizado
      * @throws BusinessLogicException 
      */
     public CelularEntity updateCelular(CelularEntity celular) throws BusinessLogicException
     {
         
        if(persistence.findByImei(celular.getImei())!=null){
            throw new BusinessLogicException("Ya existe un Celular con el IMEI " + celular.getImei());
        }
       CelularEntity newEntity = persistence.update(celular);
        return newEntity;
     }
    
}
