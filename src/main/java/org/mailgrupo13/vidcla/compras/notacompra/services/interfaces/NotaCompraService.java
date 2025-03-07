package org.mailgrupo13.vidcla.compras.notacompra.services.interfaces;

import org.mailgrupo13.vidcla.compras.notacompra.dtos.NotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface NotaCompraService {



     NotaCompra create(NotaCompraDTO notaCompraDTO);
     NotaCompraDTO findById(UUID id);
     void update(UUID id, NotaCompraDTO notaCompraDTO);
     void delete(UUID id);
     NotaCompraDTO convertToDTO(NotaCompra notaCompra);
     NotaCompra convertToEntity(NotaCompraDTO notaCompraDTO);
     ResponseEntity<List<NotaCompraDTO>> findAll();




}
