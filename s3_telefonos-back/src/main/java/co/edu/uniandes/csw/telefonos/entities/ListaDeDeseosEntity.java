/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Andres Felipe Daza Diaz
 */
@Entity
public class ListaDeDeseosEntity extends BaseEntity implements Serializable{
    
    @PodamExclude
    @OneToMany(mappedBy = "listaDeDeseos")
    private List<TabletEntity> tablets = new ArrayList<TabletEntity>();
    
    //@PodamExclude
    //@OneToMany(mappedBy = "listaDeDeseos")
    //private List<CelularEntity> celulares = new ArrayList<CelularEntity>();
    
    private double costoEstimado;

    public ListaDeDeseosEntity(){
        
    }
    
    
    /**
     * @return the tablets
     */
    public List<TabletEntity> getTablets() {
        return tablets;
    }

    /**
     * @param tablets the tablets to set
     */
    public void setTablets(List<TabletEntity> tablets) {
        this.tablets = tablets;
    }

    /**
     * @return the costoEstimado
     */
    public double getCostoEstimado() {
        return costoEstimado;
    }

    /**
     * @param costoEstimado the costoEstimado to set
     */
    public void setCostoEstimado(double costoEstimado) {
        this.costoEstimado = costoEstimado;
    }
    
}
