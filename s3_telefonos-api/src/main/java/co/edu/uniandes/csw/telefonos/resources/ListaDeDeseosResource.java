/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDTO;
import co.edu.uniandes.csw.telefonos.dtos.TabletDTO;
import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDetailDTO;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Andres Felipe Daza Diaz
 */

@Path("listasDeDeseos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class ListaDeDeseosResource {
    
    private static final Logger LOGGER = Logger.getLogger(ListaDeDeseosResource.class.getName());
    
    
    @POST
    public ListaDeDeseosDTO createListaDeDeseos(ListaDeDeseosDTO listaDeDeseos)throws BusinessLogicException{
        
        return listaDeDeseos;
    }
    

    @GET
    public ListaDeDeseosDTO getListaDeDeseos (ListaDeDeseosDTO listaDeDeseos){
        
        return listaDeDeseos;
    }
    
    @DELETE
    public ListaDeDeseosDTO deleteListaDeDeseos (ListaDeDeseosDTO listaDeDeseos){
        return listaDeDeseos;
    }
    
    @PUT
    public ListaDeDeseosDTO updateListaDeDeseos (ListaDeDeseosDTO listaDeDeseos){
        return listaDeDeseos;
    }
}
