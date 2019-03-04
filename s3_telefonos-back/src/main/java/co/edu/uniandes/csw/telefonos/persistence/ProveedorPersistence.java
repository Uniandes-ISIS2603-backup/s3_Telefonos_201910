/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.entities.TabletEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Andres Daza, Laura Prieto
 */
@Stateless
public class ProveedorPersistence {
    private final static Logger LOGGER = Logger.getLogger(TabletPersistence.class.getName());

    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;

    public ProveedorEntity create(ProveedorEntity proveedorEntity) {

        LOGGER.log(Level.INFO, "Creando un proveedor nuevo");
        em.persist(proveedorEntity);

        LOGGER.log(Level.INFO, "Saliendo de crear un proveedor nuevo");

        return proveedorEntity;
    }

    public ProveedorEntity find(Long proveedorId) {
        return em.find(ProveedorEntity.class, proveedorId);
    }
    
    public List<ProveedorEntity> findAll(){
        
        TypedQuery<ProveedorEntity> query = em.createQuery("select u from ProveedorEntity u", ProveedorEntity.class);
        return query.getResultList();
    }
    
    public ProveedorEntity update(ProveedorEntity proveedorEntity){
        LOGGER.log(Level.INFO, "Actualizando proveedor con id ={0}", proveedorEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar proveedor con id ={0}", proveedorEntity.getId());
        return em.merge(proveedorEntity);
    }
    
    public void delete(Long proveedorId){
        LOGGER.log(Level.INFO, "Borrando tableta con id ={0}", proveedorId);
        ProveedorEntity entity = em.find(ProveedorEntity.class, proveedorId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Borrando proveedor con id ={0}", proveedorId);
    }
    
    public ProveedorEntity findByUsername(String pUsername){
        LOGGER.log(Level.INFO, "Buscando proveedor por usuario  ", pUsername);    
        TypedQuery query=em.createQuery("Select e From ProveedorEntity e where e.usuario = :usuario", ProveedorEntity.class);
        query = query.setParameter("usuario", pUsername);
        List<ProveedorEntity> mismoUsuario = query.getResultList();
        ProveedorEntity resultado;
        if(mismoUsuario == null){
            resultado=null;
        }else if(mismoUsuario.isEmpty()){
            resultado = null;
        }else{
            resultado=mismoUsuario.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar proveedor con usuario ", pUsername);     
        return resultado;
    }
    
    public ProveedorEntity findByEmail(String pCorreo){
        LOGGER.log(Level.INFO, "Buscando proveedor por correo  ", pCorreo);    
        TypedQuery query=em.createQuery("Select e From ProveedorEntity e where e.correoElectronico = :correoElectronico", ProveedorEntity.class);
        query = query.setParameter("correoElectronico", pCorreo);
        List<ProveedorEntity> mismoCorreo = query.getResultList();
        ProveedorEntity resultado;
        if(mismoCorreo == null){
            resultado=null;
        }else if(mismoCorreo.isEmpty()){
            resultado = null;
        }else{
            resultado=mismoCorreo.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar proveedor con correo ", pCorreo);     
        return resultado;
    }
}
