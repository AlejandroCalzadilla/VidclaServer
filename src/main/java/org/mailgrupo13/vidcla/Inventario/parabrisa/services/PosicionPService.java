package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.PosicionPDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.PosicionP;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface PosicionPService {


     PosicionPDTO  create(PosicionPDTO categoriaPDTO);
     ResponseEntity<?> delete(UUID id);
     PosicionPDTO findById(UUID id);
     List<PosicionPDTO> findAll();
     PosicionP mapToEntity(PosicionPDTO posicionPDTO);
     PosicionPDTO mapToDTO(PosicionP posicionP);
}
