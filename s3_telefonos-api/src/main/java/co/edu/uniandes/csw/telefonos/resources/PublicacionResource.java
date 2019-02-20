/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;


import co.edu.uniandes.csw.telefonos.dtos.PublicacionDTO;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
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

@Path("publicacion")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped

/**
 *
 * @author rj.gonzalez10
 */
public class PublicacionResource {
    private static final Logger LOGGER = Logger.getLogger(PublicacionResource.class.getName());
    
    
    /**
 * Obtiene todas lsa publicaciones
 * @return Lista de publicaciones
 */
    @GET
    public List<PublicacionDTO> obtenerPublicaciones() {
        List<PublicacionDTO> result = new ArrayList();
        PublicacionDTO x = new PublicacionDTO();
        x.setId(Long.MIN_VALUE);
        result.add(x);
        return result;
        
    }
    /**
 * Crea un nueva publicacion
 * @param publicacion Nueva publicacion que se va a crear
 * @return publicacion creado
 */
@POST
public PublicacionDTO crearComprador (PublicacionDTO publicacion){
    return publicacion;
}

/**
 * Obtiene la publicion con identificador id
 * @param id Identificador de la publicion
 * @return publicacion con identificador id
 */
@GET
@Path("{publicacionId: \\d+}")
 public PublicacionDTO obtenerPublicacionID(@PathParam("publicacionId") Long id) {
     PublicacionDTO x =new PublicacionDTO();
     x.setId(id);
     return x;
 }
   
/**
 * Actualiza un publicacion con identificador id
 * @param id Identificador del publicion
 * @param publicacion Nueva informacion de la publicacion
 * @return publicacion actualizado
 */ 
@PUT
@Path("{publicacionId: \\d+}")
 public PublicacionDTO  actualizarPublicacionID(@PathParam("publicacionId") Long id, PublicacionDTO publicacion){
     publicacion.setId(id);
     return publicacion;
 }
  
 /**
  * Elimina un publicacion con identificador id
  * @param id Identificador de la publicacion
  * @return Publicacion eliminada
  */
 @DELETE
 @Path("{publicacionId: \\d+}")
 public PublicacionDTO  eliminarPublicacionID(@PathParam("publicacionId") long id){
     PublicacionDTO x =new PublicacionDTO();
     x.setId(id);
     return x;
 }
 
 
    
    
}
