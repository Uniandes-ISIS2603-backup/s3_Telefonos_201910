/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDTO;
import co.edu.uniandes.csw.telefonos.dtos.TabletDTO;
import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDetailDTO;
import java.util.logging.Logger;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Andres Felipe Daza
 */

@Path("listasdedeseos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ListaDeDeseosResource {
    
    private static final Logger LOGGER = Logger.getLogger(ListaDeDeseosResource.class.getName());
    
    @POST
    public ListaDeDeseosDTO createListaDeDeseos(ListaDeDeseosDTO listaDeDeseos){
        
        return listaDeDeseos;
    }
    

    @POST
    @PATH("tabletas")
    public ListaDeDeseosDetailDTO agregarTableta (ListaDeDeseosDetailDTO listaDeDeseos, TabletDTO tableta){
        List<TabletDTO> tabletas = listaDeDeseos.getTablets();
        tabletas.add(tableta);
        listaDeDeseos.setTablets(tabletas);
        return listaDeDeseos;
    }
    
}
