/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.MetodoDePagoDTO;
import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

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
    
    @POST
    public MetodoDePagoDTO createMetodoDePago(MetodoDePagoDTO metodo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MetodoDePagoResource createMetodoDePago: input: {0}", metodo);
        // Convierte el DTO (json) en un objeto Entity para ser manejado por la lógica.
        MetodoDePagoEntity metodoDePagoEntity = metodo.toEntity();
        // Invoca la lógica para crear el metodo de pago nueva
        /**MetodoDePagoEntity nuevoEditorialEntity = metodoDePagoLogic.createEditorial(metodoDePagoEntity);
        // Como debe retornar un DTO (json) se invoca el constructor del DTO con argumento el entity nuevo
        * */
        //TODO cambiar esto al terminar el logic
        MetodoDePagoEntity nuevoEditorialEntity = new MetodoDePagoEntity();
        MetodoDePagoDTO nuevoMetodoDTO = new MetodoDePagoDTO(nuevoEditorialEntity);
        LOGGER.log(Level.INFO, "EditorialResource createEditorial: output: {0}", nuevoMetodoDTO);
        return nuevoMetodoDTO;
    }

   
}
