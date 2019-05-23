/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.ejb;

import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.telefonos.persistence.ProveedorPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniel Babativa, Andres Daza, Rodrigo Gonzalez y Laura Prieto
 */
@Stateless
public class ProveedorLogic {

    @Inject
    private ProveedorPersistence persistencia;

    /**
     * Crea un nuevo proveedor con la informacion por parametro
     *
     * @param proveedor Informacion del proveedor que se va a crear
     * @return Proveedor creado
     * @throws BusinessLogicException Si el proveedor a persistir ya existe.
     */
    public ProveedorEntity createProveedor(ProveedorEntity proveedor) throws BusinessLogicException {

        //El usuario de cada Proveedor debe ser unico
        if (persistencia.findByUsername(proveedor.getUsuario()) != null) {
            throw new BusinessLogicException("Ya existe un Proveedor con el usuario \"" + proveedor.getUsuario() + "\"");
        }

        //El correo de cada Proveedor debe ser unico
        if (persistencia.findByEmail(proveedor.getCorreoElectronico()) != null) {
            throw new BusinessLogicException("Ya existe un Proveedor con el correo \"" + proveedor.getCorreoElectronico() + "\"");
        }

        proveedor = persistencia.create(proveedor);
        return proveedor;
    }

    /**
     *
     * Obtener todos los proveedores existentes en la base de datos.
     *
     * @return una lista de proveedores.
     */
    public List<ProveedorEntity> getProveedores() {
        List<ProveedorEntity> proveedores = persistencia.findAll();
        return proveedores;
    }

    /**
     *
     * Obtener un proveedor por medio de su id.
     *
     * @param proveedorId: id del proveedor para ser buscado.
     * @return el proveedor solicitado por medio de su id.
     */
    public ProveedorEntity getProveedor(Long proveedorId) {
        ProveedorEntity proveedorEntity = persistencia.find(proveedorId);
        return proveedorEntity;
    }
    
          /**
     *
     * Obtener un proveedor por medio de su usuario
     *
     * @param proveedorUser: usuario del proveedor para ser buscado.
     * @return el proveedor solicitado por medio de su id.
     */
    public ProveedorEntity getProveedorUsuario(String username) {
       ProveedorEntity proveedorEntity = persistencia.findByUsername(username);
        return proveedorEntity;
    }

    /**
     *
     * Actualizar un proveedor.
     *
     * @param proveedorId: id del proveedor para buscarlo en la base de datos.
     * @param proveedorEntity: proveedor con los cambios para ser actualizado,
     * por ejemplo el usuario.
     * @return el proveedor con los cambios actualizados en la base de datos.
     */
    public ProveedorEntity updateProveedor(Long proveedorId, ProveedorEntity proveedorEntity) throws BusinessLogicException {

        //El usuario de cada proveedor debe ser unico.
        if (persistencia.findByUsername(proveedorEntity.getUsuario()) != null) {
            if (!(persistencia.findByUsername(proveedorEntity.getUsuario()).getId() == proveedorEntity.getId())) {
                throw new BusinessLogicException("Ya existe un Proveedor con el usuario \"" + proveedorEntity.getUsuario() + "\"");
            }
        }

        //El correo de cada Proveedor debe ser unico
        if (persistencia.findByEmail(proveedorEntity.getCorreoElectronico()) != null) {
            if (!(persistencia.findByEmail(proveedorEntity.getCorreoElectronico()).getId() == proveedorEntity.getId())) {
                throw new BusinessLogicException("Ya existe un Proveedor con el correo \"" + proveedorEntity.getCorreoElectronico() + "\"");
            }
        }

        ProveedorEntity newEntity = persistencia.update(proveedorEntity);
        return newEntity;
    }

    /**
     * Borrar un proveedor
     *
     * @param proveedorId: id del Proveedor a borrar
     */
    public void deleteProveedor(Long proveedorId) {
        persistencia.delete(proveedorId);
    }
}
