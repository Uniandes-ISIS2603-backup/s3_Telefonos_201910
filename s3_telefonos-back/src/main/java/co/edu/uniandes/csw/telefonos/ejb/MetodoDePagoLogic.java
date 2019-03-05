/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.MetodoDePagoPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author rj.gonzalez10
 */
@Stateless
public class MetodoDePagoLogic {
      private static final Logger LOGGER = Logger.getLogger(MetodoDePagoLogic.class.getName());

    @Inject
    private MetodoDePagoPersistence persistence; // Variable para acceder a la persistencia de la aplicación. Es una inyección de dependencias.

    /**
     * Crea una método de Pago en la persistencia.
     *
     * @param metodoDePagoEntity La entidad que representa la método de Pago a
     * persistir.
     * @return La entiddad de la método de Pago luego de persistirla.
     * @throws BusinessLogicException Si la método de Pago a persistir ya existe.
     */
    public MetodoDePagoEntity createMetodoDePago(MetodoDePagoEntity metodoDePagoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación del método de pago");
        persistence.create(metodoDePagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del método de pago");
        return metodoDePagoEntity;
    }

    /**
     *
     * Obtener todas las método de Pagoes existentes en la base de datos.
     *
     * @return una lista de métodos de Pago.
     */
    public List<MetodoDePagoEntity> getMetodosDePago() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas los método de Pagoes");
        List<MetodoDePagoEntity> metododePagos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas los método de Pagoes");
        return metododePagos;
    }

    /**
     *
     * Obtener un método de Pago por medio de su id.
     *
     * @param metodoDePagosId: id del método de Pago para ser buscada.
     * @return el método de Pago solicitada por medio de su id.
     */
    public MetodoDePagoEntity getMetodoDePago(Long metodoDePagosId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar la método de Pago con id = {0}", metodoDePagosId);
        MetodoDePagoEntity metodoDePagoEntity = persistence.find(metodoDePagosId);
        if (metodoDePagoEntity == null) {
            LOGGER.log(Level.SEVERE, "La método de Pago con el id = {0} no existe", metodoDePagosId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la método de Pago con id = {0}", metodoDePagosId);
        return metodoDePagoEntity;
    }

    /**
     *
     * Actualizar un método de Pago.
     *
     * @param metodoDePagosId: id de la método de Pago para buscarla en la base de
     * datos.
     * @param metodoDePagoEntity: método de Pago con los cambios para ser actualizada,
     * por ejemplo el nombre.
     * @return la método de Pago con los cambios actualizados en la base de datos.
     */
    public MetodoDePagoEntity updateMetodoDePago(Long metodoDePagosId, MetodoDePagoEntity metodoDePagoEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la método de Pago con id = {0}", metodoDePagosId);
        MetodoDePagoEntity newEntity = persistence.update(metodoDePagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la método de Pago con id = {0}", metodoDePagoEntity.getId());
        return newEntity;
    }

    /**
     * Borrar un método de Pago
     *
     * @param metodoDePagosId: id de la método de Pago a borrar
     * @throws BusinessLogicException Si la método de Pago a eliminar tiene libros.
     */
    public void deleteMetodoDePago(Long metodoDePagosId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la método de Pago con id = {0}", metodoDePagosId);
        persistence.delete(metodoDePagosId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la método de Pago con id = {0}", metodoDePagosId);
    }


}
