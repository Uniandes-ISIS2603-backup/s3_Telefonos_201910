/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.CarritoDeComprasDTO;
import co.edu.uniandes.csw.telefonos.dtos.CarritoDeComprasDetailDTO;
import co.edu.uniandes.csw.telefonos.dtos.CompradorDetailDTO;
import co.edu.uniandes.csw.telefonos.ejb.CarritoDeComprasLogic;
import co.edu.uniandes.csw.telefonos.ejb.CompradorCarritoDeComprasLogic;
import co.edu.uniandes.csw.telefonos.ejb.CompradorLogic;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Daniel Babativa
 */
@Produces("application/json")
@Consumes("application/json")
public class CompradorCarritoDeComprasResource {
    private static final Logger LOGGER = Logger.getLogger(CompradorListaDeDeseosResource.class.getName());
    
    @Inject 
    private CompradorLogic compradorLogic;
    
    @Inject
    private CompradorCarritoDeComprasLogic compradorCarritoLogic;
    
    @Inject 
    private CarritoDeComprasLogic carritoDeComprasLogic;
    
    @GET
    public CarritoDeComprasDetailDTO getCarritoDeCompras(@PathParam("compradorId") Long compradorId) {
        CarritoDeComprasDetailDTO listaDTO = new CarritoDeComprasDetailDTO(compradorCarritoLogic.getCarritoDeCompras(compradorId));
        return listaDTO;
    }
    
    
     @PUT
    public void vaciarCarritoDeCompras(Long idComprador){
        if(compradorLogic.getComprador(idComprador) == null){
            throw new WebApplicationException("El recurso /compradores/" + idComprador + " no existe.", 404);
        }
        compradorCarritoLogic.removeCarritoDeCompras(idComprador);
    }
    

   
   
   
   
    
   
 
}
