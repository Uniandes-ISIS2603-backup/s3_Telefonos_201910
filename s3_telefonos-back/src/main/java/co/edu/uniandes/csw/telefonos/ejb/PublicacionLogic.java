/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.PublicacionPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class PublicacionLogic {

    private static final Logger LOGGER = Logger.getLogger(PublicacionLogic.class.getName());

    @Inject
    private PublicacionPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una publicacion en la persistencia.
     *
     * @param publicacionEntity La entidad que representa la publicacion a
     * persistir.
     * @return La entiddad de la publicacion luego de persistirla.
     * @throws BusinessLogicException Si la publicacion a persistir ya existe.
     */
    public PublicacionEntity createPublicacion(PublicacionEntity publicacionEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación la publicacion");
        // verifica que no exista una publicación que ya posea el id existente
       
        persistence.create(publicacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la publicón");
        return publicacionEntity;
    }

    /**
     *
     * Obtener todas las publicaciones existentes en la base de datos.
     *
     * @return una lista de publicaciones.
     */
    public List<PublicacionEntity> getPublicaciones() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas la publicaciones");
        List<PublicacionEntity> publicaciones = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las publicaciones");
        return publicaciones;
    }

    /**
     *
     * Obtener una publicacion por medio de su id.
     *
     * @param publicacionesId: id de la publicacion para ser buscada.
     * @return la publicacion solicitada por medio de su id.
     */
    public PublicacionEntity getPublicacion(Long publicacionesId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la publicacion con id = {0}", publicacionesId);
        PublicacionEntity publicacionEntity = persistence.find(publicacionesId);
        if (publicacionEntity == null) {
            LOGGER.log(Level.SEVERE, "La publicaciones con el id = {0} no existe", publicacionesId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la publicacion con id = {0}", publicacionesId);
        return publicacionEntity;
    }

    /**
     *
     * Actualizar una publicacion.
     *
     * @param publicacionesId: id de la publicación para buscarla en la base de
     * datos.
     * @param publicacionEntity: publicación con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la publicacion con los cambios actualizados en la base de datos.
     */
    public PublicacionEntity updatePublicacion(Long publicacionesId, PublicacionEntity publicacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la publiacion con id = {0}", publicacionesId);
        PublicacionEntity newEntity = persistence.update(publicacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la publicacion con id = {0}", publicacionEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un publicacion
     *
     * @param publicacionesId: id de la publicacion a borrar
     * @throws BusinessLogicException Si la publicacion a eliminar tiene libros.
     */
    public void deletePublicacion(Long publicacionesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la publicacion con id = {0}", publicacionesId);
        persistence.delete(publicacionesId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la publicacion con id = {0}", publicacionesId);
    }
}
