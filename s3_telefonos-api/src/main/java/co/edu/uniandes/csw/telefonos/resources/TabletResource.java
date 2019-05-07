/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.TabletDTO;
import co.edu.uniandes.csw.telefonos.ejb.TabletLogic;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
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
 * @author Andres Felipe Daza Diaz
 */

@Path("tablets")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class TabletResource {
    
    @Inject
    private TabletLogic tabletLogic;
    
    private static final Logger LOGGER = Logger.getLogger(TabletResource.class.getName());
    
    @POST
    public TabletDTO createTablet(TabletDTO tablet)throws BusinessLogicException{
      TabletDTO tabletDTO = new TabletDTO(tabletLogic.createTablet(tablet.toEntity()));
        return tabletDTO;
    }
    
    @GET
    public List<TabletDTO> getTablets(){
        List<TabletDTO> tablets = listEntityToDTO(tabletLogic.getTablets());
        return tablets;
    }
    
    
    @GET
    @Path("{referencia}")
    public TabletDTO getTablet(@PathParam("referencia") String referencia){
      TabletEntity tabletEntity = tabletLogic.getTablet(referencia);
      if(tabletEntity == null){
          throw new WebApplicationException("El recurso /tablets/"+referencia+" no existe.", 404);
      }
      TabletDTO tabletDTO = new TabletDTO(tabletEntity);
        return tabletDTO;
    }
    
    @PUT
    @Path("{referencia: \\d+}")
    public TabletDTO updateTablet(@PathParam("referencia") String referencia, TabletDTO tablet) throws BusinessLogicException{
      tablet.setReferencia(referencia);
        if (tabletLogic.getTablet(referencia) == null) {
            throw new WebApplicationException("El recurso /tablets/" + referencia + " no existe.", 404);
        }
        TabletDTO tabletDTO = new TabletDTO(tabletLogic.updateTablet(tabletLogic.getTablet(referencia).getReferencia(), tablet.toEntity()));
        return tabletDTO;
    }
    

    @DELETE
    @Path("{referencia: \\d+}")
    public void deleteTablet(@PathParam("referencia") String referencia, TabletDTO tablet){
      TabletEntity entity = tabletLogic.getTablet(referencia);
        if (entity == null) {
            throw new WebApplicationException("El recurso /tablets/" + referencia + " no existe.", 404);
        }
        tabletLogic.deleteTablet(entity.getId());
  
    }
    
    private List<TabletDTO> listEntityToDTO(List<TabletEntity> listaEntidades){
        List<TabletDTO> lista = new ArrayList<>();
        for(TabletEntity entity: listaEntidades){
            lista.add(new TabletDTO(entity));
        }
        return lista;
    }
    
    
}
