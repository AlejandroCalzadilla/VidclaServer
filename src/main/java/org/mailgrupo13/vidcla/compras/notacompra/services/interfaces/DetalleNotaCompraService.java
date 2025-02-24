package org.mailgrupo13.vidcla.compras.notacompra.services.interfaces;

import org.mailgrupo13.vidcla.compras.notacompra.dtos.DetalleNotaCompraDTO;
import org.mailgrupo13.vidcla.compras.notacompra.entities.DetalleNotaCompra;
import org.mailgrupo13.vidcla.compras.notacompra.entities.NotaCompra;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.UUID;


public interface DetalleNotaCompraService {

  DetalleNotaCompraDTO findById(UUID id);
  ResponseEntity<List<DetalleNotaCompraDTO>> findAll();
  void create(NotaCompra notaCompra, DetalleNotaCompraDTO detalleNotaCompraDTO);
  ResponseEntity<?> delete(UUID id);
  DetalleNotaCompraDTO convertToDTO(DetalleNotaCompra detalleNotaCompra);
  DetalleNotaCompra convertToEntity(DetalleNotaCompraDTO detalleNotaCompraDTO);


}
