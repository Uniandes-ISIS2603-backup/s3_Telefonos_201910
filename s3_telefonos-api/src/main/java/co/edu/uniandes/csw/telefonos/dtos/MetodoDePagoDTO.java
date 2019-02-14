/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import java.io.Serializable;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author rj.gonzalez10
 */
public class MetodoDePagoDTO implements Serializable{
     //nombre del metodo de pago
    private String nombre;
    //nombre del banco
    private String banco;
    //tipo de metodo de pago
    private String tipo;
    //fecha de expiración metodo de pago
    private Date fecha;
    //código de verificación metodo de pago
    private int codigoVerificacion;
    //id
    private Long id;
    
    public MetodoDePagoDTO(){
    
    }

    public MetodoDePagoDTO(MetodoDePagoEntity metodoEntity) {
        this.nombre = metodoEntity.getNombre();
        this.banco = metodoEntity.getBanco();
        this.tipo = metodoEntity.getTipo();
        this.fecha = metodoEntity.getFecha();
        this.codigoVerificacion = metodoEntity.getCodigoVerificacion();
        this.id = metodoEntity.getId();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(int codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public MetodoDePagoEntity toEntity()
    {
        MetodoDePagoEntity entidad = new MetodoDePagoEntity();
        entidad.setNombre(this.nombre);
        entidad.setBanco(this.banco);
        entidad.setFecha(this.fecha);
        entidad.setTipo(this.tipo);
        entidad.setFecha(this.fecha);
        return entidad;
    }
      @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    
}
