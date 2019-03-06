/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.SeguroPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */
@Stateless
public class SeguroLogic {

    @Inject
    private SeguroPersistence persistence;

    private static final Logger LOGGER = Logger.getLogger(SeguroLogic.class.getName());

    public SeguroEntity createSeguro(SeguroEntity seguro) throws BusinessLogicException {
        if (seguro.getProveedor() == null) {
            throw new BusinessLogicException("No se puede crear un seguro sin proveedor");
        }
        seguro = persistence.create(seguro);
        return seguro;
    }

    public List<SeguroEntity> getSeguros() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los seguros");
        List<SeguroEntity> seguros = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los seguros");
        return seguros;
    }

    public SeguroEntity getSeguro(Long seguroId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el seguro con id = {0}", seguroId);
        SeguroEntity seguro = persistence.find(seguroId);
        if (seguro == null) {
            LOGGER.log(Level.SEVERE, "El seguro con identificador = {0} no existe", seguroId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el seguro con identificador = {0}", seguroId);
        return seguro;
    }

    public SeguroEntity updateSeguro(Long seguroId, SeguroEntity seguroEntity) throws BusinessLogicException {
        if (seguroEntity.getProveedor() == null) {
            throw new BusinessLogicException("No se puede actualizar un seguro sin proveedor");
        }
        SeguroEntity newEntity = persistence.update(seguroEntity);
        return newEntity;
    }

    public void deleteSeguro(Long seguroId) {
        persistence.delete(seguroId);
    }

}
