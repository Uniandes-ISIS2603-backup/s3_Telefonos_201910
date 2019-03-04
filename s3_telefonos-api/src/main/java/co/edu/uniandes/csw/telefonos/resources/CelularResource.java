/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;
    

import co.edu.uniandes.csw.telefonos.dtos.CelularDTO;
import co.edu.uniandes.csw.telefonos.ejb.CelularLogic;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import javax.ws.rs.Produces;

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
}
