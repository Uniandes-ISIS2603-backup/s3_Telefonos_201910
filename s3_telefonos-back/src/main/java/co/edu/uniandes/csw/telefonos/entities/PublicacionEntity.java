/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author rj.gonzalez10
 */
@Entity
public class PublicacionEntity extends BaseEntity implements Serializable{
    //fecha de creación de la publicación
    private Date fechaCreacion;
    //arreglo de imagenes que se van a  usar en el post
    private ArrayList<String> imagenes;
    //precio del producto ofrecido
    private Double precio;
    //celular que esta asociado a la publicación, tiene que ser null en caso de que la publicación ya tenga asociada 
    @PodamExclude
    @OneToOne(mappedBy = "publicacion",
              fetch = FetchType.EAGER)
    private CelularEntity celular;
    //Tablet que esta aspciada a la publicación, tiene que ser null si tiene una publicación asociada
    @PodamExclude
    @OneToOne(mappedBy = "publicacion",
              fetch = FetchType.EAGER)
    private TabletEntity tablet;
    //Carrito de compras
    @PodamExclude
    @ManyToMany(mappedBy = "publicacion", fetch = FetchType.LAZY)
    private List<CarritoDeComprasEntity> carritoDeCompras;
    //factura
    @PodamExclude
    @OneToOne(mappedBy = "publicacion", fetch = FetchType.LAZY)
    private FacturaEntity factura;
    //proveedor
    @PodamExclude
    @ManyToOne(fetch = FetchType.LAZY)
    private ProveedorEntity proveedor;

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public CelularEntity getCelular() {
        return celular;
    }

    public void setCelular(CelularEntity celular) {
        this.celular = celular;
    }

    public TabletEntity getTablet() {
        return tablet;
    }

    public void setTablet(TabletEntity tablet) {
        this.tablet = tablet;
    }

    public ArrayList<String> getImagenes() {
        return imagenes;
    }

    public void setImagenes(ArrayList<String> imagenes) {
        this.imagenes = imagenes;
    }
     public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    
    public List<CarritoDeComprasEntity> getCarritoDeCompras() {
        return carritoDeCompras;
    }

    public void setCarritoDeCompras(List<CarritoDeComprasEntity> carritoDeCompras) {
        this.carritoDeCompras = carritoDeCompras;
    }

   
    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }
    
    
}
