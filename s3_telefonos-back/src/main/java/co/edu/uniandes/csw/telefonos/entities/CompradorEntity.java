/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
@Entity
public class CompradorEntity extends BaseEntity implements Serializable{
    
    /*
    Usuario con que se registra el comprador
    */
    private String usuario;
    
    /*
    Contrasenia con que se registra el comprador
    */
    private String contrasenia;
    
    /*
    Apodo (nickname) con que se registra el comprador
    */
    private String apodo;
    
    /*
    Correo con que se registra el comprador
    */
    private String correoElectronico;
    
     /*
    Carrito de compras del comprador
    */
    @PodamExclude
    @OneToOne(mappedBy = "comprador", fetch = FetchType.LAZY)
    private CarritoDeComprasEntity carritoDeCompras;
    
    /*
    Lista de deseos del comprador
    */
    @PodamExclude
    @OneToOne(mappedBy = "comprador", fetch = FetchType.LAZY)
    private ListaDeDeseosEntity listaDeDeseos;
    
    /*
    Lista de facturas asociadas con un comprador
    */ 
    @PodamExclude
    @OneToMany(mappedBy = "comprador",fetch = FetchType.LAZY )
    private List<FacturaEntity> facturasDeCompra;
    
     /*
    Metodos de pago asociados con un comprador
    */
    @PodamExclude
    @OneToMany(mappedBy = "comprador",fetch = FetchType.LAZY )
    private List<MetodoDePagoEntity> metodosDePago;

    public CompradorEntity(){
        
    }
    
    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the contrasenia
     */
    public String getContrasenia() {
        return contrasenia;
    }

    /**
     * @param contrasenia the contrasenia to set
     */
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    /**
     * @return the apodo
     */
    public String getApodo() {
        return apodo;
    }

    /**
     * @param apodo the apodo to set
     */
    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    /**
     * @return the correoElectronico
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * @param correoElectronico the correoElectronico to set
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return the facturasDeCompra
     */
    public List<FacturaEntity> getFacturasDeCompra() {
        return facturasDeCompra;
    }

    /**
     * @param facturasDeCompra the facturasDeCompra to set
     */
    public void setFacturasDeCompra(List<FacturaEntity> facturasDeCompra) {
        this.facturasDeCompra = facturasDeCompra;
    }

    /**
     * @return the listaDeDeseos
     */
    public ListaDeDeseosEntity getListaDeDeseos() {
        return listaDeDeseos;
    }

    /**
     * @param listaDeDeseos the listaDeDeseos to set
     */
    public void setListaDeDeseos(ListaDeDeseosEntity listaDeDeseos) {
        this.listaDeDeseos = listaDeDeseos;
    }

    /**
     * @return the carritoDeCompras
     */
    public CarritoDeComprasEntity getCarritoDeCompras() {
        return carritoDeCompras;
    }

    /**
     * @param carritoDeCompras the carritoDeCompras to set
     */
    public void setCarritoDeCompras(CarritoDeComprasEntity carritoDeCompras) {
        this.carritoDeCompras = carritoDeCompras;
    }

    /**
     * @return the metodosDePago
     */
    public List<MetodoDePagoEntity> getMetodosDePago() {
        return metodosDePago;
    }

    /**
     * @param metodosDePago the metodosDePago to set
     */
    public void setMetodosDePago(List<MetodoDePagoEntity> metodosDePago) {
        this.metodosDePago = metodosDePago;
    }

}
