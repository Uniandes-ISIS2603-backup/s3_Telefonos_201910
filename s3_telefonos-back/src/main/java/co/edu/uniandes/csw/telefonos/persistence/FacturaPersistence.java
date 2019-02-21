/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.persistence;

import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
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
public class FacturaPersistence {

    @PersistenceContext(unitName = "telefonosPU")
    protected EntityManager em;

    /**
     * Crea una factura en la base de datos
     * @param facturaEntity Objeto factura que se va a crear
     * @return Entidad creada con un id dado por la base de datos
     */
    public FacturaEntity create(FacturaEntity facturaEntity) {
        em.persist(facturaEntity);
        return facturaEntity;
    }

    /**
     * Busca si hay alguna factura con el id que se envia por parametro
     * @param facturaId Identificador correspondiente a la factura buscada
     * @return  Una factura
     */
    public FacturaEntity find(Long facturaId) {
        return em.find(FacturaEntity.class, facturaId);
    }

    /**
     * Devuelve todoas las facturas de la base de datos
     * @return Lista con todas las facturas que encuentre en la base de datos
     */
    public List< FacturaEntity> findAll() {
        TypedQuery< FacturaEntity> query = em.createQuery("select u from  FacturaEntity u", FacturaEntity.class);
        return query.getResultList();
    }
    
    /**
     * Actualiza una factura
     * @param facturaEntity Factura que viene con los nuevos cambios
     * @return Factura con los cambios aplicados
     */
    public FacturaEntity update(FacturaEntity facturaEntity) {       
        return em.merge(facturaEntity);
    }
    
    /**
     * Elimina una factura de la base de datos recibiendo como parametro el id de la factura
     * @param facturaId Identificador de la factura a eliminar
     */
    public void delete(Long facturaId) {
        FacturaEntity facturaEntity = em.find(FacturaEntity.class, facturaId);
        em.remove(facturaEntity);
    }
}
