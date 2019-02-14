/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Stateless
public class ListaDeDeseosPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ListaDeDeseosPersistence.class.getName());
    
    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param listaEntity objeto lista de deseos que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ListaDeDeseosEntity create(ListaDeDeseosEntity listaEntity) {
        LOGGER.log(Level.INFO, "Creando una lista de deseos nueva");
        em.persist(listaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una lista de deseos nueva");
        return listaEntity;
}
    
    /**
     * Busca si hay alguna lista de deseos con el id que se envía de argumento
     *
     * @param listaId: id correspondiente a la lista de deseos buscada.
     * @return una lista de deseos.
     */
    public ListaDeDeseosEntity find(Long listaId) {
        LOGGER.log(Level.INFO, "Consultando lista de deseos con id={0}", listaId);
        return em.find(ListaDeDeseosEntity.class, listaId);
}
    
     /**
     * Actualiza una lista de deseos.
     *
     * @param listaEntity: la lista de deseos que viene con los nuevos cambios.
     * @return una lista de deseos con los cambios aplicados.
     */
    public ListaDeDeseosEntity update(ListaDeDeseosEntity listaEntity) {
        LOGGER.log(Level.INFO, "Actualizando lista de deseos con id = {0}", listaEntity.getId());
      
        LOGGER.log(Level.INFO, "Saliendo de actualizar la lista de deseos con id = {0}", listaEntity.getId());
        return em.merge(listaEntity);
        
}
    
    /**
     *
     * Borra una lista de deseos de la base de datos recibiendo como argumento el id
     * de la lista de deseos
     *
     * @param listaId: id correspondiente a la lista de deseos a borrar.
     */
    public void delete(Long listaId) {
        LOGGER.log(Level.INFO, "Borrando lista de deseos con id = {0}", listaId);
        ListaDeDeseosEntity entity = em.find(ListaDeDeseosEntity.class, listaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la lista de deseos con id = {0}", listaId);
}
}
