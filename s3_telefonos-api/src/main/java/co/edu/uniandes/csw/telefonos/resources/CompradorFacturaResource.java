/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.CompradorDTO;
import co.edu.uniandes.csw.telefonos.dtos.FacturaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CompradorFacturaResource {

    private static final Logger LOGGER = Logger.getLogger(CompradorFacturaResource.class.getName());

    /**
     * Relaciona una factura a un comprador
     *
     * @param compradorId Identificador del comprador con el que se quiere
     * relacionar la factura
     * @param facturaId Identificador de la factura que se quiere asociar a un
     * comprador
     * @return Factura relacionada con el comprador
     */
    @POST
    @Path("{facturaId: \\d+}")
    public FacturaDTO agregarFactura(@PathParam("compradorId") Long compradorId, @PathParam("facturaId") Long facturaId) {
        FacturaDTO f = new FacturaDTO();
        CompradorDTO c = new CompradorDTO();
        f.setId(facturaId);
        f.setComprador(c);
        return f;
    }

    /**
     * Obtiene todas las facturas asociadas comprador 
     * @param compradorId
     * @return 
     */
    @GET
    public List<FacturaDTO> obtenerFacturas(@PathParam("compradorId") Long compradorId) {
        List<FacturaDTO> result = new ArrayList();
        FacturaDTO f = new FacturaDTO();
        f.setReferencia("Prueba C-F");
        result.add(f);
        return result;
    }
    
    /**
     * Obtiene una factura con identificador dado de un comprador con identificador dado
     * @param compradorId Identificador del comprador
     * @param facturaId Identificador de la factura a la que se quiere acceder
     * @return 
     */
    @GET
    @Path("{facturaId: \\d+}")
    public FacturaDTO obtenerFactura(@PathParam("compradorId") Long compradorId, @PathParam("facturaId") Long facturaId) {
        FacturaDTO f = new FacturaDTO();
        f.setReferencia("Prueba C-F");
        f.setId(facturaId);
        return f;
    }
    
    /**
     * Elimina una factura de un ocomprador con identificador dado
     * @param compradorId Identificador del comprador
     * @param facturaId Identificador de la factura que se quiere eliminar
     * @return Esto debe quitarse!!! Solo es para probar en Postman
     */
    @DELETE
    @Path("{facturaId: \\d+}")
    public String eliminarFactura(@PathParam("compradorId") Long compradorId, @PathParam("facturaId") Long facturaId) {
        return ("Comp: "+compradorId+" -- Fact: "+facturaId);
    }
}
