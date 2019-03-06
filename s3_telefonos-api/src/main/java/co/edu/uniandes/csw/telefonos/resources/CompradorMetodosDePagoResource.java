/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.CompradorDTO;
import co.edu.uniandes.csw.telefonos.dtos.MetodoDePagoDTO;
import co.edu.uniandes.csw.telefonos.ejb.CompradorLogic;
import co.edu.uniandes.csw.telefonos.ejb.CompradorMetodosDePagoLogic;
import co.edu.uniandes.csw.telefonos.ejb.MetodoDePagoLogic;
import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompradorMetodosDePagoResource {

    private static final Logger LOGGER = Logger.getLogger(CompradorMetodosDePagoResource.class.getName());

    @Inject
    private CompradorLogic compradorLogic;
    
    @Inject
    private MetodoDePagoLogic metodoDePagoLogic;
    
    @Inject 
    private CompradorMetodosDePagoLogic compradorMetodosDePagoLogic;
    
    /**
     * Relaciona un metodo de pago a un comprador
     *
     * @param compradorId Identificador del comprador con el que se quiere
     * relacionar el metodo de pago
     * @param metodoDePagoId Identificador del metodo de pago que se quiere asociar a un
     * comprador
     * @return Metodo de pago relacionado con el comprador
     */
    @POST
    @Path("{metodoDePagoId: \\d+}")
    public MetodoDePagoDTO agregarMetodoDePago(@PathParam("compradorId") Long compradorId, @PathParam("metodoDePagoId") Long metodoDePagoId) {
        if (compradorLogic.getComprador(compradorId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + " no existe.", 404);
        }
        MetodoDePagoDTO metodoDePagoDTO = new MetodoDePagoDTO(compradorMetodosDePagoLogic.addMetodoDePago(compradorId, metodoDePagoId));
        return metodoDePagoDTO;
    }

    /**
     * Obtiene todos los metodos de pago asociados a un comprador 
     * @param compradorId Identificador del comprador
     * @return 
     */
    @GET
    public List<MetodoDePagoDTO> obtenerMetodosDePago(@PathParam("compradorId") Long compradorId) {
        List<MetodoDePagoDTO> listaDetailDTOs = metodosDePagoListEntity2DTO(compradorMetodosDePagoLogic.getMetodosDePago(compradorId));
        return listaDetailDTOs;
    }
    
    /**
     * Obtiene un metodo de pago con identificador dado de un comprador con identificador dado
     * @param compradorId Identificador del comprador
     * @param metodoDePagoId Identificador del metodo de pago al que se quiere acceder
     * @return 
     */
    @GET
    @Path("{metodoDePagoId: \\d+}")
    public MetodoDePagoDTO obtenerMetodoDePago(@PathParam("compradorId") Long compradorId, @PathParam("metodoDePagoId") Long metodoDePagoId) throws BusinessLogicException {
        if (metodoDePagoLogic.getMetodoDePago(metodoDePagoId) == null) {
            throw new WebApplicationException("El recurso /compradores/" + compradorId + "/metodosDePago/" + metodoDePagoId + " no existe.", 404);
        }
        MetodoDePagoDTO metodoDePagoDTO = new MetodoDePagoDTO(compradorMetodosDePagoLogic.getMetodoDePago(compradorId, metodoDePagoId));
        return metodoDePagoDTO;
    }
    
    /**
     * Convierte una lista de MetodoDePagoEntity a una lista de MetodoDePagoDTO.
     *
     * @param entityList Lista de MetodoDePagoEntity a convertir.
     * @return Lista de MetodoDePagoDTO convertida.
     */
    private List<MetodoDePagoDTO> metodosDePagoListEntity2DTO(List<MetodoDePagoEntity> entityList) {
        List<MetodoDePagoDTO> list = new ArrayList();
        for (MetodoDePagoEntity entity : entityList) {
            list.add(new MetodoDePagoDTO(entity));
        }
        return list;
    }
}
