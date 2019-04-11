/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.SeguroDTO;
import co.edu.uniandes.csw.telefonos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.telefonos.ejb.ProveedorSegurosLogic;
import co.edu.uniandes.csw.telefonos.ejb.SeguroLogic;
import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
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
public class ProveedorSegurosResource {

    private static final Logger LOGGER = Logger.getLogger(ProveedorSegurosResource.class.getName());

    @Inject
    private ProveedorLogic proveedorLogic;
    
    @Inject
    private SeguroLogic seguroLogic;
    
    @Inject 
    private ProveedorSegurosLogic proveedorSegurosLogic;
    
    /**
     * Relaciona un seguro a un proveedor
     *
     * @param proveedorId Identificador del proveedor con el que se quiere
     * relacionar el seguro
     * @param seguroId Identificador del seguro que se quiere asociar a un
     * proveedor
     * @return Seguro relacionado con el proveedor
     */
    @POST
    @Path("{seguroId: \\d+}")
    public SeguroDTO agregarSeguro(@PathParam("proveedorId") Long proveedorId, @PathParam("seguroId") Long seguroId) {
        LOGGER.log(Level.INFO, "Entro a agregar en resource");
        if (seguroLogic.getSeguro(seguroId) == null) {
            throw new WebApplicationException("El recurso /seguros/" + seguroId + " no existe.", 404);
        }
        SeguroDTO seguroDTO = new SeguroDTO(proveedorSegurosLogic.addSeguro(seguroId, proveedorId));
        LOGGER.log(Level.INFO, "Salio de agregar en resource");
        return seguroDTO;
    }

    /**
     * Obtiene todos los seguros asociados a un proveedor
     * @param proveedorId Identificador del proveedor
     * @return 
     */
    @GET
    public List<SeguroDTO> obtenerSeguros(@PathParam("proveedorId") Long proveedorId) {
        List<SeguroDTO> listaDetailDTOs = segurosListEntity2DTO(proveedorSegurosLogic.getSeguros(proveedorId));
        return listaDetailDTOs;
    }
    
    /**
     * Obtiene un seguro con identificador dado de un proveedor con identificador dado
     * @param proveedorId Identificador del proveedor
     * @param seguroId Identificador del seguro al que se quiere acceder
     * @return 
     */
    @GET
    @Path("{seguroId: \\d+}")
    public SeguroDTO obtenerSeguro(@PathParam("proveedorId") Long proveedorId, @PathParam("seguroId") Long seguroId) throws BusinessLogicException {
        if (seguroLogic.getSeguro(seguroId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + "/seguros/" + seguroId + " no existe.", 404);
        }
        SeguroDTO seguroDTO = new SeguroDTO(proveedorSegurosLogic.getSeguro(proveedorId, seguroId));
        return seguroDTO;
    }
    
    /**
     * Convierte una lista de SeguroEntity a una lista de SeguroDTO.
     *
     * @param entityList Lista de SeguroEntity a convertir.
     * @return Lista de SeguroDTO convertida.
     */
    private List<SeguroDTO> segurosListEntity2DTO(List<SeguroEntity> entityList) {
        List<SeguroDTO> list = new ArrayList();
        for (SeguroEntity entity : entityList) {
            list.add(new SeguroDTO(entity));
        }
        return list;
    }
}
