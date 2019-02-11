/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import java.io.Serializable;
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
    //private List<MetodoDePago> metodosDePago;
    
    /*
    Metodo constructor
    */
    public CompradorDetailDTO(){
        
        
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
    
}
