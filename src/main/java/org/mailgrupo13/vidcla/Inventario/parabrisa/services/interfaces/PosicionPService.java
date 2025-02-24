package org.mailgrupo13.vidcla.Inventario.parabrisa.services.interfaces;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.PosicionPDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.PosicionP;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PosicionPService {


    public PosicionPDTO  create(PosicionPDTO categoriaPDTO);
    public ResponseEntity<?> delete(UUID id);
    public PosicionPDTO findById(UUID id);
    public PosicionP convertToEntity(PosicionPDTO posicionPDTO);
    public List<PosicionPDTO> findAll();

}
