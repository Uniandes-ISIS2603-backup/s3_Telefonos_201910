/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.CarritoDeComprasDTO;
import co.edu.uniandes.csw.telefonos.dtos.CarritoDeComprasDetailDTO;
import co.edu.uniandes.csw.telefonos.ejb.CarritoDeComprasLogic;
import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

@Path("carritosDeCompras")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped
/**
 *
 * @author estudiante
 */
public class CarritoDeComprasResource {

    @Inject
    private CarritoDeComprasLogic logica;
    
    private final static Logger LOGGER = Logger.getLogger(CarritoDeComprasResource.class.getName());

     @POST
    public CarritoDeComprasDTO createCarritoDeCompras(CarritoDeComprasDTO carrito)throws BusinessLogicException{
         CarritoDeComprasEntity entity = carrito.toEntity();
        CarritoDeComprasEntity newEntity = logica.createCarritoDeCompras(entity);
        CarritoDeComprasDTO newDTO = new CarritoDeComprasDTO(newEntity);
        return newDTO;
    }
    

 
    @GET
    public List<CarritoDeComprasDetailDTO> getListasDeDeseos (){
        List<CarritoDeComprasDetailDTO> r = listEntityToDetailDTO(logica.getCarritoDeCompras());
        return r;
    }
    
    
    @GET
    @Path("{carritoId: \\d+}")
    public CarritoDeComprasDetailDTO getCarritoDeCompras(@PathParam("carritoId") Long carritoId) throws WebApplicationException {
       
        CarritoDeComprasEntity entity = logica.getCarritoDeCompras(carritoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /carritosDeCompras/" +carritoId + " no existe.", 404);
        }
        CarritoDeComprasDetailDTO detailDTO = new CarritoDeComprasDetailDTO(entity);
        return detailDTO;
}
   
    
    
    
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos ListaDeDeseosEntity a una lista de
     * objetos ListaDeDeseosDetailDTO (json)
     *
     * @param entityList corresponde a la lista de listas de deseos de tipo Entity
     * que vamos a convertir a DTO.
     * @return la lista de listas de deseos en forma DTO (json)
     */
    private List<CarritoDeComprasDetailDTO> listEntityToDetailDTO(List<CarritoDeComprasEntity> entityList) {
        List<CarritoDeComprasDetailDTO> list = new ArrayList<>();
        for (CarritoDeComprasEntity entity : entityList) {
            list.add(new CarritoDeComprasDetailDTO(entity));
        }
        return list;
}
}
