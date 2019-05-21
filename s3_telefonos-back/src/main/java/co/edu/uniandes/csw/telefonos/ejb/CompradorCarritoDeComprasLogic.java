/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.entities.ListaDeDeseosEntity;
import co.edu.uniandes.csw.telefonos.persistence.CarritoDeComprasPersistence;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import co.edu.uniandes.csw.telefonos.persistence.ListaDeDeseosPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Babativa
 */
@Stateless
public class CompradorCarritoDeComprasLogic {
    
     @Inject 
    private CarritoDeComprasPersistence carroPersistence;
    
    @Inject
    private CompradorPersistence compradorPersistence;
    
    
    /**
     * Borrar un carrito de compras de un comprador con u id dado
     *
     * @param compradorId El comprador al que se le desea borrar el carrito de compras
     */
    public void removeCarritoDeCompras (Long compradorId) {

        CompradorEntity compradorEntity = compradorPersistence.find(compradorId);       
        CarritoDeComprasEntity carroEntity = carroPersistence.find(compradorEntity.getCarritoDeCompras().getId());
        carroPersistence.delete(compradorEntity.getListaDeDeseos().getId());
        compradorEntity.setListaDeDeseos(null);
        carroEntity.setComprador(null);
        
}
    
    
    
}
