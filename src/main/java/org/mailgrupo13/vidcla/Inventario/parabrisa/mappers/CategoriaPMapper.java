package org.mailgrupo13.vidcla.Inventario.parabrisa.mappers;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.CategoriaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.springframework.stereotype.Component;

@Component
public class CategoriaPMapper {

    public CategoriaDTO mapToDTO(CategoriaP categoriaP){
        return new CategoriaDTO(
                categoriaP.getId(),
                categoriaP.getNombre(),
                categoriaP.getCodigo(),
                categoriaP.getCreadoEn(),
                categoriaP.getActualizadoEn());
    }


    public CategoriaP mapToEntity(CategoriaDTO categoriaPDTO){
        CategoriaP categoriaP=new CategoriaP();
        categoriaP.setId(categoriaPDTO.getId());
        categoriaP.setNombre(categoriaPDTO.getNombre());
        categoriaP.setCodigo(categoriaPDTO.getCodigo());
        return categoriaP;
    }


}
