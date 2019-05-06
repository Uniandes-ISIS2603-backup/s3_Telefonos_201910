/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.MetodoDePagoEntity;
import java.io.Serializable;
import java.util.Date;
import java.util.logging.Level;
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
    private Integer codigoVerificacion;                                 
    //id
    private Long id;
    //Comprador
    private CompradorDTO comprador;
    
    public MetodoDePagoDTO(){
    
    }

    public MetodoDePagoDTO(MetodoDePagoEntity metodoEntity) {
        if(metodoEntity != null){
        this.nombre = metodoEntity.getNombre();
        this.banco = metodoEntity.getBanco();
        this.tipo = metodoEntity.getTipo();
        this.fecha = metodoEntity.getFecha();
        this.codigoVerificacion = metodoEntity.getCodigoVerificacion();
        this.id = metodoEntity.getId();
        if(metodoEntity.getComprador()!=null){
        this.comprador = new CompradorDTO(metodoEntity.getComprador());
        }
        }
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

    public Integer getCodigoVerificacion() {
        return codigoVerificacion;
    }

    public void setCodigoVerificacion(Integer codigoVerificacion) {
        this.codigoVerificacion = codigoVerificacion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public CompradorDTO getComprador() {
        return comprador;
    }

    public void setComprador(CompradorDTO comprador) {
        this.comprador = comprador;
    }
    
    public MetodoDePagoEntity toEntity()
    {
        MetodoDePagoEntity entidad = new MetodoDePagoEntity();
        entidad.setNombre(this.nombre);
        entidad.setBanco(this.banco);
        entidad.setFecha(this.fecha);
        entidad.setTipo(this.tipo);
        entidad.setCodigoVerificacion(this.codigoVerificacion);
        entidad.setId(this.id);
        if(this.getComprador() != null){
        entidad.setComprador(this.getComprador().toEntity() );}
        return entidad;
    }
      @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    
}
