package org.mailgrupo13.vidcla.Inventario.parabrisa.services;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.CategoriaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface CategoriaPService {

    public CategoriaDTO create(CategoriaDTO categoriaPDTO);
    public ResponseEntity<String> delete(UUID id);
    public CategoriaDTO findById(UUID id);
    public CategoriaP convertToEntity(CategoriaDTO categoriaPDTO);
}
