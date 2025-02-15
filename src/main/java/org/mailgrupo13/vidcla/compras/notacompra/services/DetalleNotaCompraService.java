package org.mailgrupo13.vidcla.compras.notacompra.services;

import org.mailgrupo13.vidcla.compras.notacompra.dtos.DetalleNotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface DetalleNotaCompraService {

  DetalleNotaCompraDTO create(NotaCompra notaCompra, DetalleNotaCompraDTO detalleNotaCompraDTO);


  DetalleNotaCompraDTO update(UUID id, DetalleNotaCompraDTO detalleNotaCompraDTO);


  ResponseEntity<?> delete(UUID id);


  DetalleNotaCompraDTO findById(UUID id);


  ResponseEntity<List<DetalleNotaCompraDTO>> findAll();

  DetalleNotaCompraDTO convertToDTO(DetalleNotaCompra detalleNotaCompra);



  DetalleNotaCompra convertToEntity(DetalleNotaCompraDTO detalleNotaCompraDTO);


}
