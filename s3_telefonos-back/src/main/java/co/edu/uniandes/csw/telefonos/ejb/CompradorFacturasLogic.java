/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import co.edu.uniandes.csw.telefonos.persistence.FacturaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Stateless
public class CompradorFacturasLogic {
    
    @Inject
    private CompradorPersistence compradorPersistence;
    
    @Inject
    private FacturaPersistence facturaPersistence;
    
    
    /**
     * Agregar una factura al comprador
     *
     * @param compradorId El id del comprador en el cual se va a guardar la factura
     * @param facturaId El id de la factura que se va a guardar
     * @return El libro creado.
     */
    public FacturaEntity addFactura(Long compradorId, Long facturaId) {
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        FacturaEntity facturaEntity = facturaPersistence.find(facturaId);
        List<FacturaEntity> facturas = compradorEntity.getFacturasDeCompra();
        facturas.add(facturaEntity);
        compradorEntity.setFacturasDeCompra(facturas);
        return facturaEntity;
    }
}
