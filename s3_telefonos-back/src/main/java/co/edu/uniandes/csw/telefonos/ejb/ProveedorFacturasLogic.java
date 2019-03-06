/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.FacturaPersistence;
import co.edu.uniandes.csw.telefonos.persistence.ProveedorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Stateless
public class ProveedorFacturasLogic {
    
    @Inject
    private ProveedorPersistence proveedorPersistence;
    
    @Inject
    private FacturaPersistence facturaPersistence;
    
    
    /**
     * Agregar una factura al proveedor
     *
     * @param proveedorId El id del proveedor en el cual se va a guardar la factura
     * @param facturaId El id de la factura que se va a guardar
     * @return La factura creada.
     */
    public FacturaEntity addFactura(Long proveedorId, Long facturaId) {
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        FacturaEntity facturaEntity = facturaPersistence.find(facturaId);
        List<FacturaEntity> facturas = proveedorEntity.getFacturasDeVenta();
        facturas.add(facturaEntity);
        proveedorEntity.setFacturasDeVenta(facturas);
        return facturaEntity;
    }
    
    /**
     * Retorna todas las facturas asociadas a un proveedor
     *
     * @param proveedorId El ID del proveedor buscado
     * @return La lista de facturas del proveedor
     */
    public List<FacturaEntity> getFacturas(Long proveedorId) {
        return proveedorPersistence.find(proveedorId).getFacturasDeVenta();
    }
    
    /**
     * Retorna una factura asociada a un proveedor
     *
     * @param proveedorId El id del proveedor a buscar.
     * @param facturaId El id de la factura a buscar
     * @return La factura encontrada dentro del proveedor.
     * @throws BusinessLogicException Si la factura no esta asociada al proveedor
     */
    public FacturaEntity getFactura(Long proveedorId, Long facturaId) throws BusinessLogicException {
        List<FacturaEntity> facturas = proveedorPersistence.find(proveedorId).getFacturasDeVenta();
        FacturaEntity facturaEntity = facturaPersistence.find(facturaId);
        int index = facturas.indexOf(facturaEntity);
        if (index >= 0) {
            return facturas.get(index);
        }
        throw new BusinessLogicException("La factura no esta asociada al proveedor");
    }

    
}
