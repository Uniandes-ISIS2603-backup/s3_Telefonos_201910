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
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */

@Path("compradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompradorResource {
 
private static final Logger LOGGER= Logger.getLogger(CompradorResource.class.getName());  
 
/**
 * Obtiene todos los compradores
 * @return Lista de compradores
 */
@GET
public List<CompradorDTO> obtenerCompradores (){
    List<CompradorDTO> result = new ArrayList();
    CompradorDTO c = new CompradorDTO();
    c.setApodo("Prueba");
    result.add(c);
    return result;
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

/**
 * Obtiene el comprador con identificador id
 * @param id Identificador del comprador
 * @return Comprador con identificador id
 */
@GET
@Path("{id: \\d+}")
 public CompradorDTO obtenerCompradorID(@PathParam("id") int id) {
     CompradorDTO c =new CompradorDTO();
     c.setId(id);
     return c;
 }
   
/**
 * Actualiza un comprador con identificador id
 * @param id Identificador del comprador
 * @param comprador Nueva informacion del comprador
 * @return Comprador actualizado
 */ 
@PUT
@Path("{id: \\d+}")
 public CompradorDTO  actualizarCompradorID(@PathParam("id") int id, CompradorDTO comprador){
     comprador.setId(id);
     return comprador;
 }
  
 /**
  * Elimina un comprador con identificador id
  * @param id Identificador del comprador
  * @return Comprador eliminado
  */
 @DELETE
 @Path("{id: \\d+}")
 public CompradorDTO  eliminarCompradorID(@PathParam("id") int id){
     CompradorDTO c =new CompradorDTO();
     c.setId(id);
     return c;
 }
 
}
