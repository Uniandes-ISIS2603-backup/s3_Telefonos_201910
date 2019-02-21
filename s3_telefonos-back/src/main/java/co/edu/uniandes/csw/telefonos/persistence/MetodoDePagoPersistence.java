/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
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
public class MetodoDePagoPersistence {
    private static final Logger LOGGER = Logger.getLogger(MetodoDePagoPersistence.class.getName());
    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;
    /**
     * metodo que crea un nuevo metodo de pago en la base de datos
     * @param metodoDePagoEntity entidad a ser guardada
     * @return metodoDePagoEntity con un id asignado por el sistema
     */
    public MetodoDePagoEntity create(MetodoDePagoEntity metodoDePagoEntity)
    {
        LOGGER.log(Level.INFO, "se inicia la creación de un método de pago");
        em.persist(metodoDePagoEntity);
        LOGGER.log(Level.INFO, "termina la creación de un nuevo método de pago");
        return metodoDePagoEntity;
    }
     public List<MetodoDePagoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los metodos de pago");
        
        TypedQuery query = em.createQuery("select u from MetodoDePagoEntity u", MetodoDePagoEntity.class);
        return query.getResultList();
    }
	
    /**
     * Busca si hay algun método de pago con el id que se envía de argumento
     *
     * @param metodoId: id correspondiente a la metodoDePago buscado.
     * @return una metodoDePago.
     */
    public MetodoDePagoEntity find(Long metodoId) {
        LOGGER.log(Level.INFO, "Consultando metodoDePago con id={0}", metodoId);
        return em.find(MetodoDePagoEntity.class, metodoId);
    }

	 /**
     * Actualiza un método de pago
     *
     * @param metodoDePagoEntity: el método de pago que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un ´método de pago con los cambios aplicados.
     */
    public MetodoDePagoEntity update(MetodoDePagoEntity metodoDePagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el método de pago con id = {0}", metodoDePagoEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el método de pago con id = {0}", metodoDePagoEntity.getId());
        return em.merge(metodoDePagoEntity);
    }
	
    /**
     *
     * Borra un metodoDePago de la base de datos recibiendo como argumento el id
     * de la metodoDePago
     *
     * @param metodoDePagosId: id correspondiente a la metodoDePago a borrar.
     */
    public void delete(Long metodoDePagosId) {
        LOGGER.log(Level.INFO, "Borrando metodoDePago con id = {0}", metodoDePagosId);
        // Se hace uso de mismo método que esta explicado en public MetodoDePagoEntity find(Long id) para obtener la metodoDePago a borrar.
        MetodoDePagoEntity entity = em.find(MetodoDePagoEntity.class, metodoDePagosId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la metodoDePago con id = {0}", metodoDePagosId);
    }
	
    /**
     * Busca si hay alguna metodoDePago con el nombre que se envía de argumento
     *
     * @param name: Nombre de la metodoDePago que se está buscando
     * @return null si no existe ningun metodoDePago con el nombre del argumento.
     * Si existe alguna devuelve el primer.
     */
    public MetodoDePagoEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando metodoDePago por nombre ", name);
        TypedQuery query = em.createQuery("Select e From MetodoDePagoEntity e where e.nombre = :name", MetodoDePagoEntity.class);
        //TODO revisar esta parte
        query = query.setParameter("name", name);
        List<MetodoDePagoEntity> sameName = query.getResultList();
        MetodoDePagoEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar metodoDePago por nombre ", name);
        return result;
    }
    
}
