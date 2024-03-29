/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.ProveedorDTO;
import co.edu.uniandes.csw.telefonos.dtos.ProveedorDetailDTO;
import co.edu.uniandes.csw.telefonos.ejb.ProveedorLogic;
import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
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
@Path("proveedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ProveedorResource {

    private static final Logger LOGGER = Logger.getLogger(CompradorResource.class.getName());

    @Inject
    private ProveedorLogic proveedorLogic;

    /**
     * Obtiene todos los Proveedores
     *
     * @return Lista de Proveedores
     */
    @GET
    public List<ProveedorDetailDTO> obtenerProveedores() {
        List<ProveedorDetailDTO> result = listEntityToDetailDTO(proveedorLogic.getProveedores());
        return result;
    }

    /**
     * Crea un nuevo proveedor
     *
     * @param proveedor Nuevo Proveedor que se va a crear
     * @return Proveer creado
     */
    @POST
    public ProveedorDTO crearProveedor(ProveedorDTO proveedor) throws BusinessLogicException {
        ProveedorDTO proveedorDTO = new ProveedorDTO(proveedorLogic.createProveedor(proveedor.toEntity()));
        return proveedorDTO;
    }

    /**
     * Obtiene el proveedor con identificador id
     *
     * @param id Identificador del Proveedor
     * @return Proveedor con identificador id
     */
    @GET
    @Path("{proveedorId: \\d+}")
    public ProveedorDetailDTO obtenerProveedorID(@PathParam("proveedorId") Long proveedorId) {
        ProveedorEntity proveedorEntity = proveedorLogic.getProveedor(proveedorId);
        if (proveedorEntity == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + " no existe.", 404);
        }
        ProveedorDetailDTO proveedorDetailDTO = new ProveedorDetailDTO(proveedorEntity);
        return proveedorDetailDTO;
    }

         /**
     * Obtiene el comprador con username
     *
     * @param username Usuario del comprador
     * @return Comprador con Usuario usernamen
     */
    @GET
    @Path("{proveedorUsername}")
    public ProveedorDetailDTO obtenerProveedorUsername(@PathParam("proveedorUsername") String usuario) {
        ProveedorEntity proveedorEntity = proveedorLogic.getProveedorUsuario(usuario);
        if (proveedorEntity == null) {
            throw new WebApplicationException("El recurso /proveedores/" + usuario + " no existe.", 404);
        }
        ProveedorDetailDTO proveedorDetailDTO = new ProveedorDetailDTO(proveedorEntity);
        return proveedorDetailDTO;
    }
    
    /**
     * Actualiza un proveedor con identificador id
     *
     * @param id Identificador del Proveedor
     * @param proveedor Nueva informacion del Proveedor
     * @return Proveedor actualizado
     */
    @PUT
    @Path("{proveedorId: \\d+}")
    public ProveedorDetailDTO actualizarProveedorID(@PathParam("proveedorId") Long proveedorId, ProveedorDetailDTO proveedor) throws BusinessLogicException {
        proveedor.setId(proveedorId);
        if (proveedorLogic.getProveedor(proveedorId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + " no existe.", 404);
        }
        ProveedorDetailDTO detailDTO = new ProveedorDetailDTO(proveedorLogic.updateProveedor(proveedorId, proveedor.toEntity()));
        return detailDTO;
    }

    /**
     * Elimina un proveedor con identificador id
     *
     * @param proveedorId Identificador del Proveedor
     * @return Proveedor eliminado
     */
    @DELETE
    @Path("{proveedorId: \\d+}")
    public void eliminarProveedorID(@PathParam("proveedorId") Long proveedorId) {
        ProveedorEntity entity = proveedorLogic.getProveedor(proveedorId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + " no existe.", 404);
        }
        proveedorLogic.deleteProveedor(proveedorId);
    }
    
    /**
     * Devuelve el Resource que asocia proveedores con facturas
     * @param proveedorId Identificador del proveedor
     * @return Clase de asociacion
     */
    @Path("{proveedorId: \\d+}/facturas")
    public Class<ProveedorFacturasResource> getProveedorFacturaResource(@PathParam("proveedorId") Long proveedorId) {
        if (proveedorLogic.getProveedor(proveedorId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + " no existe.", 404);
        }
        return ProveedorFacturasResource.class;
    }
        
    /**
     * Devuelve el Resource que asocia proveedores con seguros
     * @param proveedorId Identificador del proveedor
     * @return Clase de asociacion
     */
    @Path("{proveedorId: \\d+}/seguros")
    public Class<ProveedorSegurosResource> getCompradorMetodoDePagoResource(@PathParam("proveedorId") Long proveedorId) {
        if (proveedorLogic.getProveedor(proveedorId) == null) {
            throw new WebApplicationException("El recurso /proveedores/" + proveedorId + " no existe.", 404);
        }
        return ProveedorSegurosResource.class;
    }
    
    private List<ProveedorDetailDTO> listEntityToDetailDTO(List<ProveedorEntity> listaEntidades) {
        List<ProveedorDetailDTO> lista = new ArrayList<>();
        for (ProveedorEntity entity : listaEntidades) {
            lista.add(new ProveedorDetailDTO(entity));
        }
        return lista;
    }
}
