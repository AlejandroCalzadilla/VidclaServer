package org.mailgrupo13.vidcla.Inventario.almacen.services;

import org.mailgrupo13.vidcla.Inventario.almacen.dto.AlmacenDTO;
import org.mailgrupo13.vidcla.Inventario.almacen.entities.Almacen;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;

public interface AlmacenService {

    ResponseEntity<List<AlmacenDTO>> findAll();
    AlmacenDTO findById(UUID id);
    AlmacenDTO create(AlmacenDTO almacenDTO);
    AlmacenDTO update(UUID id, AlmacenDTO almacenDTO);
    ResponseEntity<?> delete(UUID id);
    AlmacenDTO convertToDTO(Almacen almacen);
    Almacen mapToEntity(AlmacenDTO almacenDTO);




}
