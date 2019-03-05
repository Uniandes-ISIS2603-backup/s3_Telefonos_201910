/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.FacturaDTO;
import co.edu.uniandes.csw.telefonos.ejb.FacturaLogic;
import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
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
 * @author Laura Valentina Prieto Jimenez
 */
@Path("facturas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaResource {

    private static final Logger LOGGER = Logger.getLogger(FacturaResource.class.getName());

    @Inject
    private FacturaLogic facturaLogic;

    /**
     * Obtiene todas las facturas
     *
     * @return Lista de facturas
     */
    @GET
    public List<FacturaDTO> obtenerFacturas() {
        List<FacturaDTO> listaFacturas = listEntity2DTO(facturaLogic.getFacturas());
        return listaFacturas;
    }

    /**
     * Crea una nueva factura
     *
     * @param factura Nuevo factura que se va a crear
     * @return factura creada
     */
    @POST
    public FacturaDTO crearFactura(FacturaDTO factura) throws BusinessLogicException {
        FacturaDTO nuevaFacturaDTO = new FacturaDTO(facturaLogic.createFactura(factura.toEntity()));
        return nuevaFacturaDTO;
    }

    /**
     * Obtiene la factura con identificador id
     *
     * @param id Identificador de la factura
     * @return Factura con identificador id
     */
    @GET
    @Path("{id: \\d+}")
    public FacturaDTO obtenerFacturaID(@PathParam("id") Long id) {
        FacturaEntity facturaEntity = facturaLogic.getFactura(id);
        if (facturaEntity == null) {
            throw new WebApplicationException("El recurso /facturas/" + id + " no existe.", 404);
        }
        FacturaDTO facturaDTO = new FacturaDTO(facturaEntity);
        return facturaDTO;
    }

    /**
     * Actualiza una factura con identificador id
     *
     * @param id Identificador de la factura
     * @param factura Nueva informacion de la factura
     * @return Factura actualizada
     */
    @PUT
    @Path("{id: \\d+}")
    public FacturaDTO actualizarFacturaID(@PathParam("id") Long id, FacturaDTO factura) throws BusinessLogicException {
        factura.setId(id);
        if (facturaLogic.getFactura(id) == null) {
            throw new WebApplicationException("El recurso /facturas/" + id + " no existe.", 404);
        }
        FacturaDTO DTO = new FacturaDTO(facturaLogic.updateFactura(id, factura.toEntity()));
        return DTO;
    }

    /**
     * Elimina una factura con identificador id
     *
     * @param id Identificador de la factura
     * @return Factura eliminada
     */
    @DELETE
    @Path("{id: \\d+}")
    public void eliminarFacturaID(@PathParam("id") Long id) {
        FacturaEntity entity = facturaLogic.getFactura(id);
        if (entity == null) {
            throw new WebApplicationException("El recurso /facturas/" + id + " no existe.", 404);
        }
        facturaLogic.deleteFactura(id);
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos FacturaEntity a una lista de
     * objetos FacturaDTO (json)
     *
     * @param entityList corresponde a la lista de seguros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de facturas en forma DTO (json)
     */
    private List<FacturaDTO> listEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturaDTO> list = new ArrayList<>();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDTO(entity));
        }
        return list;
    }

}
