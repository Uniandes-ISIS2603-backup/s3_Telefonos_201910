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
import java.util.List;

public class CarritoDeComprasDetailDTO extends CarritoDeComprasDTO{
	
    private List<PublicacionDTO> publicaciones;
    
    public CarritoDeComprasDetailDTO()
	{
            super();
	}
    
    public List<PublicacionDTO> getPublicaciones(){
        return this.publicaciones;
    }
    
    public void setPublicaciones(List<PublicacionDTO> publicaciones){
        this.publicaciones = publicaciones;
    }
}
