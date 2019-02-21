/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Stateless
public class CompradorPersistence {

    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;

    /**
     * Crea un comprador en la base de datos
     *
     * @param compradorEntity Objeto comprador que se va a crear
     * @return Entidad creada con un id dado por la base de datos
     */
    public CompradorEntity create(CompradorEntity compradorEntity) {
        em.persist(compradorEntity);
        return compradorEntity;
    }

    /**
     * Busca si hay algun comprador con el id que se envia por parametro
     *
     * @param compradorId Identificador correspondiente al comprador buscado
     * @return Un comprador
     */
    public CompradorEntity find(Long compradorId) {
        return em.find(CompradorEntity.class, compradorId);
    }

    /**
     * Devuelve todos los compradores de la base de datos
     *
     * @return Lista con todos los compradores que encuentre en la base de datos
     */
    public List<CompradorEntity> findAll() {
        TypedQuery<CompradorEntity> query = em.createQuery("select u from CompradorEntity u", CompradorEntity.class);
        return query.getResultList();
    }

    /**
     * Actualiza un comprador
     *
     * @param compradorEntity Comprador que viene con los nuevos cambios
     * @return Comprador con los cambios aplicados
     */
    public CompradorEntity update(CompradorEntity compradorEntity) {
        return em.merge(compradorEntity);
    }

    /**
     * Elimina un comprador de la base de datos recibiendo como parametro el id
     * del comprador
     *
     * @param compradorId Identificador del comprador a eliminar
     */
    public void delete(Long compradorId) {
        CompradorEntity compradorEntity = em.find(CompradorEntity.class, compradorId);
        em.remove(compradorEntity);
    }

    /**
     * Busca un comprador por su usuario
     * @param usuario Usuario que se quiere buscar
     * @return Comprador con el usuario dado
     */
    public CompradorEntity findByUsuario(String usuario) {
        TypedQuery query = em.createQuery("Select e From CompradorEntity e where e.usuario = :usuario", CompradorEntity.class);

        query = query.setParameter("usuario", usuario);

        List<CompradorEntity> sameUsuario = query.getResultList();
        CompradorEntity result;
        if (sameUsuario == null) {
            result = null;
        } else if (sameUsuario.isEmpty()) {
            result = null;
        } else {
            result = sameUsuario.get(0);
        }

        return result;
    }
    
    /**
     * Busca un comprador por su correo
     * @param correo Correo que se quiere buscar
     * @return Comprador con el correo dado
     */
    public CompradorEntity findByCorreo(String correo) {
        TypedQuery query = em.createQuery("Select e From CompradorEntity e where e.correoElectronico = :correoElectronico", CompradorEntity.class);

        query = query.setParameter("correoElectronico", correo);

        List<CompradorEntity> sameUsuario = query.getResultList();
        CompradorEntity result;
        if (sameUsuario == null) {
            result = null;
        } else if (sameUsuario.isEmpty()) {
            result = null;
        } else {
            result = sameUsuario.get(0);
        }

        return result;
    }

}
