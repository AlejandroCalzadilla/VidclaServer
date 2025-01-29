package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.ParabrisaDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParabrisaService {

    List<ParabrisaDTO> findAll();
    Optional<ParabrisaDTO> findById(UUID id);
    ParabrisaDTO create(ParabrisaDTO windshieldDTO);
    ResponseEntity<String> update(UUID id, ParabrisaDTO windshieldDTO);
    ResponseEntity<String> delete(UUID id);











}
