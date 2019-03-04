/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import co.edu.uniandes.csw.telefonos.entities.FacturaEntity;
import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
public class CompradorDetailDTO extends CompradorDTO implements Serializable{
    
    
    /*
    Lista de facturas asociadas con un comprador
    */
    private List<FacturaDTO> facturasDeCompra;
    
    /*
    Metodos de pago asociados con un comprador
    */
    private List<MetodoDePagoDTO> metodosDePago;
    
    /*
    Metodo constructor
    */
    public CompradorDetailDTO(){
        super();
    }
    
    /**
     * Crea un objeto CompradorDetailDTO a partir de un objeto CompradorEntity
     * incluyendo los atributos de CompradorDTO.
     *
     * @param compradorEntity Entidad CompradorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public CompradorDetailDTO(CompradorEntity compradorEntity) {
        super(compradorEntity);
        if (compradorEntity != null) {
            facturasDeCompra = new ArrayList<>();
            for (FacturaEntity entityFacturas : compradorEntity.getFacturasDeCompra()) {
                facturasDeCompra.add(new FacturaDTO(entityFacturas));
            }
            
            metodosDePago = new ArrayList();
            for (MetodoDePagoEntity entityMetodo : compradorEntity.getMetodosDePago()) {
                metodosDePago.add(new MetodoDePagoDTO(entityMetodo));
            }
        }
    }

    /**
     * @return the facturasDeCompra
     */
    public List<FacturaDTO> getFacturasDeCompra() {
        return facturasDeCompra;
    }

    /**
     * @param facturasDeCompra the facturasDeCompra to set
     */
    public void setFacturasDeCompra(List<FacturaDTO> facturasDeCompra) {
        this.facturasDeCompra = facturasDeCompra;
    }

    /**
     * @return the metodosDePago
     */
    public List<MetodoDePagoDTO> getMetodosDePago() {
        return metodosDePago;
    }

    /**
     * @param metodosDePago the metodosDePago to set
     */
    public void setMetodosDePago(List<MetodoDePagoDTO> metodosDePago) {
        this.metodosDePago = metodosDePago;
    }
    
}
