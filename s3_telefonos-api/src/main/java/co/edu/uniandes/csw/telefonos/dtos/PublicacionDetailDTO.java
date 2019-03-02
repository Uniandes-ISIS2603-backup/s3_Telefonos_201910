/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import co.edu.uniandes.csw.telefonos.exceptions.BusinessLogicException;
import java.io.Serializable;

/**
 *
 * @author rj.gonzalez10
 */
public class PublicacionDetailDTO extends PublicacionDTO implements Serializable {
    //celular si la posee
    public CelularDTO celular;
    //tablet si la posee
    public TabletDTO tablet;
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
}
