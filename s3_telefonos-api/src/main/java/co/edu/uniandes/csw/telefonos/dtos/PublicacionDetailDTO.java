/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

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
    
    public PublicacionDetailDTO()
    {
       super(); 
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
