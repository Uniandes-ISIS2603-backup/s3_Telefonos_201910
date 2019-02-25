/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.ProveedorDTO;
import co.edu.uniandes.csw.telefonos.dtos.ProveedorDetailDTO;
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
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */

@Path("proveedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProveedorResource {
     
private static final Logger LOGGER= Logger.getLogger(CompradorResource.class.getName());  
 
/**
 * Obtiene todos los Proveedores
 * @return Lista de Proveedores
 */
@GET
public List<ProveedorDTO> obtenerProveedores (){
    List<ProveedorDTO> result = new ArrayList();
    ProveedorDTO c = new ProveedorDTO();
    c.setNombre("Prueba");
    result.add(c);
    return result;
}


/**
 * Crea un nuevo proveedor
 * @param proveedor Nuevo Proveedor que se va a crear
 * @return Proveer creado
 */
@POST
public ProveedorDTO crearProveedor (ProveedorDTO proveedor){
    return proveedor;
}

/**
 * Obtiene el proveedor con identificador id
 * @param id Identificador del Proveedor
 * @return Proveedor con identificador id
 */
@GET
@Path("{proveedorId: \\d+}")
 public ProveedorDetailDTO obtenerProveedorID(@PathParam("proveedorId") Long proveedorId) {
     ProveedorDetailDTO c =new ProveedorDetailDTO();
     c.setId(proveedorId);
     return c;
 }
   
/**
 * Actualiza un proveedor con identificador id
 * @param id Identificador del Proveedor
 * @param proveedor Nueva informacion del Proveedor
 * @return Proveedor actualizado
 */ 
@PUT
@Path("{proveedorId: \\d+}")
 public ProveedorDetailDTO  actualizarProveedorID(@PathParam("proveedorId") Long proveedorId, ProveedorDetailDTO proveedor){
     proveedor.setId(proveedorId);
     return proveedor;
 }
  
 /**
  * Elimina un proveedor con identificador id
  * @param proveedorId Identificador del Proveedor
  * @return Proveedor eliminado
  */
 @DELETE
 @Path("{proveedorId: \\d+}")
 public ProveedorDTO  eliminarProveedorID(@PathParam("proveedorId") Long proveedorId){
     ProveedorDTO c =new ProveedorDTO();
     c.setId(proveedorId);
     return c;
 }
 
 /*@Path("{proveedorId: \\d+}/facturas")
    public Class<ProveedorFacturaResource> getProveedorFacturaResource(@PathParam("proveedorId") Long proveedorId) {
        return ProveedorFacturaResource.class;
    }
 */
}