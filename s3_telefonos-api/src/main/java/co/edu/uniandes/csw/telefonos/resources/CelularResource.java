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
 * @author estudiante
 */
public class CelularResource {
    
        @Inject
        private CelularLogic logica;

	private final static Logger LOGGER = Logger.getLogger(CelularResource.class.getName());
	
	@POST
	public CelularDTO createCelular(CelularDTO celular) throws BusinessLogicException
	{
                
            CelularDTO r = new CelularDTO(logica.createCelular(celular.toEntity()));
            return r;    
        }
        
         @GET
    public List<CelularDTO> getCelulares(){
        List<CelularDTO> celulares = listEntityToDTO(logica.getCelulares());
        return celulares;
    }
    
    
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
    
    
    @GET
    @Path("{modelo: \\d+}")
    public CelularDTO getCelularNoRegistrado(@PathParam("modelo") String modelo){
      CelularEntity entity = logica.getCelularNoRegistrado(modelo);
      if(entity == null){
          throw new WebApplicationException("El recurso /celulares/"+modelo+" no existe.", 404);
      }
      CelularDTO dTO = new CelularDTO(entity);
        return dTO;
    }
    
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
    

    @DELETE
    @Path("{imei: \\d+}")
    public void deleteTablet(@PathParam("imei") Long imei, CelularDTO tablet){
      CelularEntity entity = logica.getCelularRegistrado(imei);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tablets/" + imei + " no existe.", 404);
        }
        logica.deleteCelular(imei);
  
    }
    
    private List<CelularDTO> listEntityToDTO(List<CelularEntity> listaEntidades){
        List<CelularDTO> lista = new ArrayList<>();
        for(CelularEntity entity: listaEntidades){
            lista.add(new CelularDTO(entity));
        }
        return lista;
    }
}
