/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.ejb.CompradorListaDeDeseosLogic;
import co.edu.uniandes.csw.telefonos.ejb.CompradorLogic;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Produces("application/json")
@Consumes("application/json")
public class CompradorListaDeDeseosResource {
    
    private static final Logger LOGGER = Logger.getLogger(CompradorListaDeDeseosResource.class.getName());
    
    @Inject 
    private CompradorLogic compradorLogic;
    
    @Inject
    private CompradorListaDeDeseosLogic compradorListaLogic;
    
    @Inject 
    private ListaDeDeseosLogic listaDeDeseosLogic;
    
    /*
    *Vacia la lista de deseos de un comprador
    *
    *@param idComprador id del comprador al que se le vaciara la lista de deseos
    */
    @PUT
    public void vaciarListaDeDeseos(Long idComprador){
        if(compradorLogic.getComprador(idComprador) == null){
            throw new WebApplicationException("El recurso /compradores/" + idComprador + " no existe.", 404);
        }
        compradorListaLogic.vaciarListaDeDeseos(idComprador);
    }
    
    

    
}
