/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.CompradorDTO;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("compradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompradorResource {
 
private static final Logger LOGGER= Logger.getLogger(CompradorResource.class.getName());  
 

public List<CompradorDTO> obtenerCompradores (){
    return new ArrayList<>();
}


/**
 * Crea un nuevo comprador
 * @param comprador Nuevo comprador que se va a crear
 * @return comprador creado
 */
@POST
public CompradorDTO crearComprador (CompradorDTO comprador){
    return comprador;
}


    
    
    
}
