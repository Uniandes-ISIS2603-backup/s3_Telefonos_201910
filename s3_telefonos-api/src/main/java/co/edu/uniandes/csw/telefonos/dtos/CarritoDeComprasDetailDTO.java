/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

/**
 *
 * @author Daniel Babativa
 */
import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import java.util.ArrayList;
import java.util.List;

public class CarritoDeComprasDetailDTO extends CarritoDeComprasDTO{
    
    /**
     * Lista de publicaciones del carrito de compras
     */
    private List<PublicacionDTO> publicaciones;
    
    /**
     * Constructor super
     */
    public CarritoDeComprasDetailDTO()
    {
            super();
    }
    
    /**
     * Constructor con parametro entity
     * @param carritoEntity el entity que se convierte en dto
     */
    public CarritoDeComprasDetailDTO(CarritoDeComprasEntity carritoEntity)
    {
        super(carritoEntity);
        if (carritoEntity != null) {
            if(carritoEntity.getPublicaciones()!=null){
                publicaciones = new ArrayList<PublicacionDTO>();
            for (PublicacionEntity entityPublicacion : carritoEntity.getPublicaciones()) {
                publicaciones.add(new PublicacionDTO(entityPublicacion));
            }
            }
        }
    }
    
    /**
     * Retorna la lista de publicaciones del carrito de compras
     * @return la lista de publicaciones
     */
    public List<PublicacionDTO> getPublicaciones(){
        return this.publicaciones;
    }
    
    /**
     * Actualiza la lista de publicaciones
     * @param publicaciones la lista de publicaciones que reemplazará la antigua
     */
    public void setPublicaciones(List<PublicacionDTO> publicaciones){
        this.publicaciones = publicaciones;
    }
    
    /**
     * Método que convierte el carrito a un entity
     * @return El nuevo carrito entity con la informacion del carrito actual
     */
    @Override
    public CarritoDeComprasEntity toEntity() {
        CarritoDeComprasEntity carritoEntity = super.toEntity();
        if (publicaciones != null) {
            List<PublicacionEntity> publicacionesEntity = new ArrayList<>();
            for (PublicacionDTO dtoPublicacion : publicaciones) {
                publicacionesEntity.add(dtoPublicacion.toEntity());
            }
            carritoEntity.setPublicaciones(publicacionesEntity);
        }

        return carritoEntity;
    }

    
    
}
