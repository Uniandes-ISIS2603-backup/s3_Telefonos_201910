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

    public FacturaEntity create(FacturaEntity facturaEntity) {
        em.persist(facturaEntity);
        return facturaEntity;
    }

    public FacturaEntity find(Long facturaId) {
        return em.find(FacturaEntity.class, facturaId);
    }

    public List< FacturaEntity> findAll() {
        TypedQuery< FacturaEntity> query = em.createQuery("select u from  FacturaEntity u", FacturaEntity.class);
        return query.getResultList();
    }
}
