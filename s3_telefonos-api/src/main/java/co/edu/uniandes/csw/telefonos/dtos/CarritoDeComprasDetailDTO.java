/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.telefonos.dtos;

/**
 *
 * @author estudiante
 */
import co.edu.uniandes.csw.telefonos.entities.CarritoDeComprasEntity;
import co.edu.uniandes.csw.telefonos.entities.PublicacionEntity;
import java.util.ArrayList;
import java.util.List;

public class CarritoDeComprasDetailDTO extends CarritoDeComprasDTO{
	
    private List<PublicacionDTO> publicaciones;
    
    public CarritoDeComprasDetailDTO()
    {
            super();
    }
    
    public CarritoDeComprasDetailDTO(CarritoDeComprasEntity carritoEntity)
    {
        super(carritoEntity);
        if (carritoEntity != null) {
            publicaciones = new ArrayList<PublicacionDTO>();
            for (PublicacionEntity entityPublicacion : carritoEntity.getPublicaciones()) {
                publicaciones.add(new PublicacionDTO(entityPublicacion));
            }
        }
    }
    
    public List<PublicacionDTO> getPublicaciones(){
        return this.publicaciones;
    }
    
    public void setPublicaciones(List<PublicacionDTO> publicaciones){
        this.publicaciones = publicaciones;
    }
    
    
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
