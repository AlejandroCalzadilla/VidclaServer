package org.mailgrupo13.vidcla.Inventario.vehiculo.services;

import org.mailgrupo13.vidcla.Inventario.vehiculo.dto.MarcaVDTO;
import org.mailgrupo13.vidcla.Inventario.vehiculo.entities.MarcaV;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface MarcaVService {

    public MarcaVDTO create(MarcaVDTO marcaVDTO);
    public MarcaVDTO findById(UUID id);
    public ResponseEntity<String> delete(UUID id);
    public MarcaV convertToEntity(MarcaVDTO marcaVDTO);




}
