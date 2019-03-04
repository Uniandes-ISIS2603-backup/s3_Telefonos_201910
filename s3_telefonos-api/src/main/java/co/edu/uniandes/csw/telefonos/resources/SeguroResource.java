/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.SeguroDTO;
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

/**
 *
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */

@Path("seguros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SeguroResource {
    private static final Logger LOGGER= Logger.getLogger(SeguroResource.class.getName());
    
    //@Inject 
    //private SeguroLogic seguroLogic;
    
 /**
 * Obtiene todos los seguros
 * @return Lista de seguros
 */
@GET
public List<SeguroDTO> obtenerSeguros (){
    List<SeguroDTO> result = new ArrayList();
    SeguroDTO f = new SeguroDTO();
    f.setAseguradora("A prueba");
    result.add(f);
    return result;
}


/**
 * Crea un nuevo seguro
 * @param seguro Nuevo seguro que se va a crear
 * @return seguro creado
 */
@POST
public SeguroDTO crearSeguro (SeguroDTO seguro){
    //SeguroDTO seguroDTO = new SeguroDTO(seguroLogic)
    return null;
}

/**
 * Obtiene el seguro con identificador id
 * @param id Identificador del seguro
 * @return Seguro con identificador id
 */
@GET
@Path("{id: \\d+}")
 public SeguroDTO obtenerSeguroID(@PathParam("id") int id) {
     SeguroDTO f =new SeguroDTO();
     return f;
 }
   
/**
 * Actualiza un seguro con identificador id
 * @param id Identificador del seguro
 * @param seguro Nueva informacion del seguro
 * @return Seguro actualizado
 */ 
@PUT
@Path("{id: \\d+}")
 public SeguroDTO  actualizarSeguroID(@PathParam("id") int id, SeguroDTO seguro){
     
     return seguro;
 }
  
 /**
  * Elimina un seguro con identificador id
  * @param id Identificador del seguro
  * @return Seguro eliminado
  */
 @DELETE
 @Path("{id: \\d+}")
 public SeguroDTO  eliminarSeguroID(@PathParam("id") int id){
     SeguroDTO f =new SeguroDTO();
     return f;
 }
    
}
