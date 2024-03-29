/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDTO;
import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDetailDTO;
import co.edu.uniandes.csw.telefonos.ejb.CompradorListaDeDeseosLogic;
import co.edu.uniandes.csw.telefonos.ejb.CompradorLogic;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosLogic;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Produces("application/json")
@Consumes("application/json")
public class CompradorListaDeDeseosResource {
    
    private static final Logger LOGGER = Logger.getLogger(CompradorListaDeDeseosResource.class.getName());
    
    @Inject 
    private CompradorLogic compradorLogic;
    
    @Inject
    private CompradorListaDeDeseosLogic compradorListaLogic;
    
    @Inject 
    private ListaDeDeseosLogic listaDeDeseosLogic;
    
    /**
     * Relaciona una lista de deseos a un comprador
     *
     * @param compradorId Identificador del comprador con el que se quiere
     * relacionar la lista de deseos
     * @param listaId Identificador de la lista de deseos que se quiere asociar a un
     * comprador
     * @return Lista de deseos relacionada con el comprador
     */
    @POST
    @Path("{listaId: \\d+}")
    public ListaDeDeseosDetailDTO agregarLista(@PathParam("compradorId") Long compradorId, @PathParam("listaId") Long listaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una lista de deseos al comprador en el resource con id = {0}", compradorId);
        if (listaDeDeseosLogic.getListaDeDeseos(listaId) == null) {
            throw new WebApplicationException("El recurso /listasDeDeseos/" + listaId + " no existe.", 404);
        }
        ListaDeDeseosDetailDTO listaDTO = new ListaDeDeseosDetailDTO(compradorListaLogic.addLista(listaId,compradorId));
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una lista de deseos al comprador en el resource con id = {0}", compradorId);
        return listaDTO;
    }
    
    /**
     * Busca y devuelve la lista de deseos del comprador.
     *
     * @param compradorId Identificador del comprador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link CeluarDTO} - La lista de deseos del comprador buscado
     */
    @GET
    public ListaDeDeseosDetailDTO getListaDeDeseos(@PathParam("compradorId") Long compradorId) {
        ListaDeDeseosDetailDTO listaDTO = new ListaDeDeseosDetailDTO(compradorListaLogic.getListaDeDeseos(compradorId));
        return listaDTO;
    }
    
    /**
     * Devuelve el Resource que asocia lista de deseos con tablets
     * @param listaId Identificador de la lista
     */
    @Path("{listaId: \\d+}/tablets")
    public Class<ListaDeDeseosTabletResource> getListaDeDeseosTabletResource(@PathParam("listaId") Long listaId){
        if (listaDeDeseosLogic.getListaDeDeseos(listaId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + listaId + "/listasDeDeseos no existe.", 404);
        }
        return ListaDeDeseosTabletResource.class;
    }
    
    /**
     * Devuelve el Resource que asocia lista de deseos con celulares
     * @param listaId Identificador de la lista
     */
    @Path("{listaId: \\d+}/celulares")
    public Class<ListaDeDeseosCelularResource> getListaDeDeseosCelularResource(@PathParam("listaId") Long listaId){
        if (listaDeDeseosLogic.getListaDeDeseos(listaId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + listaId + "/listasDeDeseos no existe.", 404);
        }
        return ListaDeDeseosCelularResource.class;
    }
    
    
}
