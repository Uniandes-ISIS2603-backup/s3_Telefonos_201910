/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

import co.edu.uniandes.csw.telefonos.entities.CompradorEntity;
import java.io.Serializable;

/**
 *
 * @author Laura Valentina Prieto Jimenez
 */
public class CompradorDTO implements Serializable {
    
    /*
    Usuario con que se registra el comprador
    */
    private String usuario;
    
    /*
    Contrasenia con que se registra el comprador
    */
    private String contrasenia;
    
    /*
    ID que se le asigna al comprador
    */
    private Long id;
    
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
    private CarritoDeComprasDTO carritoDeCompras;
    
    /*
    Lista de deseos del comprador
    */
    private ListaDeDeseosDTO listaDeDeseos;
   
    /*
    Metodo constructor
    */
    public CompradorDTO(){
        
    }
    
    /**
     * Crea un objeto CompradorrDTO a partir de un objeto CompradorEntity.
     *
     * @param compradorEntity Entidad CompradorEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public CompradorDTO(CompradorEntity compradorEntity) {
        if (compradorEntity != null) {
            this.id = compradorEntity.getId();
            this.usuario = compradorEntity.getUsuario();
            this.contrasenia = compradorEntity.getContrasenia();
            this.apodo = compradorEntity.getApodo();
            this.correoElectronico = compradorEntity.getCorreoElectronico();
            this.carritoDeCompras =  new CarritoDeComprasDTO(compradorEntity.getCarritoDeCompras());
            this.listaDeDeseos =  new ListaDeDeseosDTO(compradorEntity.getListaDeDeseos());
              
        }
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
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.setId((Long) id);
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
     * @return the carritoDeCompras
     */
    public CarritoDeComprasDTO getCarritoDeCompras() {
        return carritoDeCompras;
    }

    /**
     * @param carritoDeCompras the carritoDeCompras to set
     */
    public void setCarritoDeCompras(CarritoDeComprasDTO carritoDeCompras) {
        this.carritoDeCompras = carritoDeCompras;
    }

    /**
     * @return the listaDeDeseos
     */
    public ListaDeDeseosDTO getListaDeDeseos() {
        return listaDeDeseos;
    }

    /**
     * @param listaDeDeseos the listaDeDeseos to set
     */
    public void setListaDeDeseos(ListaDeDeseosDTO listaDeDeseos) {
        this.listaDeDeseos = listaDeDeseos;
    }
    
     /**
     * Convierte un objeto CompradorDTO a CompradorEntity.
     *
     * @return Nueva objeto CompradorEntity.
     *
     */
    public CompradorEntity toEntity() {
        CompradorEntity compradorEntity = new CompradorEntity();
        compradorEntity.setId(this.getId());
        compradorEntity.setUsuario(this.getUsuario());
        compradorEntity.setContrasenia(this.getContrasenia());
        compradorEntity.setApodo(this.getApodo());
        compradorEntity.setCorreoElectronico(this.getCorreoElectronico());
        compradorEntity.setCarritoDeCompras(this.getCarritoDeCompras().toEntity());
        compradorEntity.setListaDeDeseos(this.getListaDeDeseos().toEntity());
        return compradorEntity;
    }
    
    
}
