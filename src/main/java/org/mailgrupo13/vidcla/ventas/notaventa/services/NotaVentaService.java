package org.mailgrupo13.vidcla.ventas.notaventa.services;

import org.mailgrupo13.vidcla.ventas.notaventa.dtos.NotaVentaDTO;
import org.mailgrupo13.vidcla.ventas.notaventa.entities.NotaVenta;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface NotaVentaService {


    NotaVenta create(NotaVentaDTO notaCompraDTO);
    NotaVentaDTO findById(UUID id);
    void update(UUID id, NotaVentaDTO notaCompraDTO);
    void delete(UUID id);
    NotaVentaDTO convertToDTO(NotaVenta notaCompra);
    NotaVenta convertToEntity(NotaVentaDTO notaCompraDTO);
    ResponseEntity<List<NotaVentaDTO>> findAll();

}
