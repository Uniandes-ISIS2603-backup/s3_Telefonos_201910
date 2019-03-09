/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.CelularPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

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
         */
        if((Math.random()%2)!=0)
        {
            throw new BusinessLogicException("El celular con el IMEI " + celular.getImei() + " está reportado como robado.");
        }
        
        celular = persistence.create(celular);
        return celular;
    }
    
     /**
     * Borrar un comprador
     */
    public void deleteCelular(Long imei) {
        persistence.delete(imei);
    }
    
    
     public List<CelularEntity> getCelulares() {
       List<CelularEntity> celulares = persistence.findAll();
        return celulares;
    }
     
     
     public CelularEntity getCelularRegistrado (Long imei)
     {
         CelularEntity celular = persistence.find(imei);
         return celular;
     }
     
     public CelularEntity getCelularNoRegistrado (String modelo)
     {
         CelularEntity celular = persistence.findNoRegistrado(modelo);
         return celular;
     }
     
     public CelularEntity updateCelular(CelularEntity celular) throws BusinessLogicException
     {
         //La referencia de cada factura debe ser unica
        if(persistence.findByImei(celular.getImei())!=null){
            throw new BusinessLogicException("Ya existe un Celular con el IMEI " + celular.getImei());
        }
        
       CelularEntity newEntity = persistence.update(celular);
        return newEntity;
     }
    
}
