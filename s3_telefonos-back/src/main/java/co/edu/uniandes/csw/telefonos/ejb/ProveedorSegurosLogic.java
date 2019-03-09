/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.entities.SeguroEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.ProveedorPersistence;
import co.edu.uniandes.csw.telefonos.persistence.SeguroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Stateless
public class ProveedorSegurosLogic {
    
    @Inject
    private ProveedorPersistence proveedorPersistence;
    
    @Inject
    private SeguroPersistence seguroPersistence;
    
    
    /**
     * Agregar un seguro al proveedor
     *
     * @param proveedorId El id del proveedor en el cual se va a guardar el seguro
     * @param seguroId El id del seguro que se va a guardar
     * @return Seguro creado.
     */
    public SeguroEntity addSeguro( Long seguroId, Long proveedorId) {
        ProveedorEntity proveedorEntity = proveedorPersistence.find(proveedorId);
        SeguroEntity seguroEntity = seguroPersistence.find(seguroId);
        List<SeguroEntity> seguros = proveedorEntity.getSeguros();
        seguros.add(seguroEntity);
        proveedorEntity.setSeguros(seguros);
        return seguroEntity;
    }
    
    /**
     * Retorna todos los seguros asociados a un proveedor
     *
     * @param proveedorId El ID del proveedor buscado
     * @return La lista de seguros del proveedor
     */
    public List<SeguroEntity> getSeguros(Long proveedorId) {
        return proveedorPersistence.find(proveedorId).getSeguros();
    }
    
    /**
     * Retorna un seguro asociado a un proveedor
     *
     * @param proveedorId El id del proveedor a buscar.
     * @param seguroId El id del seguro a buscar
     * @return El seguro encontrada dentro del proveedor.
     * @throws BusinessLogicException Si el seguro no esta asociado al proveedor
     */
    public SeguroEntity getSeguro(Long proveedorId, Long seguroId) throws BusinessLogicException {
        List<SeguroEntity> seguros = proveedorPersistence.find(proveedorId).getSeguros();
        SeguroEntity seguroEntity = seguroPersistence.find(seguroId);
        int index = seguros.indexOf(seguroEntity);
        if (index >= 0) {
            return seguros.get(index);
        }
        throw new BusinessLogicException("El seguro no esta asociado al proveedor");
    }

    
}
