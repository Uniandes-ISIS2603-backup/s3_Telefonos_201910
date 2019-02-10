/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.TabletDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    
    private static final Logger LOGGER = Logger.getLogger(TabletResource.class.getName());
    
    @POST
    public TabletDTO createTablet(TabletDTO tablet){
        
        return tablet;
    }
}
