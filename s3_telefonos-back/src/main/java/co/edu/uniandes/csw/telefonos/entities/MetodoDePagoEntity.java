/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author rj.gonzalez10
 */
@Entity
public class MetodoDePagoEntity extends BaseEntity implements Serializable{
    //nombre del metodo de pago
    private String nombre;
    //nombre del banco
    private String banco;
    //tipo de metodo de pago
    private String tipo;
    //fecha de expiración metodo de pago
    private Date fecha;
    //código de verificación metodo de pago
    private Integer codigoVerificacion;
    //Comprador dueño del método
    @PodamExclude
    @ManyToOne
    private CompradorEntity comprador;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String pNombre) {
        this.nombre = pNombre;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String pBanco) {
        this.banco = pBanco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String pTipo) {
        this.tipo = pTipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date pFecha) {
        this.fecha = pFecha;
    }

    public int getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(Integer pCodigoVerificacion) {
        this.codigoVerificacion = pCodigoVerificacion;
    }

    public CompradorEntity getComprador() {
        return comprador;
    }

    public void setComprador(CompradorEntity comprador) {
        this.comprador = comprador;
    }
    
}
