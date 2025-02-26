package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.Parabrisa;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ParabrisaService {

    List<ParabrisaDTO> findAll();
    ParabrisaDTO findById(UUID id);
    ParabrisaDTO create(ParabrisaDTO windshieldDTO);
    ParabrisaDTO update(UUID id, ParabrisaDTO windshieldDTO);
    ResponseEntity<?> delete(UUID id);
    Parabrisa mapToEntity(ParabrisaDTO parabrisa);
    ParabrisaDTO mapToDTO(Parabrisa parabrisa);



}
