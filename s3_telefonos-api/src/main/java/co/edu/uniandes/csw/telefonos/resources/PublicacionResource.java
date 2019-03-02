/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;


import co.edu.uniandes.csw.telefonos.dtos.PublicacionDTO;
import co.edu.uniandes.csw.telefonos.dtos.PublicacionDetailDTO;
import co.edu.uniandes.csw.telefonos.ejb.PublicacionLogic;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
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

@Path("publicaciones")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped

/**
 *
 * @author rj.gonzalez10
 */
public class PublicacionResource {
    private static final Logger LOGGER = Logger.getLogger(PublicacionResource.class.getName());
    @Inject
    private PublicacionLogic publicacionLogic;
    
    
    /**
 * Obtiene todas lsa publicaciones
 * @return Lista de publicaciones
 */
    @GET
    public List<PublicacionDetailDTO> obtenerPublicaciones() {
           LOGGER.info("PublicacionResource getPublicaciones: input: void");
        List<PublicacionDetailDTO> listaPublicaciones = listEntity2DetailDTO(publicacionLogic.getPublicaciones());
        LOGGER.log(Level.INFO, "PublicacionResource getPublicaciones: output: {0}", listaPublicaciones);
        return listaPublicaciones;
        
    }
    /**
 * Crea un nueva publicacion
 * @param publicacion Nueva publicacion que se va a crear
 * @return publicacion creado
 */
@POST
public PublicacionDTO crearComprador (PublicacionDTO publicacion)throws BusinessLogicException{
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
 
 
 /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos EditorialEntity a una lista de
     * objetos PublicacionDetailDTO (json)
     *
     * @param entityList corresponde a la lista de publicaciones de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de publicacion en forma DTO (json)
     */
    private List<PublicacionDetailDTO> listEntity2DetailDTO(List<PublicacionEntity> entityList) {
        List<PublicacionDetailDTO> list = new ArrayList<>();
        for (PublicacionEntity entity : entityList) {
            try{
            list.add(new PublicacionDetailDTO(entity));
            }
            catch(BusinessLogicException e){
            }    
            
        }
        return list;
    
    
}
}
