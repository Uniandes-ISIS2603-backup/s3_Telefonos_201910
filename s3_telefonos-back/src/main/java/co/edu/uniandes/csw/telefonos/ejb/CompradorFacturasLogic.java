/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import co.edu.uniandes.csw.telefonos.persistence.FacturaPersistence;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Stateless
public class CompradorFacturasLogic {

    private static final Logger LOGGER = Logger.getLogger(CompradorFacturasLogic.class.getName());
    
    @Inject
    private CompradorPersistence compradorPersistence;

    @Inject
    private FacturaPersistence facturaPersistence;

    /**
     * Agregar una factura al comprador
     *
     * @param compradorId El id del comprador en el cual se va a guardar la
     * factura
     * @param facturaId El id de la factura que se va a guardar
     * @return La factura creada.
     */
    public FacturaEntity addFactura(Long facturaId, Long compradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de asociarle una factura al comprador con id = {0}", compradorId);
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        FacturaEntity facturaEntity = facturaPersistence.find(facturaId);
        compradorEntity.getFacturasDeCompra().add(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de asociarle una factura al comprador con id = {0}", compradorId);
        return facturaPersistence.find(facturaId);
    }

    /**
     * Retorna todas las facturas asociadas a un comprador
     *
     * @param compradorId El ID del comprador buscado
     * @return La lista de facturas del comprador
     */
    public List<FacturaEntity> getFacturas(Long compradorId) {
        return compradorPersistence.find(compradorId).getFacturasDeCompra();
    }

    /**
     * Retorna una factura asociada a un comprador
     *
     * @param compradorId El id del comprador a buscar.
     * @param facturaId El id de la factura a buscar
     * @return La factura encontrada dentro del comprador.
     * @throws BusinessLogicException Si la factura no esta asociada al
     * comprador
     */
    public FacturaEntity getFactura(Long compradorId, Long facturaId) throws BusinessLogicException {
        List<FacturaEntity> facturas = compradorPersistence.find(compradorId).getFacturasDeCompra();
        FacturaEntity facturaEntity = facturaPersistence.find(facturaId);
        int index = facturas.indexOf(facturaEntity);
        if (index >= 0) {
            return facturas.get(index);
        }
        throw new BusinessLogicException("La factura no esta asociada al comprador");
    }

}
