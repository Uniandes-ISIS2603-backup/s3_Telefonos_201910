/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import co.edu.uniandes.csw.telefonos.persistence.MetodoDePagoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Stateless
public class CompradorMetodosDePagoLogic {
    
    @Inject
    private CompradorPersistence compradorPersistence;
    
    @Inject
    private MetodoDePagoPersistence metodoDePagoPersistence;
    
    
    /**
     * Agregar un metodo de pago al comprador
     *
     * @param compradorId El id del comprador en el cual se va a guardar el metodo de pago
     * @param metodoDePagoId El id del metodo de pago que se va a guardar
     * @return El metodo de pago creado.
     */
    public MetodoDePagoEntity addMetodoDePago(Long metodoDePagoId, Long compradorId) {
        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);
        MetodoDePagoEntity metodoDePagoEntity = metodoDePagoPersistence.find(metodoDePagoId);
        List<MetodoDePagoEntity> metodosDePago = compradorEntity.getMetodosDePago();
        metodosDePago.add(metodoDePagoEntity);
        compradorEntity.setMetodosDePago(metodosDePago);
        return metodoDePagoEntity;
    }
    
    /**
     * Retorna todos los metodos de pago asociados a un comprador
     *
     * @param compradorId El ID del comprador buscado
     * @return La lista de metodos de pago del comprador
     */
    public List<MetodoDePagoEntity> getMetodosDePago(Long compradorId) {
        return compradorPersistence.find(compradorId).getMetodosDePago();
    }
    
    /**
     * Retorna un metodo de pago asociado a un comprador
     *
     * @param compradorId El id del comprador a buscar.
     * @param metodoDePagoId El id del metodo de pago a buscar
     * @return El metodo de pago encontrado dentro del comprador.
     * @throws BusinessLogicException Si el metodo de pago no esta asociado al comprador
     */
    public MetodoDePagoEntity getMetodoDePago(Long compradorId, Long metodoDePagoId) throws BusinessLogicException {
        List<MetodoDePagoEntity> facturas = compradorPersistence.find(compradorId).getMetodosDePago();
        MetodoDePagoEntity facturaEntity = metodoDePagoPersistence.find(metodoDePagoId);
        int index = facturas.indexOf(facturaEntity);
        if (index >= 0) {
            return facturas.get(index);
        }
        throw new BusinessLogicException("El metodo de pago no esta asociado al comprador");
    }

    
}
