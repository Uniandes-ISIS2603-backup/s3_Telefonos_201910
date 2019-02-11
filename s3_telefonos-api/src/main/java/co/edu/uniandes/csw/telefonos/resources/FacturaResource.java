/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.FacturaDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("facturas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FacturaResource {
    private static final Logger LOGGER= Logger.getLogger(FacturaResource.class.getName());
    
    /**
 * Obtiene todas las facturas
 * @return Lista de facturas
 */
@GET
public List<FacturaDTO> obtenerFacturas (){
    List<FacturaDTO> result = new ArrayList();
    FacturaDTO f = new FacturaDTO();
    f.setReferencia("Ref prueba");
    result.add(f);
    return result;
}


/**
 * Crea una nueva factura
 * @param factura Nuevo factura que se va a crear
 * @return comprador creado
 */
@POST
public FacturaDTO crearFactura (FacturaDTO factura){
    return factura;
}

/**
 * Obtiene la factura con identificador id
 * @param id Identificador de la factura
 * @return Factura con identificador id
 */
@GET
@Path("{id: \\d+}")
 public FacturaDTO obtenerFacturaID(@PathParam("id") int id) {
     FacturaDTO f =new FacturaDTO();
     f.setReferencia("Ref "+id);
     return f;
 }
   
/**
 * Actualiza una factura con identificador id
 * @param id Identificador de la factura
 * @param factura Nueva informacion de la factura
 * @return Factura actualizada
 */ 
@PUT
@Path("{id: \\d+}")
 public FacturaDTO  actualizarFacturaID(@PathParam("id") int id, FacturaDTO factura){
     factura.setReferencia("Actualizar "+ id);
     return factura;
 }
  
 /**
  * Elimina una factura con identificador id
  * @param id Identificador de la factura
  * @return Factura eliminada
  */
 @DELETE
 @Path("{id: \\d+}")
 public FacturaDTO  eliminarFacturaID(@PathParam("id") int id){
     FacturaDTO f =new FacturaDTO();
     f.setReferencia("Delete "+ id);
     return f;
 }
    
}
