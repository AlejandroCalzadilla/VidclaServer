package org.mailgrupo13.vidcla.Inventario.parabrisa.mappers;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.PosicionPDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.PosicionP;
import org.springframework.stereotype.Component;

@Component
public class PosicionPMapper {


    public PosicionPDTO mapToDTO(PosicionP posicionP) {
        return new PosicionPDTO(
                posicionP.getId(),
                posicionP.getNombre(),
                posicionP.getCodigo(),
                posicionP.getCreadoEn(),
                posicionP.getActualizadoEn()
        );
    }



    public  PosicionP mapToEntity(PosicionPDTO posicionPDTO){
        PosicionP categoriaP=new PosicionP();
        categoriaP.setId(posicionPDTO.getId());
        categoriaP.setNombre(posicionPDTO.getNombre());
        categoriaP.setCodigo(posicionPDTO.getCodigo());
        return categoriaP;
    }

}
