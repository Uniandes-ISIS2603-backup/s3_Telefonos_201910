/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.resources;

import co.edu.uniandes.csw.telefonos.dtos.CelularDTO;
import co.edu.uniandes.csw.telefonos.dtos.ListaDeDeseosDetailDTO;
import co.edu.uniandes.csw.telefonos.dtos.TabletDTO;
import co.edu.uniandes.csw.telefonos.ejb.CelularLogic;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosCelularLogic;
import co.edu.uniandes.csw.telefonos.ejb.ListaDeDeseosTabletLogic;
import co.edu.uniandes.csw.telefonos.ejb.TabletLogic;
import co.edu.uniandes.csw.telefonos.entities.CelularEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
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
 * @author Andres Felipe Daza Diaz
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequestScoped
public class ListaDeDeseosCelularResource {
    
     @Inject
    private ListaDeDeseosCelularLogic listaCelularLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private CelularLogic celularLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un celular dentro de una lista de deseos con la informacion que recibe el
     * la URL. Se devuelve el celular que se guarda en la lista.
     *
     * @param listaId Identificador de la editorial que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param celularIMEI IMEI del celular que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link CelularDTO} - La lista con el celular guardado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el celular.
     */
    @POST
    @Path("{celularIMEI: \\d+}")
    public ListaDeDeseosDetailDTO addCelular(@PathParam("celularIMEI") Long listaId, @PathParam("celularIMEI") Long celularIMEI) throws BusinessLogicException {
        if (celularLogic.getCelularRegistrado(listaId)==null) {
            throw new WebApplicationException("El recurso /celulares/" + celularIMEI + " no existe.", 404);
        }
        ListaDeDeseosDetailDTO listaDetailDTO = new ListaDeDeseosDetailDTO(listaCelularLogic.agregarCelular(celularIMEI, listaId));
        return listaDetailDTO;
    }

    /**
     * Busca y devuelve todos los celulares que existen en la lista de deseos.
     *
     * @param listaId Identificador de la lista que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link CeluarDTO} - Los celulares encontradas en la
     * lista. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CelularDTO> getCelulares(@PathParam("listaId") Long listaId) {
        List<CelularDTO> listaDTOs = celularesListEntity2DTO(listaCelularLogic.getCelulares(listaId));

        return listaDTOs;
    }

    /**
     * Busca el celular con el IMEI asociado dentro de la lista de deseos con id asociado.
     *
     * @param listaId Identificador de la lista de deseos que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param celularIMEI IMEI del celular que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link TabletDTO} - El celular buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el celular.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el celular en la lista de deseos.
     */
    @GET
    @Path("{celularIMEI: \\d+}")
    public CelularDTO getCelular(@PathParam("listaId") Long listaId, @PathParam("celularIMEI") Long celularIMEI) throws BusinessLogicException {
        if (celularLogic.getCelularRegistrado(celularIMEI) == null) {
            throw new WebApplicationException("El recurso /listasDeDeseos/" + listaId + "/celulares/" + celularIMEI + " no existe.", 404);
        }
        CelularDTO celularDTO = new CelularDTO(listaCelularLogic.getCelular(listaId, celularIMEI));

        return celularDTO;
    }

    

    /**
     * Convierte una lista de CelularEntity a una lista de CelulartDTO.
     *
     * @param entityList Lista de CelularEntity a convertir.
     * @return Lista de CelularDTO convertida.
     */
    private List<CelularDTO> celularesListEntity2DTO(List<CelularEntity> entityList) {
        List<CelularDTO> list = new ArrayList();
        if(entityList!=null){
        for (CelularEntity entity : entityList) {
            list.add(new CelularDTO(entity));
        }
        }
        return list;
    }

    /**
     * Convierte una lista de CelularDTO a una lista de CelularEntity.
     *
     * @param dtos Lista de CelularDTO a convertir.
     * @return Lista de CelularEntity convertida.
     */
    private List<CelularEntity> celularesListDTO2Entity(List<CelularDTO> dtos) {
        List<CelularEntity> list = new ArrayList<>();
        if(dtos!=null){
        for (CelularDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        }
        return list;
}
    
}
