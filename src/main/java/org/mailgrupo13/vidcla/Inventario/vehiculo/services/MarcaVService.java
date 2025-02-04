package org.mailgrupo13.vidcla.Inventario.vehiculo.services;

import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.MarcaVDTO;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.MarcaV;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MarcaVService {

     MarcaVDTO create(MarcaVDTO marcaVDTO);
        ResponseEntity<List<MarcaVDTO>> findAll();
     MarcaVDTO findById(UUID id);
     ResponseEntity<?> delete(UUID id);
     MarcaV convertToEntity(MarcaVDTO marcaVDTO);




}
