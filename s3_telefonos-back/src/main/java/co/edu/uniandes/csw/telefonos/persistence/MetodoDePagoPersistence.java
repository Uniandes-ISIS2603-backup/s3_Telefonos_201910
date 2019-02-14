/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
