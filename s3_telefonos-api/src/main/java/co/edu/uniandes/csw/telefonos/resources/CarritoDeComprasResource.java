/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;
import co.edu.uniandes.csw.telefonos.dtos.CarritoDeComprasDTO;
import java.util.logging.Logger;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

@Path("carritosDeCompras")
@Produces("aplication/json")
@Consumes("aplication/json")
@RequestScoped
/**
 *
 * @author estudiante
 */
public class CarritoDeComprasResource {
	
		
		private final static Logger LOGGER = Logger.getLogger(CarritoDeComprasResource.class.getName());
		
		@POST
		public CarritoDeComprasDTO createCarritoDeCompras(CarritoDeComprasDTO carritoDeCompras)
		{
			return carritoDeCompras;
		}
}
