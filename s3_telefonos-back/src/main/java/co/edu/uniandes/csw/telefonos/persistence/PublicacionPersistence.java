/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class PublicacionPersistence {
     private static final Logger LOGGER = Logger.getLogger(PublicacionPersistence.class.getName());

    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;
      /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param publicacionEntity objeto publicacion que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PublicacionEntity create(PublicacionEntity publicacionEntity) {
        LOGGER.log(Level.INFO, "Creando una publicacion nueva");
        em.persist(publicacionEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una publicacion nueva");
        return publicacionEntity;
    }
	
	/**
     * Devuelve todas las publicaciones de la base de datos.
     *
     * @return una lista con todas las publicaciones que encuentre en la base de
     * datos, "select u from PublicacionEntity u" es como un "select * from
     * PublicacionEntity;" - "SELECT * FROM table_name" en SQL.
     */
    public List<PublicacionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las publicaciones");
        // Se crea un query para buscar todas las publicaciones en la base de datos.
        TypedQuery query = em.createQuery("select u from PublicacionEntity u", PublicacionEntity.class);
        return query.getResultList();
    }
	
    /**
     * Busca si hay alguna publicacion con el id que se envía de argumento
     *
     * @param publicacionId: id correspondiente a la publicacion buscada.
     * @return una publicacion.
     */
    public PublicacionEntity find(Long publicacionId) {
        LOGGER.log(Level.INFO, "Consultando publicacion con id={0}", publicacionId);
        return em.find(PublicacionEntity.class, publicacionId);
    }

	 /**
     * Actualiza una publicacion.
     *
     * @param publicacionEntity: la publicacion que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return una publicacion con los cambios aplicados.
     */
    public PublicacionEntity update(PublicacionEntity publicacionEntity) {
        LOGGER.log(Level.INFO, "Actualizando publicacion con id = {0}", publicacionEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar la publicacion con id = {0}", publicacionEntity.getId());
        return em.merge(publicacionEntity);
    }
	
    /**
     *
     * Borra una publicacion de la base de datos recibiendo como argumento el id
     * de la publicacion
     *
     * @param publicacionId: id correspondiente a la publicacion a borrar.
     */
    public void delete(Long publicacionId) {
        LOGGER.log(Level.INFO, "Borrando publicacion con id = {0}", publicacionId);
        // Se hace uso de mismo método que esta explicado en public PublicacionEntity find(Long id) para obtener la publicacion a borrar.
        PublicacionEntity entity = em.find(PublicacionEntity.class, publicacionId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la publicacion con id = {0}", publicacionId);
    }
	

}
