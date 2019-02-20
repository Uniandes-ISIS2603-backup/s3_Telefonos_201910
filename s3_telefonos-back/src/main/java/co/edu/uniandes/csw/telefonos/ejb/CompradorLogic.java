/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Stateless
public class CompradorLogic {
    
    @Inject
    private CompradorPersistence persistencia;
    
    public CompradorEntity createComprador (CompradorEntity comprador){
        
        //El usuario de cada comprador debe ser unico
        
        
        comprador = persistencia.create(comprador);
        return comprador;
    }
}
