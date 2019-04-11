/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;
    

import co.edu.uniandes.csw.telefonos.dtos.CelularDTO;
import co.edu.uniandes.csw.telefonos.ejb.CelularLogic;
import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

@Path("celulares")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped
/**
 *
 * @author Daniel Babativa
 */
public class CelularResource {
    
    /**
     * Conexion con la lógica
     */
        @Inject
        private CelularLogic logica;

        /**
         * Logger
         */
	private final static Logger LOGGER = Logger.getLogger(CelularResource.class.getName());
	
        /**
         * Metodo que crea un celular
         * @param celular, el celular a crear
         * @return el celular creado
         * @throws BusinessLogicException 
         */
	@POST
	public CelularDTO createCelular(CelularDTO celular) throws BusinessLogicException
	{
                
            CelularDTO r = new CelularDTO(logica.createCelular(celular.toEntity()));
            return r;    
        }
        /**
         * Retorna todos los celulares de la app
         * @return lista de todos los celularDTO
         */
         @GET
    public List<CelularDTO> getCelulares(){
        List<CelularDTO> celulares = listEntityToDTO(logica.getCelulares());
        return celulares;
    }
    
    /**
     * Retorna un celular identificado con el imei ingresado por parametro
     * @param imei el imei del celular a encontrar
     * @return el celular con el imei ingresado por parametro
     */
    @GET
    @Path("{imei: \\d+}")
    public CelularDTO getCelularRegistrado(@PathParam("imei") Long imei){
      CelularEntity entity = logica.getCelularRegistrado(imei);
      if(entity == null){
          throw new WebApplicationException("El recurso /celulares/"+imei+" no existe.", 404);
      }
      CelularDTO dTO = new CelularDTO(entity);
        return dTO;
    }
    
    /**
     * Gettea un celular no registrado por su modelo
     * @param modelo, modelo del celular a gettear
     * @return el celular getteado
     */
    @GET
    @Path("{modelo: \\c+}")
    public CelularDTO getCelularNoRegistrado(@PathParam("modelo") String modelo){
      CelularEntity entity = logica.getCelularNoRegistrado(modelo);
      if(entity == null){
          throw new WebApplicationException("El recurso /celulares/"+modelo+" no existe.", 404);
      }
      CelularDTO dTO = new CelularDTO(entity);
        return dTO;
    }
    
    /**
     * Actualiza el celular con el imei ingresado por parametro con los valores del celular ingresado por parametro
     * @param imei, del celular a actualizar
     * @param cel, el celular con la información para actualizar
     * @return el nuevo celular
     * @throws BusinessLogicException 
     */
    @PUT
    @Path("{imei: \\d+}")
    public CelularDTO updateCelular(@PathParam("imei") Long imei, CelularDTO cel) throws BusinessLogicException{
      cel.setImei(imei);
        if (logica.getCelularRegistrado(imei) == null) {
            throw new WebApplicationException("El recurso /celulares/" + imei + " no existe.", 404);
        }
        CelularDTO dO = new CelularDTO(logica.updateCelular(cel.toEntity()));
        return dO;
    }
    
    /**
     * Metodo que borra un celular por imei
     * @param imei, imei del celular a borrar
     */
    @DELETE
    @Path("{imei: \\d+}")
    public void deleteCelular(@PathParam("imei") Long imei, CelularDTO celular){
      CelularEntity entity = logica.getCelularRegistrado(imei);
        if (entity == null) {
            throw new WebApplicationException("El recurso /celulares/" + imei + " no existe.", 404);
        }
        logica.deleteCelular(imei);
  
    }
    
    /**
     * Convierte una lista de CelularEntity a  una lista de CelularDTO
     * @param listaEntidades la lista de celularEntity
     * @return la lista de CelularDTO
     */
    private List<CelularDTO> listEntityToDTO(List<CelularEntity> listaEntidades){
        List<CelularDTO> lista = new ArrayList<>();
        for(CelularEntity entity: listaEntidades){
            lista.add(new CelularDTO(entity));
        }
        return lista;
    }
}
