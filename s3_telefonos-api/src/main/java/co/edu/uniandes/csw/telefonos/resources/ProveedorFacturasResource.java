/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.FacturaDTO;
import co.edu.uniandes.csw.telefonos.ejb.FacturaLogic;
import co.edu.uniandes.csw.telefonos.ejb.ProveedorFacturasLogic;
import co.edu.uniandes.csw.telefonos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
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
public class ProveedorFacturasResource {

    private static final Logger LOGGER = Logger.getLogger(ProveedorFacturasResource.class.getName());

    @Inject
    private ProveedorLogic proveedorLogic;
    
    @Inject
    private FacturaLogic facturaLogic;
    
    @Inject 
    private ProveedorFacturasLogic proveedorFacturasLogic;
    
    /**
     * Relaciona una factura a un proveedor
     *
     * @param proveedorId Identificador del proveedor con el que se quiere
     * relacionar la factura
     * @param facturaId Identificador de la factura que se quiere asociar a un
     * proveedor
     * @return Factura relacionada con el proveedor
     */
    @POST
    @Path("{facturaId: \\d+}")
    public FacturaDTO agregarFactura(@PathParam("proveedorId") Long proveedorId, @PathParam("facturaId") Long facturaId) {
        if (facturaLogic.getFactura(facturaId) == null) {
            throw new WebApplicationException("El recurso /facturas/" + proveedorId + " no existe.", 404);
        }
        FacturaDTO facturaDTO = new FacturaDTO(proveedorFacturasLogic.addFactura(facturaId, proveedorId));
        return facturaDTO;
    }

    /**
     * Obtiene todas las facturas asociadas a un proveedor
     * @param proveedorId Identificador del proveedor
     * @return 
     */
    @GET
    public List<FacturaDTO> obtenerFacturas(@PathParam("proveedorId") Long proveedorId) {
        List<FacturaDTO> listaDetailDTOs = facturasListEntity2DTO(proveedorFacturasLogic.getFacturas(proveedorId));
        return listaDetailDTOs;
    }
    
    /**
     * Obtiene una factura con identificador dado de un proveedor con identificador dado
     * @param proveedorId Identificador del proveedor
     * @param facturaId Identificador de la factura a la que se quiere acceder
     * @return 
     */
    @GET
    @Path("{facturaId: \\d+}")
    public FacturaDTO obtenerFactura(@PathParam("proveedorId") Long proveedorId, @PathParam("facturaId") Long facturaId) throws BusinessLogicException {
        if (facturaLogic.getFactura(facturaId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + "/facturas/" + facturaId + " no existe.", 404);
        }
        FacturaDTO facturaDTO = new FacturaDTO(proveedorFacturasLogic.getFactura(proveedorId, facturaId));
        return facturaDTO;
    }
    
    /**
     * Convierte una lista de FacturaEntity a una lista de FacturaDTO.
     *
     * @param entityList Lista de FacturaEntity a convertir.
     * @return Lista de FacturaDTO convertida.
     */
    private List<FacturaDTO> facturasListEntity2DTO(List<FacturaEntity> entityList) {
        List<FacturaDTO> list = new ArrayList();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDTO(entity));
        }
        return list;
    }
}
