/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.TabletDTO;
import co.edu.uniandes.csw.telefonos.ejb.TabletLogic;
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
    public TabletDTO createTablet(TabletDTO tablet){
      
        return tablet;
    }
    
    @GET
    public TabletDTO getTablets(TabletDTO tablet){
      
        return tablet;
    }
    
    
    @GET
    @Path("{referencia: \\d+}")
    public TabletDTO getTablet(@PathParam("referencia") String referencia, TabletDTO tablet){
      
        return tablet;
    }
    
    @PUT
    @Path("{referencia: \\d+}")
    public TabletDTO updateTablet(@PathParam("referencia") String referencia, TabletDTO tablet){
      
        return tablet;
    }
    

    @DELETE
    @Path("{referencia: \\d+}")
    public TabletDTO deleteTablet(@PathParam("referencia") String referencia, TabletDTO tablet){
      
        return tablet;
    }
    

    
    
}
