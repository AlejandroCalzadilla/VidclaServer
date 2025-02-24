package org.mailgrupo13.vidcla.Inventario.parabrisa.services.interfaces;

import org.mailgrupo13.vidcla.Inventario.parabrisa.dto.CategoriaDTO;
import org.mailgrupo13.vidcla.Inventario.parabrisa.entities.CategoriaP;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CategoriaPService {

    public CategoriaDTO create(CategoriaDTO categoriaPDTO);
    public ResponseEntity<?> delete(UUID id);
    public CategoriaDTO findById(UUID id);
    public CategoriaP convertToEntity(CategoriaDTO categoriaPDTO);
    public List<CategoriaDTO> findAll();
}
