/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import javax.persistence.Entity;

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
    
}
