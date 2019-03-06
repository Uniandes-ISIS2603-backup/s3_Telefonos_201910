/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.FacturaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Valentina Prieto Jiménez
 */
@Stateless
public class FacturaLogic {

    @Inject
    private FacturaPersistence persistencia;

    /**
     * Crea una nueva factura con la informacion por parametro
     *
     * @param factura Informacion de la factura que se va a crear
     * @return Factura creada
     * @throws BusinessLogicException Si la factura a persistir ya existe.
     */
    public FacturaEntity createFactura(FacturaEntity factura) throws BusinessLogicException {

        //La referencia de cada factura debe ser unica
        if (persistencia.findByReferencia(factura.getReferencia()) != null) {
            throw new BusinessLogicException("Ya existe una Factura con la referencia \"" + factura.getReferencia() + "\"");
        }
        if (factura.getComprador() == null) {
            throw new BusinessLogicException("No se puede crear una factura sin comprador");
        }
        if (factura.getProveedor() == null) {
            throw new BusinessLogicException("No se puede crear una factura sin proveedor");
        }
        if (factura.getPublicacion() == null) {
            throw new BusinessLogicException("No se puede crear una factura sin publicacion");
        }

        factura = persistencia.create(factura);
        return factura;
    }

    /**
     *
     * Obtener todas los facturas existentes en la base de datos.
     *
     * @return una lista de facturas.
     */
    public List<FacturaEntity> getFacturas() {
        List<FacturaEntity> facturas = persistencia.findAll();
        return facturas;
    }

    /**
     *
     * Obtener una factura por medio de su id.
     *
     * @param facturaId: id de la factura para ser buscada.
     * @return la factura solicitada por medio de su id.
     */
    public FacturaEntity getFactura(Long facturaId) {
        FacturaEntity facturaEntity = persistencia.find(facturaId);
        return facturaEntity;
    }

    /**
     *
     * Actualizar una factura.
     *
     * @param facturaId: id de la factura para buscarla en la base de datos.
     * @param facturaEntity: factura con los cambios para ser actualizada, por
     * ejemplo la referencia.
     * @return la factura con los cambios actualizados en la base de datos.
     */
    public FacturaEntity updateFactura(Long facturaId, FacturaEntity facturaEntity) throws BusinessLogicException {

        //La referencia de cada factura debe ser unica
        if (persistencia.findByReferencia(facturaEntity.getReferencia()) != null) {
            throw new BusinessLogicException("Ya existe una Factura con la referencia \"" + facturaEntity.getReferencia() + "\"");
        }
        if (facturaEntity.getComprador() == null) {
            throw new BusinessLogicException("No se puede actualizar una factura sin comprador");
        }
        if (facturaEntity.getProveedor() == null) {
            throw new BusinessLogicException("No se puede actualizar una factura sin proveedor");
        }
        if (facturaEntity.getPublicacion() == null) {
            throw new BusinessLogicException("No se puede actualizar una factura sin publicacion");
        }
        FacturaEntity newEntity = persistencia.update(facturaEntity);
        return newEntity;
    }

    /**
     * Borrar una factura
     *
     * @param facturaId: id de la factura a borrar
     */
    public void deleteFactura(Long facturaId) {
        persistencia.delete(facturaId);
    }
}
