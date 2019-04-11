/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.CompradorDTO;
import co.edu.uniandes.csw.telefonos.dtos.CompradorDetailDTO;
import co.edu.uniandes.csw.telefonos.ejb.CompradorLogic;
import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Path("compradores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class CompradorResource {

    private static final Logger LOGGER = Logger.getLogger(CompradorResource.class.getName());

    @Inject
    private CompradorLogic compradorLogic;

    /**
     * Obtiene todos los compradores
     *
     * @return Lista de compradores
     */
    @GET
    public List<CompradorDetailDTO> obtenerCompradores() {
        List<CompradorDetailDTO> listaCompradores = listEntity2DetailDTO(compradorLogic.getCompradores());
        return listaCompradores;
    }

    /**
     * Crea un nuevo comprador
     *
     * @param comprador Nuevo comprador que se va a crear
     * @return comprador creado
     */
    @POST
    public CompradorDTO crearComprador(CompradorDTO comprador) throws BusinessLogicException {
        CompradorDTO compradorDTO = new CompradorDTO(compradorLogic.createComprador(comprador.toEntity()));
        return compradorDTO;
    }

    /**
     * Obtiene el comprador con identificador id
     *
     * @param id Identificador del comprador
     * @return Comprador con identificador id
     */
    @GET
    @Path("{compradorId: \\d+}")
    public CompradorDetailDTO obtenerCompradorID(@PathParam("compradorId") Long compradorId) {
        CompradorEntity compradorEntity = compradorLogic.getComprador(compradorId);
        if (compradorEntity == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + " no existe.", 404);
        }
        CompradorDetailDTO compradorDetailDTO = new CompradorDetailDTO(compradorEntity);
        return compradorDetailDTO;
    }

    /**
     * Actualiza un comprador con identificador id
     *
     * @param id Identificador del comprador
     * @param comprador Nueva informacion del comprador
     * @return Comprador actualizado
     */
    @PUT
    @Path("{compradorId: \\d+}")
    public CompradorDetailDTO actualizarCompradorID(@PathParam("compradorId") Long compradorId, CompradorDetailDTO comprador) throws BusinessLogicException {
        comprador.setId(compradorId);
        if (compradorLogic.getComprador(compradorId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + " no existe.", 404);
        }
        CompradorDetailDTO detailDTO = new CompradorDetailDTO(compradorLogic.updateComprador(compradorId, comprador.toEntity()));
        return detailDTO;
    }

    /**
     * Elimina un comprador con identificador id
     *
     * @param id Identificador del comprador
     * @return Comprador eliminado
     */
    @DELETE
    @Path("{compradorId: \\d+}")
    public void eliminarCompradorID(@PathParam("compradorId") Long compradorId) {
        CompradorEntity entity = compradorLogic.getComprador(compradorId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + " no existe.", 404);
        }
        compradorLogic.deleteComprador(compradorId);
    }

    /**
     * Devuelve el Resource que asocia compradores con facturas
     * @param compradorId Identificador del comprador
     * @return Clase de asociacion
     */
    @Path("{compradorId: \\d+}/facturas")
    public Class<CompradorFacturasResource> getCompradorFacturaResource(@PathParam("compradorId") Long compradorId) {
        LOGGER.log(Level.INFO, "Entra al metodo correcto");
        if (compradorLogic.getComprador(compradorId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + " no existe.", 404);
        }
        return CompradorFacturasResource.class;
    }
    
        /**
     * Devuelve el Resource que asocia compradores con metodos de pago
     * @param compradorId Identificador del comprador
     * @return Clase de asociacion
     */
    @Path("{compradorId: \\d+}/metodosDePago")
    public Class<CompradorMetodosDePagoResource> getCompradorMetodoDePagoResource(@PathParam("compradorId") Long compradorId) {
        if (compradorLogic.getComprador(compradorId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + " no existe.", 404);
        }
        return CompradorMetodosDePagoResource.class;
    }

    /**
     * Devuelve el Resource que asocia compradores con lista de deseos
     * @param compradorId Identificador del comprador
     * @return Clase de asociacion
     */
    @Path("{compradorId: \\d+}/listasDeDeseos")
    public Class<CompradorListaDeDeseosResource> getCompradorListaDeDeseosResource(@PathParam("compradorId") Long compradorId) {
        if (compradorLogic.getComprador(compradorId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + " no existe.", 404);
        }
        return CompradorListaDeDeseosResource.class;
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos CompradorEntity a una lista de
     * objetos CompradorDetailDTO (json)
     *
     * @param entityList corresponde a la lista de compradores de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de compradores en forma DTO (json)
     */
    private List<CompradorDetailDTO> listEntity2DetailDTO(List<CompradorEntity> entityList) {
        List<CompradorDetailDTO> list = new ArrayList<>();
        for (CompradorEntity entity : entityList) {
            list.add(new CompradorDetailDTO(entity));
        }
        return list;
    }

}
