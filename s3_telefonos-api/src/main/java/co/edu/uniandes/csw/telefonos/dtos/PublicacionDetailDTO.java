/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.entities.ProveedorEntity;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rj.gonzalez10
 */
public class PublicacionDetailDTO extends PublicacionDTO implements Serializable {
    //celular si la posee
    public CelularDTO celular;
    //tablet si la posee
    public TabletDTO tablet;
    //Carrito de compras
    private List<CarritoDeComprasDTO> carritoDeCompras;
    //factura
    private FacturaDTO factura;
    //proveedor
    private ProveedorDTO proveedor;
    public PublicacionDetailDTO(){
    
}
    
    public PublicacionDetailDTO(PublicacionEntity publicacionEntity ) throws BusinessLogicException{
       super(publicacionEntity); 
       if(publicacionEntity != null){
           if(publicacionEntity.getCelular()!= null && publicacionEntity.getTablet()== null ){
               CelularDTO pCelular = new CelularDTO(publicacionEntity.getCelular());
               this.celular = pCelular;
           }
           if(publicacionEntity.getCelular()== null && publicacionEntity.getTablet()!= null ){
               TabletDTO pTablet = new TabletDTO(publicacionEntity.getTablet());
               this.tablet = pTablet;
           }
           this.factura = new FacturaDTO(publicacionEntity.getFactura());
           this.proveedor = new ProveedorDTO(publicacionEntity.getProveedor());
           carritoDeCompras = new ArrayList<CarritoDeComprasDTO>();
              for (CarritoDeComprasEntity entityCarritoDeCompras : publicacionEntity.getCarritoDeCompras()) {
                carritoDeCompras.add(new CarritoDeComprasDTO(entityCarritoDeCompras));
            }
       }
       else{
           throw new BusinessLogicException();
                   }
         
    }

    public CelularDTO getCelular() {
        return celular;
    }

    public void setCelular(CelularDTO celular) {
        this.celular = celular;
    }

    public TabletDTO getTablet() {
        return tablet;
    }

    public void setTablet(TabletDTO tablet) {
        this.tablet = tablet;
    }

    public List<CarritoDeComprasDTO> getCarritoDeCompras() {
        return carritoDeCompras;
    }

    public void setCarritoDeCompras(List<CarritoDeComprasDTO> carritoDeCompras) {
        this.carritoDeCompras = carritoDeCompras;
    }

    public FacturaDTO getFactura() {
        return factura;
    }

    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

    public ProveedorDTO getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorDTO proveedor) {
        this.proveedor = proveedor;
    }
     @Override
     public PublicacionEntity toEntity()
     { 
         PublicacionEntity publicacionEntity = super.toEntity();
          if(celular != null)
            {  
                  publicacionEntity.setCelular(this.celular.toEntity());
            }
                
           if(tablet != null)
            {  
                  publicacionEntity.setTablet(this.tablet.toEntity());
            }
                
        
          if (carritoDeCompras != null) {
            List<CarritoDeComprasEntity> carritosEntity = new ArrayList<>();
            for (CarritoDeComprasDTO dtoCarritoDeCompra : carritoDeCompras) {
                carritosEntity.add(dtoCarritoDeCompra.toEntity());
            }
              publicacionEntity.setCarritoDeCompras(carritosEntity);
             
        }
          if(proveedor != null)
            {  
                  publicacionEntity.setProveedor(this.proveedor.toEntity());
            }
          if(factura != null)
            {  
                  publicacionEntity.setFactura(this.factura.toEntity());
            }
                
         return publicacionEntity;
     }
}
