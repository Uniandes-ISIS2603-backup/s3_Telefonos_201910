/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.CompradorPersistence;
import java.util.List;
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
    
    /**
     * Crea un nuevo comprador con la informacion por parametro
     * @param comprador Informacion del comprador que se va a crear
     * @return Comprador creado
     * @throws BusinessLogicException Si el comprador a persistir ya existe.
     */
    public CompradorEntity createComprador (CompradorEntity comprador) throws BusinessLogicException{
        
        //El usuario de cada comprador debe ser unico
        if(persistencia.findByUsuario(comprador.getUsuario())!=null){
            throw new BusinessLogicException("Ya existe un Comprador con el usuario \"" + comprador.getUsuario() + "\"");
        }
        
        //El correo de cada comprador debe ser unico
        if(persistencia.findByCorreo(comprador.getCorreoElectronico())!=null){
            throw new BusinessLogicException("Ya existe un Comprador con el correo \"" + comprador.getCorreoElectronico() + "\"");
        }
        
        comprador = persistencia.create(comprador);
        return comprador;
    }
    
    /**
     *
     * Obtener todos los compradores existentes en la base de datos.
     *
     * @return una lista de compradores.
     */
    public List<CompradorEntity> getCompradores() {
       List<CompradorEntity> editorials = persistencia.findAll();
        return editorials;
    }
    
    /**
     *
     * Obtener un comprador por medio de su id.
     *
     * @param compradorId: id del comprador para ser buscado.
     * @return el comprador solicitado por medio de su id.
     */
    public CompradorEntity getComprador(Long compradorId) {
       CompradorEntity compradorEntity = persistencia.find(compradorId);
        return compradorEntity;
    }
    
    /**
     *
     * Actualizar un comprador.
     *
     * @param compradorId: id del comprador para buscarlo en la base de
     * datos.
     * @param compradorEntity: comprador con los cambios para ser actualizado,
     * por ejemplo el usuario.
     * @return el comprador con los cambios actualizados en la base de datos.
     */
    public CompradorEntity updateComprador(Long compradorId, CompradorEntity compradorEntity) throws BusinessLogicException {
        
        //El usuario de cada comprador debe ser unico
        if(persistencia.findByUsuario(compradorEntity.getUsuario())!=null){
            throw new BusinessLogicException("Ya existe un Comprador con el usuario \"" + compradorEntity.getUsuario() + "\"");
        }
        
        //El correo de cada comprador debe ser unico
        if(persistencia.findByCorreo(compradorEntity.getCorreoElectronico())!=null){
            throw new BusinessLogicException("Ya existe un Comprador con el correo \"" + compradorEntity.getCorreoElectronico() + "\"");
        }
        
       CompradorEntity newEntity = persistencia.update(compradorEntity);
        return newEntity;
    }
    
    /**
     * Borrar un comprador
     *
     * @param compradorId: id del comprador a borrar
     */
    public void deleteComprador(Long compradorId) {
        persistencia.delete(compradorId);
    }
}
