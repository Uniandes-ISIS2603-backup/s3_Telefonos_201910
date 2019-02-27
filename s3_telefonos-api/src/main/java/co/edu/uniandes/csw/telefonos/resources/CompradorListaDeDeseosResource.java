/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.ejb.CompradorLogic;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosLogic;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Path("compradores/{compradorId: \\d+}/listaDeDeseos")
@Produces("application/json")
@Consumes("application/json")
public class CompradorListaDeDeseosResource {
    
    private static final Logger LOGGER = Logger.getLogger(CompradorListaDeDeseosResource.class.getName());
    
    @Inject 
    private CompradorLogic compradorLogic;
    
    //@Inject
    //private CompradorListaDeDeseosLogic compradorListaLogic;
    
    @Inject 
    private ListaDeDeseosLogic listaDeDeseosLogic;
    
    

    
}
