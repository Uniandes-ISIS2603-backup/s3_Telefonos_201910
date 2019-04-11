/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;


import co.edu.uniandes.csw.telefonos.dtos.CarritoDeComprasDetailDTO;
import co.edu.uniandes.csw.telefonos.dtos.PublicacionDTO;
import co.edu.uniandes.csw.telefonos.ejb.CarritoDeComprasPublicacionLogic;
import co.edu.uniandes.csw.telefonos.ejb.PublicacionLogic;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Daniel Babativa
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class CarritoDeComprasPublicacionResource {
     
    @Inject
    private CarritoDeComprasPublicacionLogic carritoPublicacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private PublicacionLogic publicacionLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Asocia una pubicacion con un carrito de compras
     * @param carritoId id del carrito
     * @param publicacionId id de la publicacion
     * @return detail del carrito de compras
     * @throws BusinessLogicException 
     */
    @POST
    @Path("{tabletId: \\d+}")
    public CarritoDeComprasDetailDTO addPublicacion(@PathParam("carritoId") Long carritoId, @PathParam("publicacionId") Long publicacionId) throws BusinessLogicException {
        if (publicacionLogic.getPublicacion(publicacionId) == null) {
            throw new WebApplicationException("El recurso /publicaciones/" + publicacionId + " no existe.", 404);
        }
        CarritoDeComprasDetailDTO carritoDetailDTO = new CarritoDeComprasDetailDTO(carritoPublicacionLogic.agregarPublicacion(publicacionId, carritoId));
        return carritoDetailDTO;
    }

    /**
     * Retorna todas las publicaciones de un carrito de compras
     * @param carritoId id del carrito de compras
     * @return 
     */
    @GET
    public List<PublicacionDTO> getPublicaciones(@PathParam("carritoId") Long carritoId) {
        List<PublicacionDTO> listaDTOs = publicacionesListEntity2DTO(carritoPublicacionLogic.getPublicaciones(carritoId));

        return listaDTOs;
    }

    /**
     * Busca la publicacion del carrito 
     * @param carritoId id del carrito de compras
     * @param pId id de la publicacion
     * @return el DTO de la publicacion
     * @throws BusinessLogicException 
     */
    @GET
    @Path("{pId: \\d+}")
    public PublicacionDTO getPublicacion(@PathParam("carritoId") Long carritoId, @PathParam("pId") Long pId) throws BusinessLogicException {
        if (publicacionLogic.getPublicacion(pId) == null) {
            throw new WebApplicationException("El recurso /carritosDeCompras/" + carritoId + "/publicaciones/" + pId + " no existe.", 404);
        }
        PublicacionDTO pDTO = new PublicacionDTO(carritoPublicacionLogic.getPublicacion(carritoId, pId));

        return pDTO;
    }

    

    /**
     * Convierte una lista de TabletEntity a una lista de TabletDTO.
     *
     * @param entityList Lista de TabletEntity a convertir.
     * @return Lista de TabletDTO convertida.
     */
    private List<PublicacionDTO> publicacionesListEntity2DTO(List<PublicacionEntity> entityList) {
        List<PublicacionDTO> list = new ArrayList();
        for (PublicacionEntity entity : entityList) {
            list.add(new PublicacionDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de TabletDTO a una lista de TabletEntity.
     *
     * @param dtos Lista de TabletDTO a convertir.
     * @return Lista de TabletEntity convertida.
     */
    private List<PublicacionEntity> publicacionesListDTO2Entity(List<PublicacionDTO> dtos) {
        List<PublicacionEntity> list = new ArrayList<>();
        for (PublicacionDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
}
}
