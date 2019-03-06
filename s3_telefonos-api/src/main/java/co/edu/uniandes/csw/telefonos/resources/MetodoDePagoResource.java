/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.MetodoDePagoDTO;
import co.edu.uniandes.csw.telefonos.ejb.MetodoDePagoLogic;
import co.edu.uniandes.csw.telefonos.ejb.PublicacionLogic;
import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author rj.gonzalez10
 */
@Path("metodosDePago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MetodoDePagoResource {
    private static final Logger LOGGER = Logger.getLogger(MetodoDePagoResource.class.getName());
    @Inject
    private MetodoDePagoLogic metodoDePagoLogic;
    
     @POST
    public MetodoDePagoDTO createMetodoDePago(MetodoDePagoDTO metodo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MetodoDePagoResource createMetodoDePago: input: {0}", metodo);
        MetodoDePagoEntity metodoDePagoEntity = metodo.toEntity();
        MetodoDePagoEntity nuevoMetodoDePagoEntity = metodoDePagoLogic.createMetodoDePago(metodoDePagoEntity);
        MetodoDePagoDTO nuevoMetodoDePagoDTO = new MetodoDePagoDTO(nuevoMetodoDePagoEntity);
        LOGGER.log(Level.INFO, "MetodoDePagoResource createMetodoDePago: output: {0}", nuevoMetodoDePagoDTO);
        return nuevoMetodoDePagoDTO;
    }
     @GET
    public List<MetodoDePagoDTO> getEditorials() {
        LOGGER.info("MetodoDePagoResource getMetodosDePago: input: void");
        List<MetodoDePagoDTO> listaMetodosDePago = listEntity2DTO(metodoDePagoLogic.getMetodosDePago());
        LOGGER.log(Level.INFO, "EditorialResource getEditorials: output: {0}", listaMetodosDePago);
        return listaMetodosDePago;
    }
   
    
    @GET
    @Path("{metodosDePagoId: \\d+}")
    public MetodoDePagoDTO getMetodoDePago(@PathParam("metodosDePagoId") Long metodoDePagoId) throws WebApplicationException {
        LOGGER.log(Level.INFO, "MetodoDePagoResource getMetodoDePago: input: {0}", metodoDePagoId);
        MetodoDePagoEntity metodoDePagoEntity = metodoDePagoLogic.getMetodoDePago(metodoDePagoId);
        if (metodoDePagoEntity == null) {
            throw new WebApplicationException("El recurso /metodosDePago/" + metodoDePagoId + " no existe.", 404);
        }
        MetodoDePagoDTO detailDTO = new MetodoDePagoDTO(metodoDePagoEntity);
        LOGGER.log(Level.INFO, "MetodoDePagoResource getMetodoDePago: output: {0}", detailDTO);
        return detailDTO;
    }
   
    
     @PUT
    @Path("{metodosDePagoId: \\d+}")
    public MetodoDePagoDTO updateMetodoDePago(@PathParam("metodosDePagoId") Long metodosDePagoId, MetodoDePagoDTO metodoDePago) throws WebApplicationException {
        LOGGER.log(Level.INFO, "MetodoDePagoResource updateMetodoDePago: input: id:{0} , metodoDePago: {1}", new Object[]{metodosDePagoId, metodoDePago});
        metodoDePago.setId(metodosDePagoId);
        if (metodoDePagoLogic.getMetodoDePago(metodosDePagoId) == null) {
            throw new WebApplicationException("El recurso /metodosDePago/" + metodosDePagoId + " no existe.", 404);
        }
        MetodoDePagoDTO detailDTO = new MetodoDePagoDTO(metodoDePagoLogic.updateMetodoDePago(metodosDePagoId, metodoDePago.toEntity()));
        LOGGER.log(Level.INFO, "MetodoDePagoResource updateMetodoDePago: output: {0}", detailDTO);
        return detailDTO;

    }
   
    @DELETE
    @Path("{metodosDePagoId: \\d+}")
    public void deleteMetodoDePago(@PathParam("metodosDePagoId") Long metodosDePagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MetodoDePagoResource deleteMetodoDePago: input: {0}", metodosDePagoId);
        if (metodoDePagoLogic.getMetodoDePago(metodosDePagoId) == null) {
            throw new WebApplicationException("El recurso /metodosDePago/" + metodosDePagoId + " no existe.", 404);
        }
        metodoDePagoLogic.deleteMetodoDePago(metodosDePagoId);
        LOGGER.info("metodosDePagoResource deleteMetodoDePago: output: void");
    }

    
  
    private List<MetodoDePagoDTO> listEntity2DTO(List<MetodoDePagoEntity> entityList) {
        List<MetodoDePagoDTO> list = new ArrayList<>();
        for (MetodoDePagoEntity entity : entityList) {
            list.add(new MetodoDePagoDTO(entity));
        }
        return list;
    }
  
}
