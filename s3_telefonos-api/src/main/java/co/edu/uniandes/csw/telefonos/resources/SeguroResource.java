/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.SeguroDTO;
import co.edu.uniandes.csw.telefonos.ejb.SeguroLogic;
import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
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

/**
 *
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */
@Path("seguros")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class SeguroResource {

    private static final Logger LOGGER = Logger.getLogger(SeguroResource.class.getName());

    @Inject
    private SeguroLogic seguroLogic;

    /**
     * Obtiene todos los seguros
     *
     * @return Lista de seguros
     */
    @GET
    public List<SeguroDTO> obtenerSeguros() {
        List<SeguroDTO> listaSeguros = listEntity2DTO(seguroLogic.getSeguros());
        return listaSeguros;
    }

    /**
     * Crea un nuevo seguro
     *
     * @param seguro Nuevo seguro que se va a crear
     * @return seguro creado
     */
    @POST
    public SeguroDTO crearSeguro(SeguroDTO seguro) throws BusinessLogicException {
        SeguroDTO nuevoSeguroDTO = new SeguroDTO(seguroLogic.createSeguro(seguro.toEntity()));
        return nuevoSeguroDTO;
    }

    /**
     * Obtiene el seguro con identificador id
     *
     * @param id Identificador del seguro
     * @return Seguro con identificador id
     */
    @GET
    @Path("{id: \\d+}")
    public SeguroDTO obtenerSeguroID(@PathParam("id") Long id) {
        SeguroEntity seguroEntity = seguroLogic.getSeguro(id);
        if (seguroEntity == null) {
            throw new WebApplicationException("El recurso /seguros/" + id + " no existe.", 404);
        }
        SeguroDTO seguroDTO = new SeguroDTO(seguroEntity);
        return seguroDTO;
    }

    /**
     * Actualiza un seguro con identificador id
     *
     * @param id Identificador del seguro
     * @param seguro Nueva informacion del seguro
     * @return Seguro actualizado
     */
    @PUT
    @Path("{id: \\d+}")
    public SeguroDTO actualizarSeguroID(@PathParam("id")Long id, SeguroDTO seguro) throws BusinessLogicException {

        seguro.setId(id);
        if (seguroLogic.getSeguro(id) == null) {
            throw new WebApplicationException("El recurso /seguros/" + id + " no existe.", 404);
        }
        SeguroDTO DTO = new SeguroDTO(seguroLogic.updateSeguro(id, seguro.toEntity()));
        return DTO;
    }

    /**
     * Elimina un seguro con identificador id
     *
     * @param id Identificador del seguro
     * @return Seguro eliminado
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarSeguroID(@PathParam("id") Long id) {
        SeguroEntity entity = seguroLogic.getSeguro(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /seguros/" + id + " no existe.", 404);
        }
        seguroLogic.deleteSeguro(id);
    }
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos SeguroEntity a una lista de
     * objetos SegurolDTO (json)
     *
     * @param entityList corresponde a la lista de seguros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de seguros en forma DTO (json)
     */
    private List<SeguroDTO> listEntity2DTO(List<SeguroEntity> entityList) {
        List<SeguroDTO> list = new ArrayList<>();
        for (SeguroEntity entity : entityList) {
            list.add(new SeguroDTO(entity));
        }
        return list;
    }

}
