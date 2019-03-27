/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDTO;
import co.edu.uniandes.csw.telefonos.dtos.TabletDTO;
import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDetailDTO;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosLogic;
import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.List;
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

/**
 *
 * @author Andres Felipe Daza Diaz
 */

@Path("listasDeDeseos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ListaDeDeseosResource {
    
    private static final Logger LOGGER = Logger.getLogger(ListaDeDeseosResource.class.getName());
    
    @Inject
    private ListaDeDeseosLogic listaLogic;
    
    @POST
    public ListaDeDeseosDTO createListaDeDeseos(ListaDeDeseosDTO listaDeDeseos)throws BusinessLogicException{
        ListaDeDeseosEntity listaEntity = listaDeDeseos.toEntity();
        ListaDeDeseosEntity nuevaListaEntity = listaLogic.createListaDeDeseos(listaEntity);
        ListaDeDeseosDTO nuevaListaDTO = new ListaDeDeseosDTO(nuevaListaEntity);
        return nuevaListaDTO;
    }
    

    /**
     * Busca y devuelve todas las listas de deseos que existen en la aplicacion.
     *
     * @return JSONArray {@link ListaDeDeseosDetailDTO} - Las listas de deseos
     * encontradas en la aplicación. Si no hay ninguna retorna una lista vacía.
     */
    @GET
    public List<ListaDeDeseosDetailDTO> getListasDeDeseos (){
        List<ListaDeDeseosDetailDTO> listaListas = listEntity2DetailDTO(listaLogic.getListasDeDeseos());
        return listaListas;
    }
    
     /**
     * Busca la lista de deseos con el id asociado recibido en la URL y la devuelve.
     *
     * @param listaId Identificador de la lista de deseos que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link ListaDeDeseosDetailDTO} - La lista de deseos buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la lista de deseos.
     */
    @GET
    @Path("{listaId: \\d+}")
    public ListaDeDeseosDetailDTO getListaDeDeseos(@PathParam("listaId") Long listaId) throws WebApplicationException {
       
        ListaDeDeseosEntity listaEntity = listaLogic.getListaDeDeseos(listaId);
        if (listaEntity == null) {
            throw new WebApplicationException("El recurso /listasDeDeseos/" + listaId + " no existe.", 404);
        }
        ListaDeDeseosDetailDTO detailDTO = new ListaDeDeseosDetailDTO(listaEntity);
        return detailDTO;
}
   
    /**
     * Conexión con el servicio de tablets para una lista de deseos.
     * {@link ListaDeDeseosTabletResource}
     *
     * Este método conecta la ruta de /listasDeDeseos con las rutas de /tablets que
     * dependen de la lista de deseos, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de las tablets de una lista de deseos.
     *
     * @param listaId El ID de la lista de deseos con respecto a la cual se
     * accede al servicio.
     * @return El servicio de tablets para esta lista de deseos en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la lista de deseos.
     */
    @Path("{listaId: \\d+}/tablets")
    public Class<ListaDeDeseosTabletResource> getListaDeDeseosTabletResource(@PathParam("listaId") Long listaId) {
        if (listaLogic.getListaDeDeseos(listaId) == null) {
            throw new WebApplicationException("El recurso /listasDeDeseos/" + listaId + " no existe.", 404);
        }
        return ListaDeDeseosTabletResource.class;
}
    
    /**
     * Conexión con el servicio de celulares para una lista de deseos.
     * {@link ListaDeDeseosTabletResource}
     *
     * Este método conecta la ruta de /listasDeDeseos con las rutas de /celulares que
     * dependen de la lista de deseos, es una redirección al servicio que maneja el
     * segmento de la URL que se encarga de los celulares de una lista de deseos.
     *
     * @param listaId El ID de la lista de deseos con respecto a la cual se
     * accede al servicio.
     * @return El servicio de celulares para esta lista de deseos en paricular.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la lista de deseos.
     */
    @Path("{listaId: \\d+}/celulares")
    public Class<ListaDeDeseosCelularResource> getListaDeDeseoCelularResource(@PathParam("listaId") Long listaId) {
        if (listaLogic.getListaDeDeseos(listaId) == null) {
            throw new WebApplicationException("El recurso /listasDeDeseos/" + listaId + " no existe.", 404);
        }
        return ListaDeDeseosCelularResource.class;
}
    
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ListaDeDeseosEntity a una lista de
     * objetos ListaDeDeseosDetailDTO (json)
     *
     * @param entityList corresponde a la lista de listas de deseos de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de listas de deseos en forma DTO (json)
     */
    private List<ListaDeDeseosDetailDTO> listEntity2DetailDTO(List<ListaDeDeseosEntity> entityList) {
        List<ListaDeDeseosDetailDTO> list = new ArrayList<>();
        for (ListaDeDeseosEntity entity : entityList) {
            list.add(new ListaDeDeseosDetailDTO(entity));
        }
        return list;
}
}
