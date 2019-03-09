/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDetailDTO;
import co.edu.uniandes.csw.telefonos.dtos.TabletDTO;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosTabletLogic;
import co.edu.uniandes.csw.telefonos.ejb.TabletLogic;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
 * @author Andres Felipe Daza Diaz
 *
 * */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ListaDeDeseosTabletResource {
    
    @Inject
    private ListaDeDeseosTabletLogic listaTabletLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private TabletLogic tabletLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda una tablet dentro de una lista de deseos con la informacion que recibe el
     * la URL. Se devuelve la tablet que se guarda en la lista.
     *
     * @param listaId Identificador de la editorial que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param tabletRef Referencia de la tablet que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link BookDTO} - La lista con la tablet guardada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la tablet.
     */
    @POST
    @Path("{tabletId: \\d+}")
    public ListaDeDeseosDetailDTO addBook(@PathParam("tabletId") Long listaId, @PathParam("booksId") String tabletRef) throws BusinessLogicException {
        if (tabletLogic.getTablet(tabletRef) == null) {
            throw new WebApplicationException("El recurso /tablets/" + tabletRef + " no existe.", 404);
        }
        ListaDeDeseosDetailDTO listaDetailDTO = new ListaDeDeseosDetailDTO(listaTabletLogic.agregarTableta(tabletRef, listaId));
        return listaDetailDTO;
    }

    /**
     * Busca y devuelve todas las tablets que existen en la lista de deseos.
     *
     * @param listaId Identificador de la lista que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link TabletDTO} - Las tablets encontradas en la
     * lista. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<TabletDTO> getTablets(@PathParam("listaId") Long listaId) {
        List<TabletDTO> listaDTOs = tabletsListEntity2DTO(listaTabletLogic.getTablets(listaId));

        return listaDTOs;
    }

    /**
     * Busca la tablet con el id asociado dentro de la lista de deseos con id asociado.
     *
     * @param listaId Identificador de la lista de deseos que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param tabletRef Referencia de la tablet que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TabletDTO} - La tablet buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la tablet.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la tablet en la lista de deseos.
     */
    @GET
    @Path("{tabletRef: \\d+}")
    public TabletDTO getTablet(@PathParam("listaId") Long listaId, @PathParam("tabletRef") String tabletRef) throws BusinessLogicException {
        if (tabletLogic.getTablet(tabletRef) == null) {
            throw new WebApplicationException("El recurso /listasDeDeseos/" + listaId + "/tablets/" + tabletRef + " no existe.", 404);
        }
        TabletDTO tabletDTO = new TabletDTO(listaTabletLogic.getTablet(listaId, tabletRef));

        return tabletDTO;
    }

    

    /**
     * Convierte una lista de TabletEntity a una lista de TabletDTO.
     *
     * @param entityList Lista de TabletEntity a convertir.
     * @return Lista de TabletDTO convertida.
     */
    private List<TabletDTO> tabletsListEntity2DTO(List<TabletEntity> entityList) {
        List<TabletDTO> list = new ArrayList();
        for (TabletEntity entity : entityList) {
            list.add(new TabletDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de TabletDTO a una lista de TabletEntity.
     *
     * @param dtos Lista de TabletDTO a convertir.
     * @return Lista de TabletEntity convertida.
     */
    private List<TabletEntity> tabletsListDTO2Entity(List<TabletDTO> dtos) {
        List<TabletEntity> list = new ArrayList<>();
        for (TabletDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
}
    
}
