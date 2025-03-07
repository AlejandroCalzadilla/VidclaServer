package org.mailgrupo13.vidcla.ventas.notaventa.services;

import org.mailgrupo13.vidcla.ventas.notaventa.dtos.DetalleNotaVentaDTO;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.DetalleNotaVenta;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.NotaVenta;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface DetalleNotaVentaService {

    DetalleNotaVentaDTO findById(UUID id);
    List<DetalleNotaVentaDTO> findAll();
    void create(NotaVenta notaCompra, DetalleNotaVentaDTO detalleNotaCompraDTO);
    void update(UUID id, DetalleNotaVentaDTO detalleNotaCompraDTO);
    ResponseEntity<?> delete(UUID id);
    DetalleNotaVentaDTO mapToDTO(DetalleNotaVenta detalleNotaCompra);
    DetalleNotaVenta mapToEntity(DetalleNotaVentaDTO detalleNotaCompraDTO);


}
